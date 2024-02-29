package com.InventoryManagement.controller;

import com.InventoryManagement.service.PurchaseHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/purchase")
public class PurchaseHeaderController {
@Autowired
    private PurchaseHeaderService service;
}
