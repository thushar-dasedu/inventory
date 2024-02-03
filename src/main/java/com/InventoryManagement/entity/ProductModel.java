package com.InventoryManagement.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "product_model")
public class ProductModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "model_id")
    private int modelId;
    @Column(name = "product_id")
    private int productId;
    @Column(name = "product_model_name")
    private String productModelName;
    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "tax")
    private float tax;

    @Column(name = "quantity")
    private int quantity;

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductModelName() {
        return productModelName;
    }

    public void setProductModelName(String productModelName) {
        this.productModelName = productModelName;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public float getTax() {
        return tax;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductModel(int modelId, int productId, String productModelName, BigDecimal unitPrice, float tax, int quantity) {
        this.modelId = modelId;
        this.productId = productId;
        this.productModelName = productModelName;
        this.unitPrice = unitPrice;
        this.tax = tax;
        this.quantity = quantity;
    }

    public ProductModel() {
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "modelId=" + modelId +
                ", productId=" + productId +
                ", productModelName='" + productModelName + '\'' +
                ", unitPrice=" + unitPrice +
                ", tax=" + tax +
                ", quantity=" + quantity +
                '}';
    }
}
