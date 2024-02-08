package com.InventoryManagement.controller;

import com.InventoryManagement.service.PurchaseHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchase")
public class PurchaseHeaderController {
@Autowired
    private PurchaseHeaderService service;
}
