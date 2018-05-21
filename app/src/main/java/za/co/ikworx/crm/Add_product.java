package za.co.ikworx.crm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Add_product extends AppCompatActivity {

    private Button addProd_btn;
    private EditText etProdName, etProdDuration, etProdPrice;

    RequestQueue requestQueue;
    String insertUrl = "http://192.168.176.33/androidDB/Add_prod.php";
    String showUrl = "http://192.168.176.33/androidDB/Add_prod.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        addProd_btn = (Button) findViewById(R.id.btnAddProd);
        etProdName = (EditText) findViewById(R.id.txtProdName);
        etProdDuration = (EditText) findViewById(R.id.txtProdDuration);
        etProdPrice = (EditText) findViewById(R.id.txtProdPrice);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        try {
            addProd_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (etProdName.getText().toString().isEmpty())
                        etProdName.setError("enter string");
                    if (etProdDuration.getText().toString().isEmpty())
                        etProdDuration.setError("Field rquired");
                    if (etProdPrice.getText().toString().isEmpty())
                        etProdPrice.setError("Field rquired");
                    else {
                        Toast.makeText(Add_product.this, "Product Successfully Added",
                                Toast.LENGTH_LONG).show();
                        //Toast.makeText(Register_page.this, spin.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();

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
                                parameters.put("Prod_Name", etProdName.getText().toString());
                                parameters.put("Prod_Duration", etProdDuration.getText().toString());
                                parameters.put("Prod_Price", etProdPrice.getText().toString());

                                return parameters;

                            }

                        };
                        requestQueue.add(request);
                    }
                }
            });

        } catch (Exception e) {
            //e.printStackTrace();
            Toast.makeText(Add_product.this, "Product Not Added",
                    Toast.LENGTH_LONG).show();
        }

    }
}
