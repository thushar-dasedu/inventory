package com.InventoryManagement.repository;

import com.InventoryManagement.entity.SaleHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface SaleHeaderRepository extends JpaRepository<SaleHeader,Integer> {
    @Query(value = "select * from sale_header where sale_date_time=:saleDate",nativeQuery = true)
    List<SaleHeader> getSaleByDate(@Param("saleDate")LocalDateTime localDateTime);

    @Query(value = "call addSaleHeader(:customerId)",nativeQuery = true)
    public SaleHeader addSale(@Param("customerId")int customerId );
}
