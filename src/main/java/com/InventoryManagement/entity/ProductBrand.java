package com.InventoryManagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "product_brand")
public class ProductBrand {
@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "product_id")
    private int productId;
@Column(name = "brand_name")
    private String brandName;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public ProductBrand(int productId, String brandName) {
        this.productId = productId;
        this.brandName = brandName;
    }

    public ProductBrand() {
    }

    @Override
    public String toString() {
        return "ProductBrand{" +
                "productId=" + productId +
                ", brandName='" + brandName + '\'' +
                '}';
    }
}
