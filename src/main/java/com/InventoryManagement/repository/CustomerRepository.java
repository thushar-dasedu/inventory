package com.InventoryManagement.repository;

import com.InventoryManagement.entity.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    @Query(value = "select * from customer where mobile_number=:mobileNumber",nativeQuery = true)
    List<Customer> getCustomerByMobileNumber(String mobileNumber);

    @Query(value = "delete from customer where customer_id=:mobileNumber",nativeQuery = true)
    public void deleteCustomerByMobileNumber(@Param("mobileNumber")String mobileNumber);


}
