package com.InventoryManagement.service;

import com.InventoryManagement.entity.Supplier;
import com.InventoryManagement.exception.MobileNumberAndEmailExistsException;
import com.InventoryManagement.exception.MobileNumberNotFoundException;
import com.InventoryManagement.exception.NoSuchElementException;
import com.InventoryManagement.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository repository;

    public Supplier getById(int supplierId){
       return repository.findById(supplierId).orElseThrow(
               ()->new NoSuchElementException("Given supplierId "+supplierId+" not present"));
    }
    public List<Supplier> getByMobileNumber(String mobileNumber){
         List<Supplier> suppliers=repository.getSupplierByMobile(mobileNumber);
         if (suppliers.isEmpty()){
             throw new MobileNumberNotFoundException("Given mobile number :"+mobileNumber+" not present");
         }return suppliers;
    }
    public Supplier addSupplier(Supplier supplier){
        List<Supplier> suppliers=repository.getSupplierByMobile(supplier.getMobileNumber());
        if (suppliers.isEmpty()){
            return repository.save(supplier);
        }throw new MobileNumberAndEmailExistsException("Given mobile number "+supplier.getMobileNumber()+" and email "
        +supplier.getEmail()+" already present");
    }

}
