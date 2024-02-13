package com.InventoryManagement.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SaleModel {
    private int saleId;

    private int customerId;
    private LocalDateTime saleDateTime;


    private List<SaleDetail> saleDetailModels=new ArrayList<SaleDetail>();




    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public LocalDateTime getSaleDateTime() {
        return saleDateTime;
    }

    public void setSaleDateTime(LocalDateTime saleDateTime) {
        this.saleDateTime = saleDateTime;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<SaleDetail> getSaleDetailModels() {
        return saleDetailModels;
    }

    public void setSaleDetailModels(List<SaleDetail> saleDetailModels) {
        this.saleDetailModels = saleDetailModels;
    }

    public SaleModel(int saleId, int customerId, LocalDateTime saleDateTime, List<SaleDetail> saleDetailModels) {
        this.saleId = saleId;
        this.customerId = customerId;
        this.saleDateTime = saleDateTime;
        this.saleDetailModels = saleDetailModels;
    }

    public SaleModel() {
    }
}
