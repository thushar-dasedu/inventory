package com.InventoryManagement.controller;

import com.InventoryManagement.entity.SaleDetail;
import com.InventoryManagement.exception.DeleteResponse;
import com.InventoryManagement.exception.NoSuchElementException;
import com.InventoryManagement.service.SaleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sale-detail")
public class SaleDetailController {
    @Autowired
    private SaleDetailService service;
    @GetMapping("/get-sale-detail")
    public List<SaleDetail> getSaleDetail(){
        return service.getSaleDetail();
    }
    @PostMapping("/add-sale-detail")
    public List<SaleDetail> addSale(@RequestBody SaleDetail saleDetail){
        return service.addSaleDetail(saleDetail);
    }
    @DeleteMapping("/delete-sale-detail/{saleId}")
    public ResponseEntity<DeleteResponse> deleteSale(@PathVariable int saleId){
        try {
            service.deleteSaleDetailBySale(saleId);
            DeleteResponse deleteResponse=new DeleteResponse("sale detail deleted success fully", HttpStatus.OK.value());
            return new ResponseEntity<>(deleteResponse, HttpStatus.OK);
        }catch (NoSuchElementException e){
            DeleteResponse deleteResponse=new DeleteResponse(e.getMessage(),HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(deleteResponse,HttpStatus.NOT_FOUND);
        }


    }
    @DeleteMapping("/delete-sale-detail-by/{id}")
    public ResponseEntity<DeleteResponse>deleteSaleById(@PathVariable int id){
        try {
            service.deleteSaleById(id);
            DeleteResponse deleteResponse=new DeleteResponse("sale detail deleted success fully",HttpStatus.OK.value());
            return new ResponseEntity<>(deleteResponse,HttpStatus.OK);
        }catch (NoSuchElementException e){
            DeleteResponse deleteResponse=new DeleteResponse(e.getMessage(),HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(deleteResponse,HttpStatus.NOT_FOUND);
        }

    }
}
