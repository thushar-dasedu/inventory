package com.InventoryManagement.repository;

import com.InventoryManagement.entity.ProductSerialNumber;
import com.InventoryManagement.entity.SaleDetail;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleDetailRepository extends JpaRepository<SaleDetail,Integer> {
    @Query(value = "call addSaleDetailInfo(:saleId,:productModel,:serialNumber,:Quantity,:Discount )", nativeQuery = true)
    List<SaleDetail> addSaleDetail(
            @Param("saleId") int saleId,
            @Param("productModel") int productModel,
            @Param("serialNumber") String serialNumber,
            @Param("Quantity") int Quantity,
            @Param("Discount") float Discount

    );
    @Query(value = "call addSaleDetailWithoutSerial(:saleId,:productId,:Quantity,:Discount )",nativeQuery = true)
    List<SaleDetail> addSaleWithoutSerial(
            @Param("saleId")int saleId,
            @Param("productId")int productId,
            @Param("Quantity")int Quantity,
            @Param("Discount")float Discount
            );
@Transactional
@Modifying
@Query(value = "call deleteSaleDetails(:saleId)",nativeQuery = true)
    void deleteSaleDetail(@Param("saleId")int saleId);
}
