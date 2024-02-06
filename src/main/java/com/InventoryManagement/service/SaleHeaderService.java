package com.InventoryManagement.service;

import com.InventoryManagement.entity.Customer;
import com.InventoryManagement.entity.SaleHeader;
import com.InventoryManagement.exception.NoSuchElementException;
import com.InventoryManagement.repository.CustomerRepository;
import com.InventoryManagement.repository.SaleHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class SaleHeaderService {
    @Autowired
    private SaleHeaderRepository saleHeaderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    public List<SaleHeader> getSale(){
        return saleHeaderRepository.findAll();
    }
    public SaleHeader getSaleById(int saleId){
        return saleHeaderRepository.findById(saleId).orElseThrow(
                ()-> new NoSuchElementException("Given Sale id not present")
        );
    }
    public List<SaleHeader> getSaleByDate(LocalDateTime localDateTime){
         List<SaleHeader> saleHeaders= saleHeaderRepository.getSaleByDate(localDateTime);
         if (saleHeaders.isEmpty()){
             throw new NoSuchElementException("There is no sale record on "+localDateTime);
         }return saleHeaders;

    }
    public SaleHeader addSaleHeader(SaleHeader saleHeader){
        Customer customer=customerRepository.findById(saleHeader.getCustomerId()).orElseThrow(
                ()->new NoSuchElementException("Given customer id "+saleHeader.getCustomerId()+" not present")
        );
        return saleHeaderRepository.addSale(saleHeader.getCustomerId() );
    }
}
