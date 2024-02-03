package com.InventoryManagement.controller;

import com.InventoryManagement.entity.SaleHeader;
import com.InventoryManagement.service.SaleHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/sale-header")
public class SaleHeaderController {
@Autowired
    private SaleHeaderService service;
@GetMapping("/get-sale")
    public List<SaleHeader> getSale(){
    return service.getSale();
}
@GetMapping("/get-sale-by/{saleId}")
public SaleHeader getSaleId(@PathVariable int saleId){
    return service.getSaleById(saleId);
}
@GetMapping("/get-sale-by-date")
    public List<SaleHeader> getSaleByDate(@RequestParam LocalDateTime date){
    return service.getSaleByDate(date);
}
@PostMapping("/add-sale")
    public SaleHeader addSale(@RequestParam int customerId){
    return service.addSaleHeader(customerId);
}
}
