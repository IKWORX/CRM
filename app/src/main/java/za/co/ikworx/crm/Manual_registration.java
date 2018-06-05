package za.co.ikworx.crm;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Manual_registration extends AppCompatActivity {

    Spinner spin, spin1, spin2;
    JSONObject jsonobject;
    JSONArray jsonarray;
    ArrayList<String> worldlist;
    AlertDialog alertDialog;

    private Button btnAddCust;
    private EditText txtName, txtSurname, txtEmails, txtPhone, txtCompany, txtDesignation, txtAddress, txtCity, txtPostalCode, txtCountry, txtCustComment;

    RequestQueue requestQueue;
    String insertUrl = "http://192.168.176.33/androidDB/manual_reg.php";
    String showUrl = "http://192.168.176.33/androidDB/manual_reg.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_registration);

        btnAddCust = (Button) findViewById(R.id.btnAddCustomer);
        txtName = (EditText) findViewById(R.id.txtCustName);
        txtSurname = (EditText) findViewById(R.id.txtCustSurname);
        txtEmails = (EditText) findViewById(R.id.txtCustEmail);
        txtPhone = (EditText) findViewById(R.id.txtCustPhone);
        txtCompany = (EditText) findViewById(R.id.txtCustCompany);
        txtDesignation = (EditText) findViewById(R.id.txtCustDesignation);
        txtAddress = (EditText) findViewById(R.id.txtCustHomeAddress);
        txtCity = (EditText) findViewById(R.id.txtCustCity);
        txtPostalCode = (EditText) findViewById(R.id.txtCustPostalCode);
        //txtCountry = (EditText) findViewById(R.id.txtCustCountry);
        txtCustComment = (EditText) findViewById(R.id.txtCustComment);

        //spinner manual addition
        spin = (Spinner) findViewById(R.id.droplistProvinces);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Manual_registration.this,
                android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Provinces));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(myAdapter);

        //spinner for status
        spin1 = (Spinner) findViewById(R.id.droplistStatus);
        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<String>(Manual_registration.this,
                android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Status));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(myAdapter1);

        spin2 = (Spinner) findViewById(R.id.droplistCountries);
        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(Manual_registration.this,
                android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Countries));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin2.setAdapter(myAdapter2);



        requestQueue = Volley.newRequestQueue(getApplicationContext());

        try {
            btnAddCust.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (txtName.getText().toString().isEmpty())
                        txtName.setError("Field rquired");
                    if (txtSurname.getText().toString().isEmpty())
                        txtSurname.setError("Field rquired");
                    if (txtEmails.getText().toString().isEmpty() && txtPhone.getText().toString().isEmpty())
                        Toast.makeText(Manual_registration.this, "Please fill in either the Email or Phone field",
                                Toast.LENGTH_LONG).show();
                    else {
                       // Toast.makeText(Manual_registration.this, "Customer Successfully Added",
                          //      Toast.LENGTH_LONG).show();
                        //Toast.makeText(Register_page.this, spin.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();

                       // final String province = spin.getSelectedItem().toString();
                        StringRequest request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT);
                            }

                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> parameters = new HashMap<String, String>();
                                parameters.put("C_Name", txtName.getText().toString());
                                parameters.put("C_Surname", txtSurname.getText().toString());
                                parameters.put("C_Emails", txtEmails.getText().toString());
                                parameters.put("C_Phone", txtPhone.getText().toString());
                                parameters.put("C_Company", txtCompany.getText().toString());
                                parameters.put("C_Designation", txtDesignation.getText().toString());
                                parameters.put("C_Address", txtAddress.getText().toString());
                                parameters.put("C_city", txtCity.getText().toString());
                                parameters.put("C_Postal_code", txtPostalCode.getText().toString());
                                parameters.put("C_Province",spin.getSelectedItem().toString());
                                parameters.put("C_Country", spin2.getSelectedItem().toString());
                                parameters.put("C_Comment", txtCustComment.getText().toString());
                                parameters.put("C_Status",spin1.getSelectedItem().toString());

                                return parameters;

                            }

                        };
                        requestQueue.add(request);
                        Toast.makeText(Manual_registration.this, "Customer was successfully added",
                                Toast.LENGTH_LONG).show();
                    }
                }
            });

        } catch (Exception e) {
            //e.printStackTrace();
            Toast.makeText(Manual_registration.this, "Customer Not Added",
                    Toast.LENGTH_LONG).show();
        }
    }
}
