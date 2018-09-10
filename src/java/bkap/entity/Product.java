/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkap.entity;

import java.sql.Date;

/**
 *
 * @author panth
 */
public class Product {

    private int productId;
    private String productName;
    private String productContent;
    private String productContentDetail;
    private String images;
    private String priceInput;
    private String priceOutput;
    private int quantity;
    private int catalogId;
    private int supplierId;
    private String created;
    private boolean status;
    private int discount;
    private String priceDiscount;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductContent() {
        return productContent;
    }

    public void setProductContent(String productContent) {
        this.productContent = productContent;
    }

    public String getProductContentDetail() {
        return productContentDetail;
    }

    public void setProductContentDetail(String productContentDetail) {
        this.productContentDetail = productContentDetail;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getPriceInput() {
        return priceInput;
    }

    public void setPriceInput(String priceInput) {
        this.priceInput = priceInput;
    }

    public String getPriceOutput() {
        return priceOutput;
    }

    public void setPriceOutput(String priceOutput) {
        this.priceOutput = priceOutput;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getPriceDiscount() {
        return priceDiscount;
    }

    public void setPriceDiscount(String priceDiscount) {
        this.priceDiscount = priceDiscount;
    }
   
}
