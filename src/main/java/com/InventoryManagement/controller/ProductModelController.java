package com.InventoryManagement.controller;

import com.InventoryManagement.entity.ProductModel;
import com.InventoryManagement.entity.ViewProducts;
import com.InventoryManagement.exception.DeleteResponse;
import com.InventoryManagement.exception.NoSuchElementException;
import com.InventoryManagement.service.ProductModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/pro-model")
public class ProductModelController {
    @Autowired
    private ProductModelService service;
    @GetMapping("get-by/{id}")
    public ProductModel getModelById(@PathVariable int id){
        return service.getModelById(id);
    }
    @PostMapping("/add-product-model")
    public ProductModel addModel(@RequestBody ProductModel model){
        return service.addProductModel(model);
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
}
