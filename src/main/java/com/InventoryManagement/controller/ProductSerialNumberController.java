package com.InventoryManagement.controller;

import com.InventoryManagement.entity.ProductSerialNumber;
import com.InventoryManagement.exception.DeleteResponse;
import com.InventoryManagement.exception.NoSuchElementException;
import com.InventoryManagement.service.ProductSerialNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/serial-number")
public class ProductSerialNumberController {
    @Autowired
    private ProductSerialNumberService service;
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get-all")
    public List<ProductSerialNumber> getALlSerialNumber(){
        return service.getAllSerialNumber();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("get-serial-number/{id}")
    public ProductSerialNumber getById(@PathVariable int id){
        return service.getById(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get-serial-number-by-stock/{stockId}")
    public List<ProductSerialNumber> getSerialByStock(@PathVariable int stockId){
        return service.getSerialByStock(stockId);
    }
    @PreAuthorize("hasRole('ADMIN')")

    @PostMapping("/add-serial-number")
    public ProductSerialNumber addSerialNumber(@RequestBody ProductSerialNumber serialNumber){
        return service.addSerial(serialNumber);
    }
    @PreAuthorize("hasRole('ADMIN')")

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
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get-serial-by/{serial}")
    public ProductSerialNumber getSerialBySerial(@PathVariable String serial){
        return service.getBySerial(serial);
    }
}
