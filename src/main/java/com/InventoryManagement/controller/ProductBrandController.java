package com.InventoryManagement.controller;

import com.InventoryManagement.entity.ProductBrand;
import com.InventoryManagement.service.ProductBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/product")
public class ProductBrandController {
    @Autowired
    private ProductBrandService service;
    @GetMapping("/get-product-by/{id}")
    public ProductBrand getProductById(@PathVariable int id){
        return service.getProductById(id);
    }
    @PostMapping("/add-product-brand")
    public ProductBrand addProduct(@RequestBody ProductBrand brand){
        return service.addProduct(brand);
    }
    @GetMapping("/get-all-product")
    public List<ProductBrand> getProduct(){
        return service.getProduct();
    }
    @DeleteMapping("/delete-product-by/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable int id){
        try{
            service.deleteProductById(id);
            return new ResponseEntity<>("product deleted success fully", HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }

}
