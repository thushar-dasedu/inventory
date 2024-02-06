package com.InventoryManagement.controller;

import com.InventoryManagement.entity.ProductSerialNumber;
import com.InventoryManagement.exception.DeleteResponse;
import com.InventoryManagement.exception.NoSuchElementException;
import com.InventoryManagement.service.ProductSerialNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ProductSerialNumber addSerialNumber(@RequestBody ProductSerialNumber serialNumber){
        return service.addSerial(serialNumber);
    }
    @DeleteMapping("/delete-serial-number/{serialNumber}")
    public ResponseEntity<DeleteResponse>deleteSerialNumber(@PathVariable String serialNumber){
        try {
            service.deleteSerialNumber(serialNumber);
            DeleteResponse deleteResponse=new DeleteResponse("serial number detail deleted success fully", HttpStatus.OK.value());
            return new ResponseEntity<>(deleteResponse,HttpStatus.OK);
        }catch (NoSuchElementException e){
            DeleteResponse deleteResponse=new DeleteResponse(e.getMessage(),HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(deleteResponse,HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/get-serial-by/{serial}")
    public ProductSerialNumber getSerialBySerial(@PathVariable String serial){
        return service.getBySerial(serial);
    }
}
