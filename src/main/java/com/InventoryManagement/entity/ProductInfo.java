package com.InventoryManagement.entity;

import java.math.BigDecimal;

public class ProductInfo {
    private int modelId;
    private int productId;
    private String productModelName;
    private BigDecimal unitPrice;
    private float tax;
    private int quantity;

    private int id;
    private String name;
    private String type;
    private String filePath;

    public ProductInfo() {
    }

    public ProductInfo(int modelId, int productId, String productModelName, BigDecimal unitPrice, float tax, int quantity, int id, String name, String type, String filePath) {
        this.modelId = modelId;
        this.productId = productId;
        this.productModelName = productModelName;
        this.unitPrice = unitPrice;
        this.tax = tax;
        this.quantity = quantity;
        this.id = id;
        this.name = name;
        this.type = type;
        this.filePath = filePath;
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "ProductInfo{" +
                "modelId=" + modelId +
                ", productId=" + productId +
                ", productModelName='" + productModelName + '\'' +
                ", unitPrice=" + unitPrice +
                ", tax=" + tax +
                ", quantity=" + quantity +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}
