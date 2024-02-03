package com.InventoryManagement.service;

import com.InventoryManagement.entity.Customer;
import com.InventoryManagement.exception.MobileNumberAndEmailExistsException;
import com.InventoryManagement.exception.MobileNumberNotFoundException;
import com.InventoryManagement.exception.NoSuchElementException;
import com.InventoryManagement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerService {
@Autowired
    private CustomerRepository customerRepository;

public Customer getById(int  id){
    return customerRepository.findById(id).orElseThrow(()->new NoSuchElementException("NO CUSTOMER PRESENT WITH ID "+id));
}
public Customer addCustomer(Customer customer) {

    List<Customer> cust=customerRepository.getCustomerByMobileNumber(customer.getMobileNumber());
    if (cust.isEmpty()){
       return customerRepository.save(customer);
    }else {
        throw new MobileNumberAndEmailExistsException("given mobile number and email already exists");
    }


}
public List<Customer> getByMobileNumber(String mobileNumber){

    List<Customer> cust= customerRepository.getCustomerByMobileNumber(mobileNumber);
if (cust.isEmpty()){
    throw new MobileNumberNotFoundException("Given mobile number :"+mobileNumber+" not preent");
}
return cust;
}
public List<Customer> getAllCustomer(){
    return customerRepository.findAll();
}
public void deleteCustomerById(int customerId) {
    Customer customer=customerRepository.findById(customerId) .orElseThrow(
            ()-> new NoSuchElementException("Given customer id "+customerId+" not present")
    ) ;customerRepository.deleteById(customerId);

}
public Customer updateCustomerById(int customerId,Customer customer){
   Customer customer1=customerRepository.findById(customerId).orElseThrow(()->new NoSuchElementException("Given customer id "+customerId+" not present"));
   if (customer1.getCustomerId()==customerId){
       customer1.setCustomerName(customer.getCustomerName());
       customer1.setCustomerAddress(customer.getCustomerAddress());
       customer1.setMobileNumber(customer.getMobileNumber());
       customer1.setEmail(customer.getEmail());

   }return customerRepository.save(customer1);
}
}
