package com.InventoryManagement.controller;

import com.InventoryManagement.entity.AllPurchaseInfo;
import com.InventoryManagement.service.PurchaseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/purchase-detail")
public class PurchaseDetailController {
@Autowired
    private PurchaseDetailService service;

@GetMapping("/get-all-purchase")
    public List<AllPurchaseInfo> getPurchaseInfo() {
    return service.getAllPurchase();
}
}
