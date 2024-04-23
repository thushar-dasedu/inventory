package com.InventoryManagement.controller;

import com.InventoryManagement.entity.SaleHeader;
import com.InventoryManagement.service.SaleHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/sale-header")
public class SaleHeaderController {
@Autowired
    private SaleHeaderService service;
    @PreAuthorize("hasRole('ADMIN')")
@GetMapping("/get-sale")
    public List<SaleHeader> getSale(){
    return service.getSale();
}
    @PreAuthorize("hasRole('ADMIN')")
@GetMapping("/get-sale-by/{saleId}")
public SaleHeader getSaleId(@PathVariable int saleId){
    return service.getSaleById(saleId);
}
    @PreAuthorize("hasRole('ADMIN')")
@GetMapping("/get-sale-by-date/{localDateTime}")
    public List<SaleHeader> getSaleByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime localDateTime){
    return service.getSaleByDate(localDateTime);
}

}
