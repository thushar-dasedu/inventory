package com.InventoryManagement.service;

import com.InventoryManagement.entity.Customer;
import com.InventoryManagement.entity.Login;
import com.InventoryManagement.exception.LoginMessage;
import com.InventoryManagement.exception.MobileNumberAndEmailExistsException;
import com.InventoryManagement.exception.MobileNumberNotFoundException;
import com.InventoryManagement.exception.NoSuchElementException;
import com.InventoryManagement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerService {
@Autowired
    private CustomerRepository customerRepository;
//@Autowired
//private PasswordEncoder passwordEncoder;

public Customer getById(int  id){
    return customerRepository.findById(id).orElseThrow(()->new NoSuchElementException("NO CUSTOMER PRESENT WITH ID "+id));
}
//public Customer addCustomer(Customer customer) {
//
//    List<Customer> cust=customerRepository.getCustomerByMobileNumber(customer.getMobileNumber());
//    if (cust.isEmpty()){
//        Customer customer1=new Customer(customer.getCustomerId(),customer.getCustomerName(),customer.getCustomerAddress(),customer.getMobileNumber(),customer.getEmail(),this.passwordEncoder.encode(customer.getPassword()));
//
//       return customerRepository.save(customer1);
//    }else {
//        throw new MobileNumberAndEmailExistsException("given mobile number and email already exists");
//    }
//
//
//}
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
//    public LoginMessage loginEmployee(Login loginDTO) {
//        String msg = "";
//        Optional<Customer> employee1 = customerRepository.findByEmail(loginDTO.getEmail());
//        if (employee1.isPresent()) {
//            String password = loginDTO.getPassword();
//            String encodedPassword = employee1.get().getPassword();
//            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
//            if (isPwdRight) {
//                Optional<Customer> employee = customerRepository.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
//                if (employee.isPresent()) {
//                    return new LoginMessage("Login Success", true);
//                } else {
//                    return new LoginMessage("Login Failed", false);
//                }
//            } else {
//                return new LoginMessage("Password Not Match", false);
//            }
//        } else {
//            return new LoginMessage("Email not exists", false);
//        }
//    }

}
