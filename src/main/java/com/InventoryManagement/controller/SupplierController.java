package com.InventoryManagement.controller;

import com.InventoryManagement.entity.Customer;
import com.InventoryManagement.entity.Supplier;
import com.InventoryManagement.exception.DeleteResponse;
import com.InventoryManagement.exception.NoSuchElementException;
import com.InventoryManagement.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    private SupplierService service;
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get-supplier-id/{id}")
    public Supplier getById(@PathVariable int id){
        return service.getById(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
@GetMapping("/get-supplier-by-mobile/{mobileNumber}")
    public List<Supplier> getByMobileNumber(@PathVariable String mobileNumber){
        return service.getByMobileNumber(mobileNumber);
}
    @PreAuthorize("hasRole('ADMIN')")
@PostMapping("/add-supplier")
    public  Supplier addSupplier(@RequestBody Supplier supplier){
        return service.addSupplier(supplier);
}
    @PreAuthorize("hasRole('ADMIN')")

@GetMapping("/get-all-supplier")
    public List<Supplier>getSupplier(){
        return service.getAllSup();
}
    @PreAuthorize("hasRole('ADMIN')")
@DeleteMapping("/delete-supplier-by/{supplierId}")
    public ResponseEntity<DeleteResponse> deleteSupplier(@PathVariable int supplierId){
        try {
            service.deleteSupplierById(supplierId);
            DeleteResponse deleteResponse=new DeleteResponse("supplier detail deleted successfully", HttpStatus.OK.value());
            return new ResponseEntity<>(deleteResponse,HttpStatus.OK);
        }catch (NoSuchElementException e){
            DeleteResponse deleteResponse=new DeleteResponse(e.getMessage(),HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(deleteResponse,HttpStatus.NOT_FOUND);
        }

}
    @PreAuthorize("hasRole('ADMIN')")
@PutMapping("/update-supplier/{id}")
    public Supplier updateSupplier(@PathVariable int id,@RequestBody Supplier supplier){
        return service.updateSupplier(id,supplier);
}
}
