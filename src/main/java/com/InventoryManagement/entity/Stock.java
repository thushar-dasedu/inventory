package com.InventoryManagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "stock")
public class Stock {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "stock_id")
    private int stockId;
    @Column(name = "product_id")
    private int productId;

    @Column(name ="quantity")
    private int quantity;

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Stock(int stockId, int productId, int quantity) {
        this.stockId = stockId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Stock() {
    }

    @Override
    public String toString() {
        return "Stock{" +
                "stockId=" + stockId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
