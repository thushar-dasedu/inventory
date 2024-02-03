package com.InventoryManagement.repository;

import com.InventoryManagement.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface StockRepository extends JpaRepository<Stock,Integer> {
    @Query(value = "call StockInsertionByProductId(:productId)",nativeQuery = true)
    public Stock addStockByProductId(@Param("productId")int productId);

    @Query(value = "select * from stock where product_id=:productId",nativeQuery = true)
    public Stock getStockByProductId(@Param("productId")int productId);

}
