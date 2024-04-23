package com.InventoryManagement.controller;

import com.InventoryManagement.entity.Stock;
import com.InventoryManagement.exception.DeleteResponse;
import com.InventoryManagement.exception.NoSuchElementException;
import com.InventoryManagement.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/stock")
public class StockController {
    @Autowired
    private StockService service;
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get-all-stock")
    public List<Stock> getAllStock(){
        return service.getAllStock();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get-stock-by/{id}")
    public Stock getStockById(@PathVariable int id){
        return service.getStockById(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add-stock")
    public Stock addStock(@RequestBody Stock stock){
        return service.addStock(stock);
    }
    @PreAuthorize("hasRole('ADMIN')")
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
