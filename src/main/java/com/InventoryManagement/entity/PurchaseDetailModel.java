package com.InventoryManagement.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PurchaseDetailModel {
 private int purchaseDetailId;
 private int purchaseId;
 private int modelId;
 private BigDecimal unitPrice;
 private int quantity;
 private List<String> serialNumbers=new ArrayList<>();
 private float Discount;
 private BigDecimal discountAmount;
 private BigDecimal netAmount;
 private float taxRate;
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

 public List<String> getSerialNumbers() {
  return serialNumbers;
 }

 public void setSerialNumbers(List<String> serialNumbers) {
  this.serialNumbers = serialNumbers;
 }

 public float getDiscount() {
  return Discount;
 }

 public void setDiscount(float discount) {
  Discount = discount;
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

 public float getTaxRate() {
  return taxRate;
 }

 public void setTaxRate(float taxRate) {
  this.taxRate = taxRate;
 }

 public BigDecimal getTaxAmount() {
  return taxAmount;
 }

 public void setTaxAmount(BigDecimal taxAmount) {
  this.taxAmount = taxAmount;
 }

 public PurchaseDetailModel(int purchaseDetailId, int purchaseId, int modelId, BigDecimal unitPrice, int quantity, List<String> serialNumbers, float discount, BigDecimal discountAmount, BigDecimal netAmount, float taxRate, BigDecimal taxAmount) {
  this.purchaseDetailId = purchaseDetailId;
  this.purchaseId = purchaseId;
  this.modelId = modelId;
  this.unitPrice = unitPrice;
  this.quantity = quantity;
  this.serialNumbers = serialNumbers;
  Discount = discount;
  this.discountAmount = discountAmount;
  this.netAmount = netAmount;
  this.taxRate = taxRate;
  this.taxAmount = taxAmount;
 }

 public PurchaseDetailModel(int purchaseDetailId,int purchaseId,int modelId,BigDecimal unitPrice,int quantity,float discount,BigDecimal discountAmount,BigDecimal netAmount,float taxRate,BigDecimal taxAmount){
  this.purchaseDetailId=purchaseDetailId;
  this.purchaseId=purchaseId;
  this.modelId=modelId;
  this.unitPrice=unitPrice;
  this.quantity=quantity;
  Discount=discount;
  this.discountAmount=discountAmount;
  this.netAmount=netAmount;
  this.taxRate=taxRate;
  this.taxAmount=taxAmount;
 }

 public PurchaseDetailModel() {
 }

 @Override
 public String  toString() {
  return "PurchaseDetailModel{" +
          "purchaseDetailId=" + purchaseDetailId +
          ", purchaseId=" + purchaseId +
          ", modelId=" + modelId +
          ", unitPrice=" + unitPrice +
          ", quantity=" + quantity +
          ", serialNumbers=" + serialNumbers +
          ", Discount=" + Discount +
          ", discountAmount=" + discountAmount +
          ", netAmount=" + netAmount +
          ", taxRate=" + taxRate +
          ", taxAmount=" + taxAmount +
          '}';
 }
}
