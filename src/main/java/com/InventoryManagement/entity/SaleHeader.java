package com.InventoryManagement.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "sale_header")
public class SaleHeader {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "sale_id")
    private int saleId;
    @Column(name = "customer_id")
    private int customerId;
    @Column(name = "sale_date_time")
    private LocalDateTime saleDateTime;

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getSaleDateTime() {
        return saleDateTime;
    }

    public void setSaleDateTime(LocalDateTime saleDateTime) {
        this.saleDateTime = saleDateTime;
    }

    public SaleHeader(int saleId, int customerId, LocalDateTime saleDateTime) {
        this.saleId = saleId;
        this.customerId = customerId;
        this.saleDateTime = saleDateTime;
    }

    public SaleHeader() {
    }

    @Override
    public String toString() {
        return "SaleHeader{" +
                "saleId=" + saleId +
                ", customerId=" + customerId +
                ", saleDateTime=" + saleDateTime +
                '}';
    }
}
