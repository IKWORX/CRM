package za.co.ikworx.crm.models;

import static za.co.ikworx.crm.Utility.Utility.IP;

/**
 * Created by amardeep on 1/4/2018.
 */

public class Product {
    private String imageResourceId;
    private String duration;
    private String ID;
    private String leadID;
    private String invoiceID;

    public String getLeadID() {
        return leadID;
    }

    public void setLeadID(String leadID) {
        this.leadID = leadID;
    }

    public String getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }

    private String productName;
    private String productPrice;

    private boolean isLoading = false;
    private boolean isNew = false;

    public String getDuration() {
        return duration;
    }

    public String getID() {
        return ID;
    }

    public Product(String imageResourceId, String productName, String productPrice, boolean isNew, String ID, String duration) {
        this.imageResourceId = imageResourceId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.duration=duration;
        this.ID=ID;
        this.isNew = isNew;
    }

    public Product() {
    }
    public boolean isNew() {
        return isNew;
    }

    public String getImageResourceId() {
        return IP+imageResourceId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }
}
