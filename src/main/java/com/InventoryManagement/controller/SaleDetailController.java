package com.InventoryManagement.controller;

import com.InventoryManagement.entity.AllSalesInformation;
import com.InventoryManagement.entity.SaleDetail;
import com.InventoryManagement.entity.SaleModel;
import com.InventoryManagement.exception.DeleteResponse;
import com.InventoryManagement.exception.NoSuchElementException;
import com.InventoryManagement.service.SaleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/sale-detail")
public class SaleDetailController {
    @Autowired
    private SaleDetailService service;
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get-sale-detail")
    public List<SaleDetail> getSaleDetail(){
        return service.getSaleDetail();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add-sale-detail")
    public  SaleModel  addSale(@RequestBody SaleModel saleModel) {

        return service.addSaleDetail(saleModel);
    }
    @PreAuthorize("hasRole('ADMIN')")

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
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get-sale")
    public List<AllSalesInformation > getAllSale(){
        return service.getAllSale();

    }


}
