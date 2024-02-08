package com.InventoryManagement.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AllPurchaseInfo {
private int purchaseDetailId;
private int purchaseId;
private String supplierName;
private LocalDateTime purchaseDatetime;
private String productModelName;
private BigDecimal unitPrice;
private int quantity;
private BigDecimal discountAmount;
private BigDecimal netAmount;
private BigDecimal taxAmount;

    public int getPurchaseDetailId() {
        return purchaseDetailId;
    }

    public void setPurchaseDetailId(int purchaseDetailId) {
        this.purchaseDetailId = purchaseDetailId;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public LocalDateTime getPurchaseDatetime() {
        return purchaseDatetime;
    }

    public void setPurchaseDatetime(LocalDateTime purchaseDatetime) {
        this.purchaseDatetime = purchaseDatetime;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public AllPurchaseInfo(int purchaseDetailId, int purchaseId, String supplierName, LocalDateTime purchaseDatetime, String productModelName, BigDecimal unitPrice, int quantity, BigDecimal discountAmount, BigDecimal netAmount, BigDecimal taxAmount) {
        this.purchaseDetailId = purchaseDetailId;
        this.purchaseId = purchaseId;
        this.supplierName = supplierName;
        this.purchaseDatetime = purchaseDatetime;
        this.productModelName = productModelName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.discountAmount = discountAmount;
        this.netAmount = netAmount;
        this.taxAmount = taxAmount;
    }

    public AllPurchaseInfo() {
    }

}
