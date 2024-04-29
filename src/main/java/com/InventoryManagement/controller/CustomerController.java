package com.InventoryManagement.controller;

import com.InventoryManagement.config.TokenProvider;
import com.InventoryManagement.entity.AuthToken;
import com.InventoryManagement.entity.Customer;
import com.InventoryManagement.entity.Login;
import com.InventoryManagement.entity.LoginUser;
import com.InventoryManagement.exception.DeleteResponse;
import com.InventoryManagement.exception.LoginMessage;
import com.InventoryManagement.service.CustomerService;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.AuthenticationException;


import javax.annotation.PostConstruct;
import java.util.List;
import java.util.NoSuchElementException;
//@CrossOrigin("http://localhost:3000")
//@RestController
//@RequestMapping("/customer")
//public class   CustomerController {
//    @Autowired
//    private CustomerService service;
//    @PostMapping("/add-customer")
//    public Customer addCustomer(@RequestBody Customer customer){
//        return service.addCustomer(customer);
//    }

//
//    @PostConstruct
//    public void initRoleAndUser() {
//        service.initRoleAndUser();
//    }
//
//    @GetMapping("/get-mobile-number/{mobileNumber}")
//    public List<Customer> getCustomer(@PathVariable String mobileNumber){
//        return service.getByMobileNumber(mobileNumber);
//    }
//
//    @GetMapping("/get-all-customer")
//    public  List<Customer> getAllCustomer(){
//        return service.getAllCustomer();
//    }
//    @GetMapping("/get-customer-by-id/{id}")
//    public Customer getCustomerById(@PathVariable int id){
//        return service.getById(id);
//    }
//    @DeleteMapping("/delete-customer-by/{id}")
//    public ResponseEntity<DeleteResponse> deleteCustomer(@PathVariable int id){
//        try {
//            service.deleteCustomerById(id);
//            DeleteResponse deleteResponse=new DeleteResponse("customer detail deleted",HttpStatus.OK.value());
//            return new ResponseEntity<>(deleteResponse ,HttpStatus.OK);
//        }catch (NoSuchElementException e){
//            DeleteResponse deleteResponse=new DeleteResponse(e.getMessage(),HttpStatus.NOT_FOUND.value());
//            return new ResponseEntity<>( deleteResponse,HttpStatus.NOT_FOUND);
//        }
//
//
//    }
//    @PutMapping("/update-customer-by/{id}")
//    public Customer updateCustomer(@PathVariable int id,@RequestBody Customer customer){
//        return service.updateCustomerById(id,customer);
//    }

//    @PostMapping("/login")
//    public ResponseEntity<?> loginCustomer(@RequestBody Login loginDto){
//        LoginMessage loginMessage =service.loginEmployee(loginDto);
//        return ResponseEntity.ok(loginMessage);
//
//    }

//}
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private CustomerService userService;

    /**
     * Generates a token for the given user credentials.
     *
     * @param loginUser The user's login credentials.
     * @return A response entity containing the generated token.
     * @throws AuthenticationException if authentication fails.
     */
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody LoginUser loginUser) throws AuthenticationException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getEmail(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return ResponseEntity.ok(new AuthToken(token));
    }

    /**
     * Saves a new user.
     *
     * @param user The user to be saved.
     * @return The saved user.
     */
    @RequestMapping(value="/register", method = RequestMethod.POST)
    public Customer saveUser(@RequestBody Customer user){
        return userService.save(user);
    }

    /**
     * Returns a message that can only be accessed by users with the 'ADMIN' role.
     *
     * @return A message that can only be accessed by admins.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/adminping", method = RequestMethod.GET)
    public String adminPing(){
        return "Only Admins Can Read This";
    }

    /**
     * Returns a message that can be accessed by any user.
     *
     * @return A message that can be accessed by any user.
     */
    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value="/userping", method = RequestMethod.GET)
    public String userPing(){
        return "Any User Can Read This";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/create/employee", method = RequestMethod.POST)
    public Customer createEmployee(@RequestBody Customer user){
        return userService.createEmployee(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value=" ", method = RequestMethod.GET)
    public List<Customer> getAllList(){
        return userService.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/find/by/username", method = RequestMethod.GET)
    public Customer getAllList(@RequestParam String username){
        return userService.findOne(username);
    }
}