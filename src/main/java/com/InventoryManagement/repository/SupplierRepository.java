package com.InventoryManagement.repository;

import com.InventoryManagement.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Integer> {
    @Query(value = "select * from supplier where mobile_number=:mobileNumber",nativeQuery = true)
    List<Supplier> getSupplierByMobile(String mobileNumber);
}
