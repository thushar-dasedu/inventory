package com.InventoryManagement.controller;

import com.InventoryManagement.entity.ProductModel;
import com.InventoryManagement.exception.NoSuchElementException;
import com.InventoryManagement.service.ProductModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/pro-model")
public class ProductModelController {
    @Autowired
    private ProductModelService service;
    @GetMapping("get-by/{id}")
    public ProductModel getModelById(@PathVariable int id){
        return service.getModelById(id);
    }
    @PostMapping("/add-product-model/{productId}")
    public ProductModel addModel(@PathVariable int productId, @RequestParam String productModel, @RequestParam BigDecimal unitPrice
    ,@RequestParam float Tax,@RequestParam int Quantity){
        return service.addProductModel(productId,productModel,unitPrice,Tax,Quantity);
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
    public ResponseEntity<String> deleteModel(@PathVariable int id){
        try {
            ProductModel model=service.getModelById(id);
            service.deleteModelById(id);
            return new  ResponseEntity<>("Product with detail "+model+"deleted", HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }
}
