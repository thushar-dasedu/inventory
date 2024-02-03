package com.InventoryManagement.repository;

import com.InventoryManagement.entity.ProductSerialNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSerialNumberRepository extends JpaRepository<ProductSerialNumber,Integer> {
    @Query(value = "select * from product_serial_number where stock_id=:stockId",nativeQuery = true)
    List<ProductSerialNumber> getByStockId(@Param("stockId")int stockId);

    @Query(value = "call addSerialNumber(:stockId,:serialNumber)",nativeQuery = true)
    public ProductSerialNumber addSerialNumber(@Param("stockId")int stockId,@Param("serialNumber")String serialNumber);
}