package za.co.ikworx.crm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.Map;

public class Register_page extends AppCompatActivity {

    private Button reg_btn;
    private EditText etName, etSurname, etEmails, etPassword, etAddress, etCity, etPostalCode;

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

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error){

                        }

                    }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<String, String>();
                        parameters.put("S_Name", etName.getText().toString());
                        parameters.put("S_Surname", etSurname.getText().toString());
                        parameters.put("S_Emails", etEmails.getText().toString());
                        parameters.put("S_Password", etPassword.getText().toString());
                        parameters.put("Address", etAddress.getText().toString());
                        parameters.put("city", etCity.getText().toString());
                        parameters.put("Postal_code", etPostalCode.getText().toString());

                        return parameters;

                }

                };
                requestQueue.add(request);
            }
        });

    }
}
