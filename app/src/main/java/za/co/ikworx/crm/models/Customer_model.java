package za.co.ikworx.crm.models;

public class Customer_model {

    public String getCust_name() {
        return cust_name;
    }

    public String getCust_surname() {
        return cust_surname;
    }

    public String getCust_email() {
        return cust_email;
    }

    public String getCust_phone() {
        return cust_phone;
    }

    public String getCust_company() {
        return cust_company;
    }

    public String getCust_designation() {
        return cust_designation;
    }

    public String getCust_address() {
        return cust_address;
    }

    public String getCust_city() {
        return cust_city;
    }

    public String getCust_province() {
        return cust_province;
    }

    public String getCust_comment() {
        return cust_comment;
    }


    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    String cust_name;
    String cust_surname;
    String cust_email;
    String cust_phone;
    String cust_company;
    String cust_designation;
    String cust_address;
    String cust_city;

    public Customer_model() {
    }

    String cust_province;
    String cust_comment;

    public String getCust_country() {
        return cust_country;
    }

    String cust_country;

    public void setCust_ID(String cust_ID) {
        this.cust_ID = cust_ID;
    }

    String cust_ID;

    public String getCust_ID() {
        return cust_ID;
    }

    public Customer_model(String cust_ID, String cust_name, String cust_surname, String cust_email, String cust_phone, String cust_company, String cust_designation, String cust_address, String cust_city, String cust_province, String cust_country,String cust_comment) {
        this.cust_name = cust_name;
        this.cust_surname = cust_surname;
        this.cust_email = cust_email;
        this.cust_phone = cust_phone;
        this.cust_company = cust_company;
        this.cust_designation = cust_designation;
        this.cust_address = cust_address;
        this.cust_city = cust_city;
        this.cust_province = cust_province;
        this.cust_country = cust_country;
        this.cust_comment = cust_comment;
        this.cust_ID=cust_ID;
    }


}

