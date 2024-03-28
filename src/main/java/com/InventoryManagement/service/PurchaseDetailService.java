package com.InventoryManagement.service;

import com.InventoryManagement.entity.*;
import com.InventoryManagement.exception.ElementAlreadyExistsException;
import com.InventoryManagement.exception.IllegalArgumentException;
import com.InventoryManagement.exception.NoSuchElementException;
import com.InventoryManagement.exception.NotNullException;
import com.InventoryManagement.repository.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PurchaseDetailService {
@Autowired
    private PurchaseDetailRepository purchaseDetailRepository;
@Autowired
private SupplierRepository supplierRepository;
@Autowired
private ProductModelRepository productModelRepository;
@Autowired
private PurchaseHeaderRepository purchaseHeaderRepository;
@Autowired
private ProductSerialNumberRepository productSerialNumberRepository;

@Autowired
private StockRepository stockRepository;

public List<AllPurchaseInfo> getAllPurchase(){
    List<Object[]> result=purchaseDetailRepository.getAllPurchase();
    List<AllPurchaseInfo> allPurchaseInfo=new ArrayList<>();
    for (Object[] objects:result){
        AllPurchaseInfo purchaseInfo=new AllPurchaseInfo();
        purchaseInfo.setPurchaseDetailId((int) objects[0]);
        purchaseInfo.setPurchaseId((int) objects[1]);
        purchaseInfo.setSupplierName((String) objects[2]);
        Timestamp timestamp=(Timestamp) objects[3];
        LocalDateTime localDateTime=timestamp.toLocalDateTime();
        purchaseInfo.setPurchaseDatetime(localDateTime);

        purchaseInfo.setProductModelName((String) objects[4]);
        purchaseInfo.setUnitPrice((BigDecimal) objects[5]);
        purchaseInfo.setQuantity((int) objects[6]);
        purchaseInfo.setDiscountAmount((BigDecimal) objects[7]);
        purchaseInfo.setNetAmount((BigDecimal) objects[8]);
        purchaseInfo.setTaxAmount((BigDecimal) objects[9]);

        allPurchaseInfo.add(purchaseInfo);

    }return allPurchaseInfo;
}
 @Transactional
    public PurchaseModel addPurchaseInformation(PurchaseModel purchaseModel) {
         int supplierId = purchaseModel.getSupplierId();
        List<PurchaseDetailModel> purchaseDetailModels = purchaseModel.getPurchaseDetailModels();

         Supplier supplier = supplierRepository.findById(supplierId).orElseThrow(
                () -> new NoSuchElementException("Given supplier id " + supplierId + " not present")
        );

         PurchaseHeader purchaseHeader = purchaseHeaderRepository.addPurchase(supplierId);


         for (PurchaseDetailModel detail : purchaseDetailModels) {
             List<String> serialNumber = detail.getSerialNumbers();
             int quantity=detail.getQuantity();
             productModelRepository.findById(detail.getModelId()).orElseThrow(
                    () -> new NoSuchElementException("Given product model id " + detail.getModelId() + " not present")
            );


             if (purchaseDetailRepository.



                     getContainSerial(detail.getModelId())) {
                 purchaseDetailRepository.addPurchaseDetail(
                        purchaseHeader.getPurchaseId(),

                        detail.getModelId(),
                        detail.getUnitPrice(),
                        detail.getQuantity(),
                        detail.getDiscount()
                );
                 if (serialNumber.isEmpty()){
                     throw new NotNullException("please set serial number  ");
                 }


                 for (String serialNumber1 : serialNumber) {
                     if (serialNumber.size()!=quantity){
                         throw new IllegalArgumentException("Please set " + quantity + " serial numbers for product with model id " + detail.getModelId());
                     }

                     int stockId = stockRepository.getStockId(detail.getModelId());
                    String serial = serialNumber1;
                   ProductSerialNumber productSerialNumber= productSerialNumberRepository.getBySerialNumber(serial);
                   if (productSerialNumber ==null){
                        purchaseDetailRepository.addSerialNumber(stockId, serial);

                   }
                   else  throw new ElementAlreadyExistsException("Given serial number "+serial+" already exists");
                 }
            } else {
                 purchaseDetailRepository.addPurchaseDetail(
                        purchaseHeader.getPurchaseId(),
                        detail.getModelId(),
                        detail.getUnitPrice(),
                        detail.getQuantity(),
                        detail.getDiscount()
                );
            }


        }
        PurchaseHeader header=purchaseHeaderRepository.findById(purchaseHeader.getPurchaseId()).get();
        List<PurchaseDetail> getPurchase=purchaseDetailRepository.getByPurchaseId(purchaseHeader.getPurchaseId());
        List<PurchaseDetailModel> purchaseDetailMode = purchaseModel.getPurchaseDetailModels();

        List<PurchaseDetailModel> purchaseDetailModel=new ArrayList<>();
        List<String> serialNumber=new ArrayList<>();
        for (PurchaseDetailModel purchaseDetailInfo:purchaseDetailMode){
           for (String string:purchaseDetailInfo.getSerialNumbers()) {
               serialNumber.add(string);
           }



    }
        for (PurchaseDetail purchaseDetail:getPurchase){
            PurchaseDetailModel purchaseDetailModel1=new PurchaseDetailModel();
            purchaseDetailModel1.setPurchaseDetailId(purchaseDetail.getPurchaseDetailId());
            purchaseDetailModel1.setPurchaseId(purchaseDetail.getPurchaseId());
            purchaseDetailModel1.setModelId(purchaseDetail.getModelId());
            purchaseDetailModel1.setUnitPrice(purchaseDetail.getUnitPrice());
            purchaseDetailModel1.setQuantity(purchaseDetail.getQuantity());
            purchaseDetailModel1.setSerialNumbers(serialNumber);
            purchaseDetailModel1.setDiscount(purchaseDetail.getDiscount());
            purchaseDetailModel1.setDiscountAmount(purchaseDetail.getDiscountAmount());
            purchaseDetailModel1.setNetAmount(purchaseDetail.getNetAmount());
            purchaseDetailModel1.setTaxRate(purchaseDetail.getTaxRate());
            purchaseDetailModel1.setTaxAmount(purchaseDetail.getTaxAmount());
            purchaseDetailModel.add(purchaseDetailModel1);
        }



        purchaseModel.setPurchaseId(header.getPurchaseId());
        purchaseModel.setSupplierId(header.getSupplierId());
        purchaseModel.setPurchaseDateTime(header.getPurchaseDateTime());
        purchaseModel.setPurchaseDetailModels(purchaseDetailModel);

        return purchaseModel;
    }

    public void deletePurchaseDetail(int purchaseDetailId){
     purchaseDetailRepository.findById(purchaseDetailId).orElseThrow(
             ()->new NoSuchElementException("Given purchase detail id "+purchaseDetailId+" not present")

     );
     purchaseDetailRepository.deleteById(purchaseDetailId);

    }


    public void deletePurchase(int purchaseId){

    purchaseHeaderRepository.findById(purchaseId).orElseThrow(
            ()->new NoSuchElementException("Given purchase id "+purchaseId+" not present")
    );
    purchaseDetailRepository.deletePurchase(purchaseId);
    }
    public List<PurchaseDetail> getByPurchaseId(int purchaseId){
    purchaseHeaderRepository.findById(purchaseId).orElseThrow(
            ()->new NoSuchElementException("Given purchase id "+purchaseId+" not present")
    );
      return purchaseDetailRepository.getByPurchaseId(purchaseId);
    }
    public PurchaseDetail getById(int purchaseDetailId){
    return purchaseDetailRepository.findById(purchaseDetailId).orElseThrow(
            ()->new NoSuchElementException("Given purchase detail id "+purchaseDetailId+" not present")
    );
    }
    @Transactional

    public PurchaseModel updatePurchase(int  purchaseId, PurchaseModel purchaseModel){
    purchaseHeaderRepository.findById(purchaseId).orElseThrow(
            ()->new NoSuchElementException("Given purchase id "+purchaseId+" not present")
    );
    int purchaseId1=purchaseModel.getPurchaseId();
    int supplierId=purchaseModel.getSupplierId();
    LocalDateTime purchaseDateTime=purchaseModel.getPurchaseDateTime();


   List<PurchaseDetailModel> purchaseDetailInfo =new ArrayList<>();
    List<PurchaseDetailModel> purchaseDetailModels=purchaseModel.getPurchaseDetailModels();
    for (PurchaseDetailModel purchaseDetailModel:purchaseDetailModels){
      int purchaseDetailId=purchaseDetailModel.getPurchaseDetailId();
      int modelId= purchaseDetailModel.getModelId();
      BigDecimal unitPrice= purchaseDetailModel.getUnitPrice();
      int quantity= purchaseDetailModel.getQuantity();
      float discountRate= purchaseDetailModel.getDiscount();
      float taxRate= purchaseDetailModel.getTaxRate();
      PurchaseDetail purchaseDetail=purchaseDetailRepository.findById(purchaseDetailId).get();
        int newQuantity=(quantity-purchaseDetail.getQuantity());

       List<PurchaseDetail> purchaseDetails1=purchaseDetailRepository.updatePurchaseInformation(purchaseId1,supplierId,purchaseDateTime,purchaseDetailId,modelId,unitPrice,quantity,discountRate,taxRate);
       for (PurchaseDetail purchaseDetail1:purchaseDetails1){
           PurchaseDetailModel purchaseDetailModel1=new PurchaseDetailModel();
           purchaseDetailModel1.setPurchaseDetailId(purchaseDetail1.getPurchaseDetailId());
           purchaseDetailModel1.setPurchaseId(purchaseDetail1.getPurchaseId());
           purchaseDetailModel1.setModelId(purchaseDetail1.getModelId());
           purchaseDetailModel1.setUnitPrice(purchaseDetail1.getUnitPrice());
           purchaseDetailModel1.setQuantity(purchaseDetail1.getQuantity());
           purchaseDetailModel1.setDiscount(purchaseDetail1.getDiscount());
           purchaseDetailModel1.setDiscountAmount(purchaseDetail1.getDiscountAmount());
           purchaseDetailModel1.setNetAmount(purchaseDetail1.getNetAmount());
           purchaseDetailModel1.setTaxRate(purchaseDetail1.getTaxRate());
           purchaseDetailModel1.setTaxAmount(purchaseDetail1.getTaxAmount());
           purchaseDetailInfo.add(purchaseDetailModel1);
       }
        purchaseDetailRepository.updateQuantity(modelId,newQuantity);

    } purchaseModel.setPurchaseId(purchaseId1);
        purchaseModel.setSupplierId(supplierId);
        purchaseModel.setPurchaseDateTime(purchaseDateTime);
         purchaseModel.setPurchaseDetailModels(purchaseDetailInfo);
        return purchaseModel;}

    }