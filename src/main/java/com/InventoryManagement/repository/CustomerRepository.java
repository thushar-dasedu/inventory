package com.InventoryManagement.repository;

import com.InventoryManagement.entity.Customer;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    @Query(value = "select * from customer where mobile_number=:mobileNumber",nativeQuery = true)
    List<Customer> getCustomerByMobileNumber(String mobileNumber);

    @Query(value = "delete from customer where customer_id=:mobileNumber",nativeQuery = true)
    public void deleteCustomerByMobileNumber(@Param("mobileNumber")String mobileNumber);

    Optional<Customer> findOneByEmailAndPassword(String email, String password);
//   Optional< Customer> findByEmail(String email);

   Customer findByEmail(String email);
}
