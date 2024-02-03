package com.InventoryManagement.controller;

import com.InventoryManagement.entity.ProductSerialNumber;
import com.InventoryManagement.service.ProductSerialNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/serial-number")
public class ProductSerialNumberController {
    @Autowired
    private ProductSerialNumberService service;
    @GetMapping("/get-all")
    public List<ProductSerialNumber> getALlSerialNumber(){
        return service.getAllSerialNumber();
    }
    @GetMapping("get-serial-number/{id}")
    public ProductSerialNumber getById(@PathVariable int id){
        return service.getById(id);
    }
    @GetMapping("/get-serial-number-by-stock/{stockId}")
    public List<ProductSerialNumber> getSerialByStock(@PathVariable int stockId){
        return service.getSerialByStock(stockId);
    }
    @PostMapping("/add-serial-number")
    public ProductSerialNumber addSerialNumber(@RequestParam int stockId,@RequestParam String serialNumber){
        return service.addSerial(stockId,serialNumber);
    }
}
