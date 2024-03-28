//package com.InventoryManagement.security;
//
//import com.InventoryManagement.entity.Customer;
//import com.InventoryManagement.repository.CustomerRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomUserDetailService implements UserDetailsService {
//    @Autowired
//    private CustomerRepository customerRepository;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//    Customer customer= customerRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("Usr not found"));
//
//        return customer;
//    }
//}
