package com.InventoryManagement.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PurchaseModel {
    private int purchaseId;
    private int supplierId;
    private LocalDateTime purchaseDateTime;
   private List<PurchaseDetailModel> purchaseDetailModels=new ArrayList<>();

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
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

    public List<PurchaseDetailModel> getPurchaseDetailModels() {
        return purchaseDetailModels;
    }

    public void setPurchaseDetailModels(List<PurchaseDetailModel> purchaseDetailModels) {
        this.purchaseDetailModels = purchaseDetailModels;
    }

    public PurchaseModel(int purchaseId, int supplierId, LocalDateTime purchaseDateTime, List<PurchaseDetailModel> purchaseDetailModels) {
        this.purchaseId = purchaseId;
        this.supplierId = supplierId;
        this.purchaseDateTime = purchaseDateTime;
        this.purchaseDetailModels = purchaseDetailModels;
    }

    public PurchaseModel() {
    }

    @Override
    public String toString() {
        return "PurchaseModel{" +
                "purchaseId=" + purchaseId +
                ", supplierId=" + supplierId +
                ", purchaseDateTime=" + purchaseDateTime +
                ", purchaseDetailModels=" + purchaseDetailModels +
                '}';
    }
}
