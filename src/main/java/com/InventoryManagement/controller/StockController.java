package com.InventoryManagement.controller;

import com.InventoryManagement.entity.Stock;
import com.InventoryManagement.exception.DeleteResponse;
import com.InventoryManagement.exception.NoSuchElementException;
import com.InventoryManagement.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {
    @Autowired
    private StockService service;
    @GetMapping("/get-all-stock")
    public List<Stock> getAllStock(){
        return service.getAllStock();
    }
    @GetMapping("/get-stock-by/{id}")
    public Stock getStockById(@PathVariable int id){
        return service.getStockById(id);
    }
    @PostMapping("/add-stock/{productId}")
    public Stock addStock(@PathVariable int productId){
        return service.addStock(productId);
    }
    @DeleteMapping("/delete-stock-by/{id}")
    public ResponseEntity<DeleteResponse> deleteStockById(@PathVariable int id){
        try {
            service.deleteStockById(id);
            DeleteResponse deleteResponse=new DeleteResponse("Stock detail deleted successfully", HttpStatus.OK.value());
            return new  ResponseEntity<>(deleteResponse, HttpStatus.OK);
        }catch (NoSuchElementException e){
            DeleteResponse deleteResponse=new DeleteResponse(e.getMessage(),HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(deleteResponse,HttpStatus.NOT_FOUND);
        }

    }
}
