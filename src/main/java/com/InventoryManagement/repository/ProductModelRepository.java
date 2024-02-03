package com.InventoryManagement.repository;

import com.InventoryManagement.entity.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductModelRepository extends JpaRepository<ProductModel,Integer> {
@Query(value = "select * from product_model where product_model_name=:productName",nativeQuery = true)
List<ProductModel> getModelByProductModel(@Param("productName")String productName);

@Query(value = "call addProductModelDetail(:productId,:productModel,:unitPrice,:Tax,:Quantity)",nativeQuery = true)
    public ProductModel addProductModel(@Param("productId")int productId, @Param("productModel")String productModel
, @Param("unitPrice")BigDecimal unitPrice,@Param("Tax")float Tax,@Param("Quantity")int Quantity);
}
