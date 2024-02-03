package com.InventoryManagement.controller;

import com.InventoryManagement.entity.Customer;
import com.InventoryManagement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService service;
    @PostMapping("/add-customer")
    public Customer addCustomer(@RequestBody Customer customer){
        return service.addCustomer(customer);
    }

    @GetMapping("/get-mobile-number/{mobileNumber}")
    public List<Customer> getCustomer(@PathVariable String mobileNumber){
        return service.getByMobileNumber(mobileNumber);
    }

    @GetMapping("/get-all-customer")
    public  List<Customer> getAllCustomer(){
        return service.getAllCustomer();
    }
    @GetMapping("/get-customer-by-id/{id}")
    public Customer getCustomerById(@PathVariable int id){
        return service.getById(id);
    }
    @DeleteMapping("/delete-customer-by/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable int id){
        try {
            service.deleteCustomerById(id);
            return new ResponseEntity<>("customer detail deleted " ,HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }


    }
    @PutMapping("/update-customer-by/{id}")
    public Customer updateCustomer(@PathVariable int id,@RequestBody Customer customer){
        return service.updateCustomerById(id,customer);
    }

}
