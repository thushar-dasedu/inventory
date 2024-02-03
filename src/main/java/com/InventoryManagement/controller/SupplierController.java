package com.InventoryManagement.controller;

import com.InventoryManagement.entity.Customer;
import com.InventoryManagement.entity.Supplier;
import com.InventoryManagement.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    private SupplierService service;
    @GetMapping("/get-supplier-id/{id}")
    public Supplier getById(@PathVariable int id){
        return service.getById(id);
    }
@GetMapping("/get-supplier-by-mobile/{mobileNumber}")
    public List<Supplier> getByMobileNumber(@PathVariable String mobileNumber){
        return service.getByMobileNumber(mobileNumber);
}
@PostMapping("/add-supplier")
    public  Supplier addSupplier(@RequestBody Supplier supplier){
        return service.addSupplier(supplier);
}
}
