package com.InventoryManagement.service;

import com.InventoryManagement.entity.*;
import com.InventoryManagement.exception.BadRequestException;
import com.InventoryManagement.exception.NoSuchElementException;
import com.InventoryManagement.exception.NotNullException;
import com.InventoryManagement.repository.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
@Autowired
private CustomerRepository customerRepository;


public List<SaleDetail> getSaleDetail(){
    return saleDetailRepository.findAll();
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

@Transactional
    public SaleModel addSaleDetail(SaleModel saleModel) {

        int customerId = saleModel.getCustomerId();
        List<SaleDetail > saleDetails = saleModel.getSaleDetailModels();
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new NoSuchElementException("Given customer id " + customerId + " not present")
        );

        SaleHeader header = saleHeaderRepository.addSale(customerId);
        for (SaleDetail saleDetail : saleDetails) {
            // Check if ProductModel exists
            ProductModel productModel = productModelRepository.findById(saleDetail.getProductModelID())
                    .orElseThrow(() -> new NoSuchElementException("Given product model id " + saleDetail.getProductModelID()+ " not present"));

            if (productModel.getQuantity() >= saleDetail.getQuantity() &&(productModel.getQuantity()-saleDetail.getQuantity())  != 0) {

                if (saleDetailRepository.getContainSerialNumber(saleDetail.getProductModelID())) {
                    if(saleDetail.getSerialNumber()==null){
                        throw new NotNullException("Please enter product serial number");
                    }
                    ProductSerialNumber serialNumbers = repository.getBySerialNumber(saleDetail.getSerialNumber());
                    if (serialNumbers == null) {
                        throw new NoSuchElementException("Given serial number " + saleDetail.getSerialNumber() + " not present");
                    }
                    if (saleDetail.getQuantity() != 1) {
                        throw new BadRequestException("set quantity value as 1");
                    }
                    int stockId = stockRepository.getStockId(saleDetail.getProductModelID());
                    if (stockId == serialNumbers.getStockId()) {
                        saleDetailRepository.addSaleDetail(header.getSaleId(),
                                saleDetail.getProductModelID(),
                                saleDetail.getSerialNumber(),
                                saleDetail.getQuantity(),
                                saleDetail.getDiscount());
                    } else {
                        throw new NoSuchElementException("invalid serial number");
                    }
                } else {
                    saleDetailRepository.addSaleWithoutSerial(header.getSaleId(),
                            saleDetail.getProductModelID(),
                            saleDetail.getQuantity(),
                            saleDetail.getDiscount());
                }
            } else {
                throw new NoSuchElementException("Product with id "+saleDetail.getProductModelID()+" not available");
            }
        }
        SaleHeader saleHeader1=saleHeaderRepository.findById(header.getSaleId()).get();
        List<SaleDetail> savedSaleDetails = saleDetailRepository.findBySaleId(header.getSaleId());
        saleModel.setCustomerId(saleHeader1.getCustomerId());
        saleModel.setSaleDateTime(saleHeader1.getSaleDateTime());
        saleModel.setSaleId(saleHeader1.getSaleId());

         saleModel.setSaleDetailModels(savedSaleDetails);



        return   saleModel;
    }



public List<AllSalesInformation> getAllSale(){
     List<Object[]> result=saleDetailRepository.getSale();
     List<AllSalesInformation> allSalesInfo =new ArrayList<>();
     for (Object[] object:result){
         AllSalesInformation allSalesInformation=new AllSalesInformation();
         allSalesInformation.setSaleDetailId((int) object[0]);
         allSalesInformation.setSaleId((int) object[1]);
         allSalesInformation.setCustomerName((String) object[2]);
         //convert time stamp to Local datetime
         Timestamp timestamp = (Timestamp) object[3];
         LocalDateTime saleDateTime = timestamp.toLocalDateTime();
         allSalesInformation.setSaleDateTime(saleDateTime);
         allSalesInformation.setProductModelName((String) object[4]);
         allSalesInformation.setUnitPrice((BigDecimal) object[5]);
         allSalesInformation.setDiscountAmount((BigDecimal) object[6]);
         allSalesInformation.setNetAmount((BigDecimal) object[7]);
         allSalesInformation.setTaxAmount((BigDecimal) object[8]);
         allSalesInfo.add(allSalesInformation);
     }return allSalesInfo ;
}


}
