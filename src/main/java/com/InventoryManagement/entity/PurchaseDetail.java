package com.InventoryManagement.entity;

import javax.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "purchase_detail")
public class PurchaseDetail {
@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "purchase_detail_id")
    private int purchaseDetailId;
@Column(name = "purchase_id")
    private int purchaseId;
@Column(name = "model_id")
    private int modelId;
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
    private float taxRate;
@Column(name = "tax_amount")
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

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
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

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
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

    public Float getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Float taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public PurchaseDetail(int purchaseDetailId, int purchaseId, int modelId, BigDecimal unitPrice, int quantity, Float discount, BigDecimal discountAmount, BigDecimal netAmount, Float taxRate, BigDecimal taxAmount) {
        this.purchaseDetailId = purchaseDetailId;
        this.purchaseId = purchaseId;
        this.modelId = modelId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.discount = discount;
        this.discountAmount = discountAmount;
        this.netAmount = netAmount;
        this.taxRate = taxRate;
        this.taxAmount = taxAmount;
    }

    public PurchaseDetail() {
    }

    @Override
    public String toString() {
        return "PurchaseDetail{" +
                "purchaseDetailId=" + purchaseDetailId +
                ", purchaseId=" + purchaseId +
                ", modelId=" + modelId +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", discount=" + discount +
                ", discountAmount=" + discountAmount +
                ", netAmount=" + netAmount +
                ", taxRate=" + taxRate +
                ", taxAmount=" + taxAmount +
                '}';
    }
}
