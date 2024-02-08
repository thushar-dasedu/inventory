package com.InventoryManagement.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "purchase_header")
public class PurchaseHeader {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "purchase_id")
    private int PurchaseId;
    @Column(name = "supplier_id")
    private int supplierId;
    @Column(name = "purchase_date_time")
    private LocalDateTime purchaseDateTime;

    public int getPurchaseId() {
        return PurchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        PurchaseId = purchaseId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public LocalDateTime getPurchaseDateTime() {
        return purchaseDateTime;
    }

    public void setPurchaseDateTime(LocalDateTime purchaseDateTime) {
        this.purchaseDateTime = purchaseDateTime;
    }

    public PurchaseHeader(int purchaseId, int supplierId, LocalDateTime purchaseDateTime) {
        PurchaseId = purchaseId;
        this.supplierId = supplierId;
        this.purchaseDateTime = purchaseDateTime;
    }

    public PurchaseHeader() {
    }

    @Override
    public String toString() {
        return "PurchaseHeader{" +
                "PurchaseId=" + PurchaseId +
                ", supplierId=" + supplierId +
                ", purchaseDateTime=" + purchaseDateTime +
                '}';
    }
}
