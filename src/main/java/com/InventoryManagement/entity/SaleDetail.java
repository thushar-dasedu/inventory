package com.InventoryManagement.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "sale_detail")
public class SaleDetail {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "sale_detail_id")
    private int saleDetailId;
    @Column(name = "sale_id")
    private int saleId;
    @Column(name = "product_model_id")
    private int productModelID;
    @Column(name = "serial_number")
    private String serialNumber;
    @Column(name = "unit_price")
    private BigDecimal unitPrice;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "discount")
    private float discount;
    @Column(name = "discount_amount")
    private BigDecimal discountAmount;
    @Column(name = "net_amount")
    private BigDecimal netAmount;
    @Column(name = "tax_rate")
    private float taxPrice;
    @Column(name = "tax_amount")
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

    public int getProductModelID() {
        return productModelID;
    }

    public void setProductModelID(int productModelID) {
        this.productModelID = productModelID;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
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

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
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

    public float getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(Float taxPrice) {
        this.taxPrice = taxPrice;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public SaleDetail(int saleDetailId, int saleId, int productModelID, String serialNumber, BigDecimal unitPrice, int quantity, float discount, BigDecimal discountAmount, BigDecimal netAmount, float taxPrice, BigDecimal taxAmount) {
        this.saleDetailId = saleDetailId;
        this.saleId = saleId;
        this.productModelID = productModelID;
        this.serialNumber = serialNumber;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.discount = discount;
        this.discountAmount = discountAmount;
        this.netAmount = netAmount;
        this.taxPrice = taxPrice;
        this.taxAmount = taxAmount;
    }

    public SaleDetail() {
    }

    @Override
    public String toString() {
        return "SaleDetail{" +
                "saleDetailId=" + saleDetailId +
                ", saleId=" + saleId +
                ", productModelID=" + productModelID +
                ", serialNumber='" + serialNumber + '\'' +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", discount=" + discount +
                ", discountAmount=" + discountAmount +
                ", netAmount=" + netAmount +
                ", taxPrice=" + taxPrice +
                ", taxAmount=" + taxAmount +
                '}';
    }
}
