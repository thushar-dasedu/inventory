package com.InventoryManagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "product_serial_number")
public class ProductSerialNumber {
@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "sl_id")
    private int slID;
@Column(name = "stock_id")
    private int stockId;
@Column(name = "serial_number")
    private String serialNumber;

    public int getSlID() {
        return slID;
    }

    public void setSlID(int slID) {
        this.slID = slID;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public ProductSerialNumber(int slID, int stockId, String serialNumber) {
        this.slID = slID;
        this.stockId = stockId;
        this.serialNumber = serialNumber;
    }

    public ProductSerialNumber() {
    }

    @Override
    public String toString() {
        return "ProductSerialNumber{" +
                "slID=" + slID +
                ", stockId=" + stockId +
                ", serialNumber='" + serialNumber + '\'' +
                '}';
    }
}
