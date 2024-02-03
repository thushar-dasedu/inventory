package com.InventoryManagement.service;

import com.InventoryManagement.repository.SaleDetailRepository;
import com.InventoryManagement.repository.SaleHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleDetailService {
@Autowired
    private SaleDetailRepository saleDetailRepository;
}
