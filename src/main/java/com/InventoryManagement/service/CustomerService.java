package com.InventoryManagement.service;
//
//import com.InventoryManagement.entity.Customer;
//import com.InventoryManagement.entity.Login;
//import com.InventoryManagement.entity.Role;
//import com.InventoryManagement.exception.LoginMessage;
//import com.InventoryManagement.exception.MobileNumberAndEmailExistsException;
//import com.InventoryManagement.exception.MobileNumberNotFoundException;
//import com.InventoryManagement.exception.NoSuchElementException;
//import com.InventoryManagement.repository.CustomerRepository;
//import com.InventoryManagement.repository.RoleRepository;
//import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//
//
//@Service
//public class CustomerService {
//    @Autowired
//    RoleRepository roleRepository;
//@Autowired
//    private CustomerRepository customerRepository;
//@Autowired
//private PasswordEncoder passwordEncoder;
//
//public Customer getById(int  id){
//    return customerRepository.findById(id).orElseThrow(()->new NoSuchElementException("NO CUSTOMER PRESENT WITH ID "+id));
//}
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
//
//    public void initRoleAndUser() {
//
//        Role adminRole = new Role();
//        adminRole.setName("ADMIN");
//        adminRole.setRoleDiscription("Admin role");
//        roleRepository.save(adminRole);
//
//        Role userRole = new Role();
//        userRole.setName("USER");
//        userRole.setRoleDiscription("Default role for newly created record");
//        roleRepository.save(userRole);
//
//       Customer adminUser = new Customer();
//        adminUser.setCustomerName("admin123");
//        adminUser.setPassword(this.passwordEncoder.encode("admin@pass"));
//        adminUser.setCustomerAddress("admin");
//        adminUser.setEmail("admin@gmail.com");
//        Set<Role> adminRoles = new HashSet<>();
//        adminRoles.add(adminRole);
//        adminUser.setRoles(adminRoles);
//        customerRepository.save(adminUser);

//        User user = new User();
//        user.setUserName("raj123");
//        user.setUserPassword(getEncodedPassword("raj@123"));
//        user.setUserFirstName("raj");
//        user.setUserLastName("sharma");
//        Set<Role> userRoles = new HashSet<>();
//        userRoles.add(userRole);
//        user.setRole(userRoles);
//        userDao.save(user);
//    }
//public List<Customer> getByMobileNumber(String mobileNumber){
//
//    List<Customer> cust= customerRepository.getCustomerByMobileNumber(mobileNumber);
//if (cust.isEmpty()){
//    throw new MobileNumberNotFoundException("Given mobile number :"+mobileNumber+" not preent");
//}
//return cust;
//}
//public List<Customer> getAllCustomer(){
//    return customerRepository.findAll();
//}
//public void deleteCustomerById(int customerId) {
//    Customer customer=customerRepository.findById(customerId) .orElseThrow(
//            ()-> new NoSuchElementException("Given customer id "+customerId+" not present")
//    ) ;customerRepository.deleteById(customerId);
//
//}
//public Customer updateCustomerById(int customerId,Customer customer){
//   Customer customer1=customerRepository.findById(customerId).orElseThrow(()->new NoSuchElementException("Given customer id "+customerId+" not present"));
//   if (customer1.getCustomerId()==customerId){
//       customer1.setCustomerName(customer.getCustomerName());
//       customer1.setCustomerAddress(customer.getCustomerAddress());
//       customer1.setMobileNumber(customer.getMobileNumber());
//       customer1.setEmail(customer.getEmail());
//
//   }return customerRepository.save(customer1);
//}
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


//}
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.InventoryManagement.entity.Customer;
import com.InventoryManagement.entity.Role;
import com.InventoryManagement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class CustomerService implements UserDetailsService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private CustomerRepository userDao;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    // Load user by username
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer user = userDao.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    // Get user authorities
    private Set<SimpleGrantedAuthority> getAuthority(Customer user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }

    // Find all users
    public List<Customer> findAll() {
        List<Customer> list = new ArrayList<>();
        userDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    // Find user by username

    public Customer findOne(String email) {
        return userDao.findByEmail(email);
    }

    // Save user

    public Customer save(Customer user) {


        user.setPassword(bcryptEncoder.encode(user.getPassword()));

        // Set default role as USER
        Role role = roleService.findByName("USER");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);

        // If email domain is admin.edu, add ADMIN role
        if(user.getEmail().split("@")[1].equals("admin.edu")){
            role = roleService.findByName("ADMIN");
            roleSet.add(role);
        }

        user.setRoles(roleSet);
        return userDao.save(user);
    }


    public Customer createEmployee(Customer user) {
        Customer nUser = user;
        nUser.setPassword(bcryptEncoder.encode(user.getPassword()));

        Role employeeRole = roleService.findByName("ADMIN");
        Role customerRole = roleService.findByName("USER");

        Set<Role> roleSet = new HashSet<>();
        if (employeeRole != null) {
            roleSet.add(employeeRole);
        }
        if (customerRole != null) {
            roleSet.add(customerRole);
        }

        nUser.setRoles(roleSet);
        return userDao.save(nUser);
    }
}