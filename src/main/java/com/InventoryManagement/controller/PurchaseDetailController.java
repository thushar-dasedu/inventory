package com.InventoryManagement.controller;

import com.InventoryManagement.entity.AllPurchaseInfo;
import com.InventoryManagement.entity.PurchaseDetail;
import com.InventoryManagement.entity.PurchaseModel;
import com.InventoryManagement.exception.DeleteResponse;
import com.InventoryManagement.exception.NoSuchElementException;
import com.InventoryManagement.service.PurchaseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase-detail")
public class PurchaseDetailController {
@Autowired
    private PurchaseDetailService service;

@GetMapping("/get-all-purchase")
    public List<AllPurchaseInfo> getPurchaseInfo() {
    return service.getAllPurchase();
}
@PostMapping("/add-purchase")
    public PurchaseModel addPurchase(@RequestBody PurchaseModel purchaseModel){
    return service.addPurchaseInformation(purchaseModel);
}
@DeleteMapping("/delete-by-detail-id/{id}")
    public ResponseEntity<DeleteResponse> deletePurchaseDetail(@PathVariable int id){
    try {
        service.deletePurchaseDetail(id);
        DeleteResponse deleteResponse=new DeleteResponse("purchase detail deleted success fully", HttpStatus.OK.value());
        return new ResponseEntity<>(deleteResponse,HttpStatus.OK);
    }catch (NoSuchElementException e){
        DeleteResponse deleteResponse=new DeleteResponse(e.getMessage(),HttpStatus.NOT_FOUND.value());
    return new ResponseEntity<>(deleteResponse,HttpStatus.NOT_FOUND);
    }

}
@DeleteMapping("/delete-purchase/{id}")
public ResponseEntity<DeleteResponse> deletePurchase(@PathVariable int id){
    try {
        service.deletePurchase(id);
        DeleteResponse deleteResponse=new DeleteResponse("purchase detail deleted success fully",HttpStatus.OK.value());
        return new ResponseEntity<>(deleteResponse,HttpStatus.OK);
    }catch (NoSuchElementException e){
        DeleteResponse deleteResponse=new DeleteResponse(e.getMessage(),HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(deleteResponse,HttpStatus.NOT_FOUND);
    }
}
@GetMapping("/get-purchase-by/{id}")
    public List<PurchaseDetail> getPurchase(@PathVariable int id){
    return service.getByPurchaseId(id);
}
@GetMapping("/get-purchase/{id}")
    public PurchaseDetail displayPurchaseInfo(@PathVariable int id){
    return service.getById(id);
}
@PutMapping("/update-purchase/{id}")
public PurchaseModel updatePurchase(@PathVariable int id,@RequestBody PurchaseModel purchaseModel){
    return service.updatePurchase(id,purchaseModel);
}
}
