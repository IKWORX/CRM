package za.co.ikworx.crm.helpers;


import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import za.co.ikworx.crm.R;

public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public TextView txtName;
    public TextView txtSurname;
    public TextView txtEmail;
    public TextView txtPhone;
    public TextView txtCompany;
    public TextView txtDesignation;
    public TextView txtAddress;
    public TextView txtCity;
    public TextView txtProvince;
    public TextView txtComment;


    String Name;
    String Surname;
    String email;
    String phone;
    String comany;
    String position;
    String address;
    String city;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getComany() {
        return comany;
    }

    public void setComany(String comany) {
        this.comany = comany;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    String province;
    String comment;

    public CustomDialogClass(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.alert_dialog);
        txtName =findViewById(R.id.cust_name2);
        txtSurname=findViewById(R.id.cust_surname2);
        txtAddress=findViewById(R.id.cust_Address2);
        txtCity=findViewById(R.id.cust_city2);
        txtDesignation= findViewById(R.id.cust_designation2);
        txtPhone=findViewById(R.id.cust_phone2);
        txtEmail=findViewById(R.id.cust_email2);
        txtComment=findViewById(R.id.cust_comment2);
        txtProvince=findViewById(R.id.cust_province2);
        txtCompany=findViewById(R.id.cust_company2);

        txtName.setText(Name);
        txtSurname.setText(Surname);
        txtProvince.setText(province);
        txtComment.setText(comment);
        txtCompany.setText(comany);
        txtAddress.setText(address);
        txtEmail.setText(email);
        txtCity.setText(city);
        txtDesignation.setText(position);
        txtPhone.setText(phone);
        //d.setTitle("Info");


    }

    @Override
    public void onClick(View v) {

        dismiss();
    }
}