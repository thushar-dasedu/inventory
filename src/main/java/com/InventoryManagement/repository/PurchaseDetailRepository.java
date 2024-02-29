package com.InventoryManagement.repository;

import com.InventoryManagement.entity.PurchaseDetail;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PurchaseDetailRepository extends JpaRepository<PurchaseDetail,Integer> {
    @Query(value = "call returnPurchase()",nativeQuery = true)
    List<Object[]> getAllPurchase();



    @Query(value = "call addPurchaseDetail(:purchaseId,:productModelId,:unitPrice,:quantity,:discountRate)",nativeQuery = true)
    List<PurchaseDetail> addPurchaseDetail(
            @Param("purchaseId")int purchaseId,
            @Param("productModelId")int modelId,
            @Param("unitPrice")BigDecimal unitPrice,
            @Param("quantity")int quantity,
            @Param("discountRate")float discountRate
            );
    @Transactional
    @Modifying
    @Query(value = "insert into product_serial_number (stock_id,serial_number) values(:stockId,:serialNumber)",nativeQuery = true)
 void addSerialNumber(@Param("stockId")int stockId, @Param("serialNumber")String serialNumber);

    @Query(value = "select * from purchase_detail where purchase_id=:purchaseId",nativeQuery = true)
    List<PurchaseDetail> getByPurchaseId(@Param("purchaseId")int purchaseId);

@Transactional
@Modifying
    @Query(value = "call deletePurchase(:purchaseId)",nativeQuery = true)
    void deletePurchase(@Param("purchaseId")int purchaseId);


@Query(value = "select contain_serial_number from product_brand b inner join product_model m on m.product_id=b.product_id where m.model_id=:modelId",nativeQuery = true)
boolean getContainSerial(@Param("modelId")int modelId);


@Query(value = "call updatePurchase(:purchaseId,:supplierId,:purchaseDateTime,:purchaseDetailId,:modelId,:unitPrice,:Quantity,:discountRate,:taxRate)",nativeQuery = true)
    List<PurchaseDetail>  updatePurchaseInformation(
        @Param("purchaseId")int purchaseId,
        @Param("supplierId")int supplierId,
        @Param("purchaseDateTime")LocalDateTime purchaseDateTime,
        @Param("purchaseDetailId")int purchaseDetailId,
        @Param("modelId")int modelId,
        @Param("unitPrice")BigDecimal unitPrice,
        @Param("Quantity")int Quantity,
        @Param("discountRate")float discountRate,
        @Param("taxRate")float taxRate
        );

@Transactional
    @Modifying
    @Query(value = "call updateQuantity(:modelId,:Quantity)",nativeQuery = true)
    void updateQuantity(
            @Param("modelId")int modelId,
            @Param("Quantity")int Quantity
);
}
