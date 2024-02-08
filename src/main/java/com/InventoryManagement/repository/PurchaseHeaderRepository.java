package com.InventoryManagement.repository;

import com.InventoryManagement.entity.PurchaseHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseHeaderRepository extends JpaRepository<PurchaseHeader,Integer> {
}
