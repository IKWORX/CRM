package za.co.ikworx.crm.models;

public class Sales_model {

    public Sales_model(String s_name, String s_surname, String s_role, String s_email, String emp_status, String salesID) {
        this.s_name = s_name;
        this.s_surname = s_surname;
        this.s_role = s_role;
        this.s_email = s_email;
        this.emp_status = emp_status;
        this.salesID = salesID;
    }

    String s_name;
    String s_surname;
    String s_role;
    String s_email;
    String emp_status;
    String salesID;

    public String getSalesID() {
        return salesID;
    }

    public void setSalesID(String salesID) {
        this.salesID = salesID;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getS_surname() {
        return s_surname;
    }

    public void setS_surname(String s_surname) {
        this.s_surname = s_surname;
    }

    public String getS_role() {
        return s_role;
    }

    public void setS_role(String s_role) {
        this.s_role = s_role;
    }

    public String getS_email() {
        return s_email;
    }

    public void setS_email(String s_email) {
        this.s_email = s_email;
    }

    public String getEmp_status() {
        return emp_status;
    }

    public void setEmp_status(String emp_status) {
        this.emp_status = emp_status;
    }
}
