package com.InventoryManagement.service;

import com.InventoryManagement.entity.ProductBrand;
import com.InventoryManagement.entity.Stock;
import com.InventoryManagement.exception.ElementAlreadyExistsException;
import com.InventoryManagement.exception.NoSuchElementException;
import com.InventoryManagement.repository.ProductBrandRepository;
import com.InventoryManagement.repository.ProductModelRepository;
import com.InventoryManagement.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class StockService {
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private ProductBrandRepository productBrandRepository;

    public List<Stock> getAllStock(){
        return stockRepository.findAll();
    }
    public Stock getStockById(int stockId){
        return stockRepository.findById(stockId).orElseThrow(
                ()-> new NoSuchElementException("Given stock id "+stockId+" not present")
        );
    }
    public Stock addStock(Stock stock){
        ProductBrand brand=productBrandRepository.findById(stock.getProductId()).orElseThrow(
                ()-> new NoSuchElementException("Given product id "+stock.getProductId()+" not present")
        );
        Stock stock1=stockRepository.getStockByProductId(stock.getProductId());
        if( stock1==null){
            return stockRepository.addStockByProductId(stock.getProductId());
        }
       throw new ElementAlreadyExistsException("Given Already exists in stock");
    }
    public void deleteStockById(int stockId){
        Stock stock=stockRepository.findById(stockId).orElseThrow(
                ()->new NoSuchElementException("given stock id not present")
        );
        stockRepository.deleteById(stockId);
    }
}
