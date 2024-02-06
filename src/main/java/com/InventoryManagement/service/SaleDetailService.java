package com.InventoryManagement.service;

import com.InventoryManagement.entity.ProductModel;
import com.InventoryManagement.entity.ProductSerialNumber;
import com.InventoryManagement.entity.SaleDetail;
import com.InventoryManagement.entity.SaleHeader;
import com.InventoryManagement.exception.BadQuantityAssignException;
import com.InventoryManagement.exception.NoSuchElementException;
import com.InventoryManagement.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SaleDetailService {
@Autowired
    private SaleDetailRepository saleDetailRepository;
@Autowired
private ProductModelRepository productModelRepository;
@Autowired
private SaleHeaderRepository saleHeaderRepository;
@Autowired
private ProductSerialNumberRepository repository;
@Autowired
private StockRepository stockRepository;


public List<SaleDetail> getSaleDetail(){
    return saleDetailRepository.findAll();
}

public List<SaleDetail>addSaleDetail(SaleDetail saleDetail){
    saleHeaderRepository.findById(saleDetail.getSaleId())
            .orElseThrow(() -> new NoSuchElementException("Given Sale id " + saleDetail.getSaleId() + " not present"));

    // Check if ProductModel exists
   ProductModel productModel= productModelRepository.findById(saleDetail.getProductModelID())
            .orElseThrow(() -> new NoSuchElementException("Given product model id "+saleDetail.getProductModelID()+" not present"));

   if (productModel.getQuantity()>=saleDetail.getQuantity()&&productModel.getQuantity()!=0){


   Set<Integer> allowedProduct=Set.of(1,2,3,4,5,6,7,8);
    if (allowedProduct.contains(saleDetail.getProductModelID())){
       ProductSerialNumber serialNumbers= repository.getBySerialNumber(saleDetail.getSerialNumber());
       if (serialNumbers==null){
           throw new NoSuchElementException("Given serial number "+saleDetail.getSerialNumber()+" not present");
       }if (saleDetail.getQuantity()!=1){
           throw new BadQuantityAssignException("set quantity value as 1");
        }
       int stockId=stockRepository.getStockId(saleDetail.getProductModelID());
       if (stockId==serialNumbers.getStockId()){
           return saleDetailRepository.addSaleDetail(
                   saleDetail.getSaleId(),
                   saleDetail.getProductModelID(),
                   saleDetail.getSerialNumber(),
                   saleDetail.getQuantity(),
                   saleDetail.getDiscount()

           );
       }throw new NoSuchElementException("invalid serial number");

    }
    return saleDetailRepository.addSaleWithoutSerial(
            saleDetail.getSaleId(),
            saleDetail.getProductModelID(),
            saleDetail.getQuantity(),
            saleDetail.getDiscount()
            ); }
    throw new NoSuchElementException("Product not available");
}
public void deleteSaleDetailBySale(int saleId){
    SaleHeader saleHeader=saleHeaderRepository.findById(saleId).orElseThrow(
            ()->new NoSuchElementException("given sale id "+saleId+" not present")
    );
    saleDetailRepository.deleteSaleDetail(saleId);
}
public void deleteSaleById(int saleDetailId){
    SaleDetail saleDetail=saleDetailRepository.findById(saleDetailId).orElseThrow(
            ()->new NoSuchElementException("given sale detail id "+saleDetailId+" not present")

    );
    saleDetailRepository.deleteById(saleDetailId);
}
}
