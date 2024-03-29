package com.InventoryManagement.repository;

import com.InventoryManagement.entity.ProductSerialNumber;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query(value = "select * from product_serial_number where serial_number=:serialNumber",nativeQuery = true)
    public ProductSerialNumber getBySerialNumber(@Param("serialNumber")String serialNumber);

    @Query(value = " select count(serial_number) from product_serial_number where stock_id=:stockId",nativeQuery = true)
    public int getCountSerialNumber(@Param("stockId") int stockId);

    @Transactional
    @Modifying
    @Query(value = "delete from product_serial_number where serial_number=:serialNumber",nativeQuery = true)
    void deleteBySerialNumber(@Param("serialNumber")String serialNumber);

    @Query(value = "select p.contain_serial_number from product_brand p inner join stock s on p.product_id=s.product_id where s.stock_id=:stockId",nativeQuery = true)
boolean getSerialCheck(@Param("stockId")int stockId);
}
