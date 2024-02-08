package com.InventoryManagement.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AllSalesInformation {
    private int saleDetailId;
    private int saleId;
    private String customerName;
    private LocalDateTime saleDateTime;
    private String productModelName;
    private BigDecimal unitPrice;
    private BigDecimal discountAmount;
    private BigDecimal netAmount;
    private BigDecimal taxAmount;


    public int getSaleDetailId() {
        return saleDetailId;
    }

    public void setSaleDetailId(int saleDetailId) {
        this.saleDetailId = saleDetailId;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDateTime getSaleDateTime() {
        return saleDateTime;
    }

    public void setSaleDateTime(LocalDateTime saleDateTime) {
        this.saleDateTime = saleDateTime;
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

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(BigDecimal netAmount) {
        this.netAmount = netAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public AllSalesInformation(int saleDetailId, int saleId, String customerName, LocalDateTime saleDateTime, String productModelName, BigDecimal unitPrice, BigDecimal discountAmount, BigDecimal netAmount, BigDecimal taxAmount) {
        this.saleDetailId = saleDetailId;
        this.saleId = saleId;
        this.customerName = customerName;
        this.saleDateTime = saleDateTime;
        this.productModelName = productModelName;
        this.unitPrice = unitPrice;
        this.discountAmount = discountAmount;
        this.netAmount = netAmount;
        this.taxAmount = taxAmount;
    }

    public AllSalesInformation() {
    }

    @Override
    public String toString() {
        return "AllSalesInformation{" +
                "saleDetailId=" + saleDetailId +
                ", saleId=" + saleId +
                ", customerName='" + customerName + '\'' +
                ", saleDateTime=" + saleDateTime +
                ", productModelName='" + productModelName + '\'' +
                ", unitPrice=" + unitPrice +
                ", discountAmount=" + discountAmount +
                ", netAmount=" + netAmount +
                ", taxAmount=" + taxAmount +
                '}';
    }
}
