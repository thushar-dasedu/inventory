package com.InventoryManagement.service;

import com.InventoryManagement.entity.ProductSerialNumber;
import com.InventoryManagement.entity.Stock;
import com.InventoryManagement.exception.BadRequestException;
import com.InventoryManagement.exception.ElementAlreadyExistsException;
import com.InventoryManagement.exception.IllegalArgumentException;
import com.InventoryManagement.exception.NoSuchElementException;
import com.InventoryManagement.repository.ProductSerialNumberRepository;
import com.InventoryManagement.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProductSerialNumberService {
    @Autowired
    private ProductSerialNumberRepository productSerialNumberRepository;
    @Autowired
    private StockRepository stockRepository;


    public List<ProductSerialNumber> getAllSerialNumber(){
        return productSerialNumberRepository.findAll();
    }
    public ProductSerialNumber getById(int id){
return productSerialNumberRepository.findById(id).orElseThrow(
        ()->new NoSuchElementException("Given seial id "+id+" not present")
);
    }
    public List<ProductSerialNumber> getSerialByStock(int stockId){
        Stock stock=stockRepository.findById(stockId).orElseThrow(
                ()-> new NoSuchElementException("Given stock id "+stockId+" not present")
        );
        List<ProductSerialNumber> serialNumbers=productSerialNumberRepository.getByStockId(stockId);
        if (serialNumbers.isEmpty()){
            throw new NoSuchElementException("Stocks are not present");
        }return serialNumbers;
    }

    public ProductSerialNumber addSerial(ProductSerialNumber serialNumber) {
         if (serialNumber == null || serialNumber.getStockId() <= 0 || serialNumber.getSerialNumber() == null) {
            throw new IllegalArgumentException("Invalid serial number object");
        }

         if ( productSerialNumberRepository.getSerialCheck(serialNumber.getStockId())){


        // Check if the stock exists
        Stock stock = stockRepository.findById(serialNumber.getStockId())
                .orElseThrow(() -> new NoSuchElementException("Given stock not present"));

        // Check the count of serial numbers against stock quantity
        int stockCount = productSerialNumberRepository.getCountSerialNumber(serialNumber.getStockId());
        if (stockCount >= stock.getQuantity()) {
            throw new BadRequestException("Cannot add serial number, stock quantity reached");
        }

        // Check if the serial number already exists
        ProductSerialNumber existingSerial = productSerialNumberRepository.getBySerialNumber(serialNumber.getSerialNumber());
        if (existingSerial != null) {
            throw new ElementAlreadyExistsException("Given serial number " + serialNumber.getSerialNumber() + " already exists");
        }

        // Call the stored procedure to get the count of serial numbers
        int countSerial = productSerialNumberRepository.getCountSerialNumber(serialNumber.getStockId());

        // Add the serial number if count is within limit
        if (countSerial < stock.getQuantity()) {
            return productSerialNumberRepository.addSerialNumber(serialNumber.getStockId(), serialNumber.getSerialNumber());
        } else {
            throw new BadRequestException("Cannot add serial number, stock quantity reached");
        }  }throw new BadRequestException("You cant set serial number to given stock id "+serialNumber.getStockId());
    }

    public void deleteSerialNumber(String serialNumber){
        ProductSerialNumber serial=productSerialNumberRepository.getBySerialNumber(serialNumber);
        if (  serial ==null){
            throw new NoSuchElementException("Given product serial number "+serialNumber+" not present");
        }productSerialNumberRepository.deleteBySerialNumber(serialNumber);

    }
    public ProductSerialNumber getBySerial(String serialNumber){
        ProductSerialNumber productSerialNumber= productSerialNumberRepository.getBySerialNumber(serialNumber);
        if (productSerialNumber==null){
            throw new NoSuchElementException("given serial number "+serialNumber+" not present");
        }return productSerialNumber;

    }
}
