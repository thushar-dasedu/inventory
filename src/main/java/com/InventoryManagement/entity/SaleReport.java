package com.InventoryManagement.entity;

import java.math.BigDecimal;
import java.util.Date;

public class SaleReport {
    private String month;
    private BigDecimal totalSale;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public BigDecimal getTotalSale() {
        return totalSale;
    }

    public void setTotalSale(BigDecimal totalSale) {
        this.totalSale = totalSale;
    }

    public SaleReport() {
    }

    public SaleReport(String month, BigDecimal totalSale) {
        this.month = month;
        this.totalSale = totalSale;
    }

    @Override
    public String toString() {
        return "SaleReport{" +
                "month=" + month +
                ", totalSale=" + totalSale +
                '}';
    }
}
