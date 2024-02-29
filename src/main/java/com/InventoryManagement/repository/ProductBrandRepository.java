package com.InventoryManagement.repository;

import com.InventoryManagement.entity.ProductBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductBrandRepository extends JpaRepository<ProductBrand,Integer> {
    @Query(value = "select * from product_brand where brand_name=:name",nativeQuery = true)
    public List<ProductBrand> getProductByName(String name);

    @Query(value = "select b.contain_serial_number from product_brand b inner join product_model m on b.product_id =m.product_id where m.model_id=:modelId",nativeQuery = true)
    boolean containSerialNumber(@Param("modelId")int modelId);
}
