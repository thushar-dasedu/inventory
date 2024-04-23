package com.InventoryManagement.repository;

import com.InventoryManagement.entity.PurchaseHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface  PurchaseHeaderRepository extends JpaRepository<PurchaseHeader,Integer> {
    @Query(value = "call addPurchaseHeader(:supplierId)",nativeQuery = true)
    PurchaseHeader addPurchase(@Param("supplierId")int supplierId);
}
