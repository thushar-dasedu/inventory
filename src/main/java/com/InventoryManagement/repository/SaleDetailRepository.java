package com.InventoryManagement.repository;

import com.InventoryManagement.entity.AllSalesInformation;
import com.InventoryManagement.entity.SaleDetail;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

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

    @Query(value = "select * from sale_detail where sale_id=:saleId",nativeQuery = true)
      List<SaleDetail > findBySaleId(@Param("saleId")int saleId);

    @Query(value = "call returnSale()",nativeQuery = true)
      List<Object[]> getSale();


@Transactional
@Modifying
@Query(value = "call deleteSaleDetails(:saleId)",nativeQuery = true)
    void deleteSaleDetail(@Param("saleId")int saleId);

@Query(value = "select contain_serial_number from product_brand b inner join product_model m on m.product_id=b.product_id where m.model_id=:modelId ",nativeQuery = true)
    boolean getContainSerialNumber(@Param("modelId")int modelId);
}
