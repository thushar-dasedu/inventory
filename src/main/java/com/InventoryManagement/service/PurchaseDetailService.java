package com.InventoryManagement.service;

import com.InventoryManagement.entity.AllPurchaseInfo;
import com.InventoryManagement.repository.PurchaseDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseDetailService {
@Autowired
    private PurchaseDetailRepository purchaseDetailRepository;
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

}
