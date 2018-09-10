/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkap.entity;

/**
 *
 * @author panth
 */
public class Supplier {
   private int supplierId;
   private String supplierName;
   private String supplierPhone;
   private String supplierMail;
   private String supplierAddress;
   private boolean status;

   
    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }
    
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }
   private int supplierStatus;

    
    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierPhone() {
        return supplierPhone;
    }

    public void setSupplierPhone(String supplierPhone) {
        this.supplierPhone = supplierPhone;
    }

    public String getSupplierMail() {
        return supplierMail;
    }

    public void setSupplierMail(String supplierMail) {
        this.supplierMail = supplierMail;
    }

    public int getSupplierStatus() {
        return supplierStatus;
    }

    public void setSupplierStatus(int supplierStatus) {
        this.supplierStatus = supplierStatus;
    }
   
   
}
