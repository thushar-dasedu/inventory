package com.InventoryManagement.repository;

import com.InventoryManagement.entity.PurchaseDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseDetailRepository extends JpaRepository<PurchaseDetail,Integer> {
    @Query(value = "call returnPurchase()",nativeQuery = true)
    List<Object[]> getAllPurchase();
}
