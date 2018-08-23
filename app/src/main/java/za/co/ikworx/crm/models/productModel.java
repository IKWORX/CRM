package za.co.ikworx.crm.models;

public class productModel {

    private static String custID;
    private static String prodID;
    private static String custname;
    private static String prodName;
    private static String Price;

    public static String getPrice() {
        return Price;
    }

    public static void setPrice(String price) {
        Price = price;
    }

    public static String getCustID() {
        return custID;
    }

    public static void setCustID(String custID) {
        productModel.custID = custID;
    }

    public static String getProdID() {
        return prodID;
    }

    public static void setProdID(String prodID) {
        productModel.prodID = prodID;
    }

    public static String getCustname() {
        return custname;
    }

    public static void setCustname(String custname) {
        productModel.custname = custname;
    }

    public static String getProdName() {
        return prodName;
    }

    public static void setProdName(String prodName) {
        productModel.prodName = prodName;
    }

    public static String getPicUrl() {
        return picUrl;
    }

    public static void setPicUrl(String picUrl) {
        productModel.picUrl = picUrl;
    }

    private static String picUrl;
}
