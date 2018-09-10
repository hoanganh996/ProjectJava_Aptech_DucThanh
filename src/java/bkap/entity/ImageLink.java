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
public class ImageLink {
    private int imageLinkId;
    private int productId;
    private String imageLinkName;
    private boolean status;

    public int getImageLinkId() {
        return imageLinkId;
    }

    public void setImageLinkId(int imageLinkId) {
        this.imageLinkId = imageLinkId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getImageLinkName() {
        return imageLinkName;
    }

    public void setImageLinkName(String imageLinkName) {
        this.imageLinkName = imageLinkName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
