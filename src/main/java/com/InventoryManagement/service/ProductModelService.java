package com.InventoryManagement.service;

import com.InventoryManagement.entity.ProductBrand;
import com.InventoryManagement.entity.ProductModel;
import com.InventoryManagement.exception.ElementAlreadyExistsException;
import com.InventoryManagement.exception.NoSuchElementException;
import com.InventoryManagement.repository.ProductBrandRepository;
import com.InventoryManagement.repository.ProductModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductModelService {
    @Autowired
   private ProductModelRepository productModelRepository;
    @Autowired
    private ProductBrandRepository productBrandRepository;
    public ProductModel getModelById(int id){
        return productModelRepository.findById(id).orElseThrow(
                ()->new NoSuchElementException("Given product model id "+id+" not present")
        );
    }
    public ProductModel addProductModel(int productId, String productModel, BigDecimal unitPrice,float Tax,int Quantity) {

        ProductBrand brand=productBrandRepository.findById(productId).orElseThrow(
                ()->new NoSuchElementException("Given product id "+ productId +" is not present")
        );
        List<ProductModel> product=productModelRepository.getModelByProductModel(productModel);

        if (product.isEmpty()){
            return productModelRepository.addProductModel(productId,productModel,unitPrice,Tax,Quantity) ;
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
}
