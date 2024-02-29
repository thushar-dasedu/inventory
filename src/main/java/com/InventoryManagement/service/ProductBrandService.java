package com.InventoryManagement.service;

import com.InventoryManagement.entity.Customer;
import com.InventoryManagement.entity.ProductBrand;
import com.InventoryManagement.exception.ElementAlreadyExistsException;
import com.InventoryManagement.exception.NoSuchElementException;
import com.InventoryManagement.repository.ProductBrandRepository;
import com.InventoryManagement.repository.ProductModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductBrandService {
    @Autowired
    private ProductBrandRepository productBrandRepository;
    @Autowired
    private ProductModelRepository productModelRepository;
    public ProductBrand getProductById(int productId){
        return productBrandRepository.findById(productId).orElseThrow(
                ()->new  NoSuchElementException("Given product id "+productId+" not present"));
    }
    public ProductBrand addProduct(ProductBrand productBrand){
        List<ProductBrand> brands=productBrandRepository.getProductByName(productBrand.getBrandName());
        if (brands.isEmpty()){
            return productBrandRepository.save(productBrand);
        }throw new ElementAlreadyExistsException("Given brand name "+productBrand.getBrandName()+" already exists");
    }

    public List<ProductBrand> getProduct(){
        return productBrandRepository.findAll();
    }

    public void deleteProductById(int id){
        ProductBrand productBrand=productBrandRepository.findById(id).orElseThrow(
                ()->new java.util.NoSuchElementException("given product id not present")
        );
         productBrandRepository.deleteById(id);}
    public ProductBrand updateProduct(int productId, ProductBrand productBrand){
        productBrandRepository.findById(productId).orElseThrow(
                ()->new NoSuchElementException("Given product Id "+productId+" not present")
        );
       return productBrandRepository.save(productBrand);
    }
public boolean checkSerialNumber(int modelId){
        productModelRepository.findById(modelId).orElseThrow(()->new NoSuchElementException("given model id "+modelId+" not present"));
        return  productBrandRepository.containSerialNumber(modelId);
}
}
