package com.InventoryManagement.controller;

import com.InventoryManagement.entity.ProductBrand;
import com.InventoryManagement.exception.DeleteResponse;
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
    public ResponseEntity<DeleteResponse> deleteCustomer(@PathVariable int id){
        try{
            service.deleteProductById(id);
            DeleteResponse deleteResponse=new DeleteResponse("product deleted success fully", HttpStatus.OK.value());
            return new ResponseEntity<>( deleteResponse, HttpStatus.OK);
        }catch (NoSuchElementException e){
            DeleteResponse deleteResponse=new DeleteResponse(e.getMessage(),HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(deleteResponse,HttpStatus.NOT_FOUND);
        }

    }
    @PutMapping("/update-product/{productId}")
    public ProductBrand updateBrand(@PathVariable int productId,@RequestBody ProductBrand productBrand){
        return service.updateProduct(productId,productBrand);
    }


}
