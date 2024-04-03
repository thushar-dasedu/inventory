package com.InventoryManagement.service;

import com.InventoryManagement.entity.*;
import com.InventoryManagement.exception.ElementAlreadyExistsException;
import com.InventoryManagement.exception.NoSuchElementException;
import com.InventoryManagement.repository.FileDataRepository;
import com.InventoryManagement.repository.ProductBrandRepository;
import com.InventoryManagement.repository.ProductModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductModelService {
    @Autowired
   private ProductModelRepository productModelRepository;
    @Autowired
    private ProductBrandRepository productBrandRepository;

    @Autowired
    private FileDataRepository fileDataRepository;

    private final String FOLDER_PATH="C:\\Users\\Lenovo\\MyFiles\\";
    public ProductModel getModelById(int id){
        return productModelRepository.findById(id).orElseThrow(
                ()->new NoSuchElementException("Given product model id "+id+" not present")
        );
    }
    public ProductModel addProductModel(ProductModel model) {

        ProductBrand brand=productBrandRepository.findById(model.getProductId()).orElseThrow(
                ()->new NoSuchElementException("Given product id "+ model.getProductId() +" is not present")
        );
        List<ProductModel> product=productModelRepository.getModelByProductModel(model.getProductModelName());

        if (product.isEmpty()){
            return productModelRepository.save(model);
        }throw new ElementAlreadyExistsException("Given product model already exists");

    }

    public List<ProductModel> listAllModel(){
       return productModelRepository.findAll();
    }
    public ProductModel updateModel(int modelId,ProductModel model) {
        ProductModel productModel = productModelRepository.findById(modelId).orElseThrow(
                () -> new NoSuchElementException("Given model id " + modelId + " not present")
        );
        if (productModel.getModelId() == modelId) {
            productModel.setProductId(model.getProductId());
            productModel.setProductModelName(model.getProductModelName());
            productModel.setUnitPrice(model.getUnitPrice());
            productModel.setTax(model.getTax());
            productModel.setQuantity(model.getQuantity());

        }
        return productModelRepository.save(productModel);
    }
    public List<ProductModel> getModelByName(String name){
         List<ProductModel> models=productModelRepository.getModelByProductModel(name);
         if (models.isEmpty()){
             throw new NoSuchElementException("Given product model "+name+" not exists");
         }return models;

    }
    public void deleteModelById(int modelId){
        ProductModel model=productModelRepository.findById(modelId).orElseThrow(
                ()->new NoSuchElementException("Given model id"+modelId+" not present")
        );
        productModelRepository.delete(model);
    }
    public List<ViewProducts> listProducts(){
        List<Object[]> objects= productModelRepository.viewAllProduct();
        List<ViewProducts>  viewProducts=new ArrayList<>();
        for(Object[] objects1:objects){
            int modelId=(int) objects1[0];
            String brandName=(String) objects1[1];
            String productModelName=(String) objects1[2];
            BigDecimal unitPrice=(BigDecimal) objects1[3];
            float tax=(float) objects1[4];
            int quantity=(int) objects1[5];
            ViewProducts viewProducts1=new ViewProducts(modelId,brandName,productModelName,unitPrice,tax,quantity);
            viewProducts.add(viewProducts1);

        }
        return viewProducts;
    }


    public List<ViewProducts> listProduct(int productId){
        productModelRepository.findById(productId).orElseThrow(
                ()->new NoSuchElementException("Given  product id "+productId+" not present")
        );
       List<Object[]> viewProducts= productModelRepository.viewProduct(productId);
       List<ViewProducts> viewProduct=new ArrayList<>();
         for (Object[] object:viewProducts){
             int modelId=(int) object[0];
             String brandName=(String) object[1];
             String productModelName=(String) object[2];
             BigDecimal unitPrice=(BigDecimal) object[3];
             float tax=(float) object[4];
             int quantity=(int) object[5];
             int id=(int) object[6];
             String name=(String) object[7];
             String type=(String) object[8];
             String filePath=(String) object[9];
             ViewProducts viewProducts1=new ViewProducts(modelId,brandName,productModelName,unitPrice,tax,quantity,id,name,type,filePath);
             viewProduct.add(viewProducts1);


         }
return viewProduct;
    }


    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        String filePath=FOLDER_PATH+file.getOriginalFilename();

        FileData fileData=fileDataRepository.save(FileData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath).build());

        file.transferTo(new File(filePath));

        if (fileData != null) {
            return "file uploaded successfully : " + filePath;
        }
        return null;
    }

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<FileData> fileData = fileDataRepository.findByName(fileName);
        String filePath=fileData.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }

    public List<ProductInfo> getData(){
       List<Object[]>getProduct= fileDataRepository.getProductInfo();
       List<ProductInfo> productInfos=new ArrayList<>();

       for(Object[] object:getProduct){
           int modelId=(int) object[0];
           int productId=(int)object[1];
           String productModelName=(String) object[2];
           BigDecimal unitPrice=(BigDecimal) object[3];
           float tax=(float) object[4];
           int quantity=(int) object[5];
           int id=(int) object[6];
           String name=(String) object[7];
           String type=(String) object[8];
           String filePath=(String) object[9];
           ProductInfo productInfo=new ProductInfo(modelId,productId,productModelName,unitPrice,tax,quantity,id,name,type,filePath);
           productInfos.add(productInfo);
       }
       return productInfos;
    }

}
