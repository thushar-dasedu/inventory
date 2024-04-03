package com.InventoryManagement.repository;

import com.InventoryManagement.entity.FileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileDataRepository extends JpaRepository<FileData,Integer> {
    Optional<FileData> findByName(String fileName);


    @Query(value = "select p.model_id,p.product_id,p.product_model_name,p.unit_price,p.tax,p.quantity,f.id," +
            "f.name,f.type,f.file_path from ((product_model p inner join product_image i on p.model_id=i.model_id)" +
            "inner join file_data f on f.id=i.image_id)",nativeQuery = true)

    List<Object[]> getProductInfo();

}
