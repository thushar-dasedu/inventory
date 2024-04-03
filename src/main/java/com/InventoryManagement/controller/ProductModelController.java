package com.InventoryManagement.controller;

import com.InventoryManagement.entity.FileData;
import com.InventoryManagement.entity.ProductInfo;
import com.InventoryManagement.entity.ProductModel;
import com.InventoryManagement.entity.ViewProducts;
import com.InventoryManagement.exception.DeleteResponse;
import com.InventoryManagement.exception.NoSuchElementException;
import com.InventoryManagement.service.ProductModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/pro-model")
public class ProductModelController {
    @Autowired
    private ProductModelService service;
    private final String FOLDER_PATH="C:\\Users\\Lenovo\\MyFiles\\";

    @GetMapping("get-by/{id}")
    public ProductModel getModelById(@PathVariable int id){
        return service.getModelById(id);
    }
    @PostMapping(value = "/add-product-model",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ProductModel addModel(@RequestPart("product") ProductModel model,
                                 @RequestPart("image")MultipartFile[] file){
//        return service.addProductModel(model);

        try {
          Set<FileData> fileData=  uploadImages(file);
          model.setImageMadel(fileData);
          return service.addProductModel(model);

        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Set<FileData> uploadImages(MultipartFile[] multipartFiles) throws IOException {
        Set<FileData> imageModels =new HashSet<>();
        for (MultipartFile file:multipartFiles){
            String filePath=FOLDER_PATH+file.getOriginalFilename();
            FileData fileData=new FileData(
                    file.getOriginalFilename(),
                    file.getContentType(),filePath

            );
            file.transferTo(new File(filePath));
            imageModels.add(fileData);
        }
        return imageModels;
    }




    @GetMapping("/get-all-model")
    public List<ProductModel> getAllModel(){
        return service.listAllModel();
    }
    @PutMapping("/update-model-by/{id}")
    public ProductModel updateModel(@PathVariable int id,@RequestBody ProductModel model){
        return service.updateModel(id,model);
    }
    @GetMapping("/get-model-by-name/{name}")
    public List<ProductModel> getModelByName(@PathVariable String name){
        return service.getModelByName(name);
    }
    @DeleteMapping("/delete-model-by/{id}")
    public ResponseEntity<DeleteResponse> deleteModel(@PathVariable int id){
        try {
            ProductModel model=service.getModelById(id);
            service.deleteModelById(id);
            DeleteResponse deleteResponse=new DeleteResponse("Product   detail deleted", HttpStatus.OK.value());
            return new  ResponseEntity<>(deleteResponse, HttpStatus.OK);
        }catch (NoSuchElementException e){
            DeleteResponse deleteResponse=new DeleteResponse(e.getMessage(),HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(deleteResponse,HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/view-products/{productId}")
    public List<ViewProducts> viewProducts(@PathVariable int productId){
     return    service.listProduct(productId);
    }

    @GetMapping("/view/all-product")
    public List<ViewProducts> listProduct(){
        return service.listProducts();
    }

    @PostMapping("/fileSystem")
    public ResponseEntity<?> uploadImageToFIleSystem(@RequestParam("image") MultipartFile file) throws IOException {
        String uploadImage = service.uploadImageToFileSystem(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @GetMapping("/fileSystem/{fileName}")
    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException {
        byte[] imageData=service.downloadImageFromFileSystem(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);

    }

@GetMapping("/images")
    public List<ProductInfo> getData(){
        return service.getData();
}
}
