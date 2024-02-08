package com.InventoryManagement.service;

import com.InventoryManagement.repository.PurchaseHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseHeaderService {
@Autowired
    private PurchaseHeaderRepository purchaseHeaderRepository;
}
