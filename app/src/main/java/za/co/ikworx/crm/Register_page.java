package za.co.ikworx.crm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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

import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Register_page extends AppCompatActivity {


    Spinner spin;
    JSONObject jsonobject;
    JSONArray jsonarray;
    ArrayList<String> worldlist;

    private Button reg_btn;
    private EditText etName, etSurname, etEmails, etPassword, etAddress, etCity, etPostalCode, etCountry;

    RequestQueue requestQueue;
    String insertUrl = "http://192.168.176.33/androidDB/insertRegisterPage.php";
    String showUrl = "http://192.168.176.33/androidDB/insertRegisterPage.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        reg_btn = (Button) findViewById(R.id.btnConfirm);
        etName = (EditText) findViewById(R.id.txtName);
        etSurname = (EditText) findViewById(R.id.txtSurname);
        etEmails = (EditText) findViewById(R.id.txtEmail);
        etPassword = (EditText) findViewById(R.id.txtPassword);
        etAddress = (EditText) findViewById(R.id.txtHomeAddress);
        etCity = (EditText) findViewById(R.id.txtPostalCode);
        etPostalCode = (EditText) findViewById(R.id.txtPostalCode);
        etCountry = (EditText) findViewById(R.id.txtCountry);

        //spinner manual addition
        spin = (Spinner) findViewById(R.id.droplistRoles);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Register_page.this,
                android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Roles));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(myAdapter);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        try {
            reg_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (etPostalCode.getText().toString().isEmpty())
                        etPostalCode.setError("Field rquired");
                    if (etName.getText().toString().isEmpty())
                        etName.setError("Field rquired");
                    if (etSurname.getText().toString().isEmpty())
                        etSurname.setError("Field rquired");
                    if (etEmails.getText().toString().isEmpty())
                        etEmails.setError("Field rquired");
                    if (etPassword.getText().toString().isEmpty())
                        etPassword.setError("Field rquired");
                    if (etAddress.getText().toString().isEmpty())
                        etAddress.setError("Field rquired");
                    if (etCity.getText().toString().isEmpty())
                        etCity.setError("Field rquired");
                    if (etPostalCode.getText().toString().isEmpty())
                        etPostalCode.setError("Field rquired");
                    else {
                        Toast.makeText(Register_page.this, "User added successfully",
                                Toast.LENGTH_LONG).show();
                        //Toast.makeText(Register_page.this, spin.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();

                        final String role = spin.getSelectedItem().toString();
                        StringRequest request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }

                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> parameters = new HashMap<String, String>();
                                parameters.put("S_Name", etName.getText().toString());
                                parameters.put("S_Surname", etSurname.getText().toString());
                                parameters.put("S_Role", role);
                                parameters.put("S_Emails", etEmails.getText().toString());
                                parameters.put("S_Password", etPassword.getText().toString());
                                parameters.put("Address", etAddress.getText().toString());
                                parameters.put("city", etCity.getText().toString());
                                parameters.put("Postal_code", etPostalCode.getText().toString());
                                parameters.put("Country", etCountry.getText().toString());

                                return parameters;

                            }

                        };
                        requestQueue.add(request);
                    }
                }
            });

        } catch (Exception e) {
            //e.printStackTrace();
            Toast.makeText(Register_page.this, "User Not Added",
                    Toast.LENGTH_LONG).show();
        }

    }
}
