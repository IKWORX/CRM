package za.co.ikworx.crm;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import za.co.ikworx.crm.models.Customer_model;
import za.co.ikworx.crm.models.productModel;

import static za.co.ikworx.crm.Utility.Utility.getIP;

public class customer_view_edit extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "customer_view_edit" ;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    private ArrayList<Customer_model> arraylist1;
    Spinner spinCustProvinces, spinCustCountries;
    Button btnInsert;
    private EditText name, surname, email, phone, company, designation, address, city, province, country, comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_customer);
       btnInsert = findViewById(R.id.btnEditCustomer);

        NavigationView mNavigationView =  findViewById(R.id.Drawer);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        if (mNavigationView != null) {
            mNavigationView.setNavigationItemSelectedListener(this);
        }

      /*  spinCustProvinces = (Spinner) findViewById(R.id.droplistCustPronvinces);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(customer_view_edit.this,
                android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Provinces));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinCustProvinces.setAdapter(myAdapter);

        spinCustCountries = (Spinner) findViewById(R.id.droplistCustCountries);
        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(customer_view_edit.this,
                android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Countries));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinCustCountries.setAdapter(myAdapter2);
*/

        mDrawerLayout = findViewById(R.id.customerDrawer1);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

new fetchCustomer().execute();

    }

public void insert1(View view){
        new insertCustomer().execute();

}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_Home)
        {
            Intent myIntent = new Intent(customer_view_edit.this, user_main.class);
            // myIntent.putExtra("key", value); //Optional parameters
            customer_view_edit .this.startActivity(myIntent);

        }
        return false;
    }
Customer_model custModel= new Customer_model();
    private class fetchCustomer extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog

        }

        @Override
        protected Void doInBackground(Void... params) {
            // Create an array
            arraylist1 = new ArrayList<Customer_model>();

            httpGet sh = new httpGet();

            // arraylist = new ArrayList<HashMap<String, String>>();
            // Making a request to url and getting response
            String url = getIP() + "customer_fetch.php";
            String jsonStr = sh.makeServiceCall(url, productModel.getCustID());

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray custome = jsonObj.getJSONArray("server_response");
                    // looping through All Contacts


                        JSONObject c = custome.getJSONObject(0);


                        custModel = new Customer_model(c.getString("C_ID"),c.getString("C_Name"), c.getString("C_Surname"),c.getString("C_Email"),c.getString("C_Phone"),c.getString("C_Company"),c.getString("C_Designation"),c.getString("C_Address"),c.getString("C_City"),c.getString("C_Province"),c.getString("C_Country"),c.getString("C_Comment"));
                        arraylist1.add(custModel);




                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
            // adapter.notifyDataSetChanged();
            return null;

        }



        @Override
        protected void onPostExecute(Void args) {
            name =findViewById(R.id.edit_name);
            surname =findViewById(R.id.edit_surname);
            email =findViewById(R.id.edit_email);
            phone =findViewById(R.id.edit_phone);
            company =findViewById(R.id.edit_company);
            designation =findViewById(R.id.edit_designation);
            address =findViewById(R.id.edit_Address);
            city =findViewById(R.id.edit_city);
            province =findViewById(R.id.edit_Province);
            country =findViewById(R.id.edit_Country);
            comment =findViewById(R.id.edit_comment);

            name.setText(custModel.getCust_name());
            surname.setText(custModel.getCust_surname());
            email.setText(custModel.getCust_email());
            phone.setText(custModel.getCust_phone());
            company.setText(custModel.getCust_company());
            designation.setText(custModel.getCust_designation());
            address.setText(custModel.getCust_address());
            city.setText(custModel.getCust_city());
            province.setText(custModel.getCust_province());
            country.setText(custModel.getCust_country());
            comment.setText(custModel.getCust_province());

        }
    }


    private class insertCustomer extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog

        }

        @Override
        protected Void doInBackground(Void... params) {
            // Create an array
            name =findViewById(R.id.edit_name);
            surname =findViewById(R.id.edit_surname);
            email =findViewById(R.id.edit_email);
            phone =findViewById(R.id.edit_phone);
            company =findViewById(R.id.edit_company);
            designation =findViewById(R.id.edit_designation);
            address =findViewById(R.id.edit_Address);
            city =findViewById(R.id.edit_city);
            province =findViewById(R.id.edit_Province);
            country =findViewById(R.id.edit_Country);
            comment =findViewById(R.id.edit_comment);
            arraylist1 = new ArrayList<Customer_model>();

            httpGet sh = new httpGet();

            // arraylist = new ArrayList<HashMap<String, String>>();
            // Making a request to url and getting response
            String url = getIP() + "customer_edit.php";
            String jsonStr = sh.makeServiceCall(url,productModel.getCustID(), name.getText().toString(), surname.getText().toString(), email.getText().toString(), phone.getText().toString(), company.getText().toString(), designation.getText().toString(), address.getText().toString(), city.getText().toString(), province.getText().toString(), country.getText().toString(), comment.getText().toString());

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray custome = jsonObj.getJSONArray("server_response");
                    // looping through All Contacts


                    JSONObject c = custome.getJSONObject(0);


                   // custModel = new Customer_model(c.getString("C_ID"),c.getString("C_Name"), c.getString("C_Surname"),c.getString("C_Email"),c.getString("C_Phone"),c.getString("C_Company"),c.getString("C_Designation"),c.getString("C_Address"),c.getString("C_City"),c.getString("C_Province"),c.getString("C_Country"),c.getString("C_Comment"));
                    //arraylist1.add(custModel);




                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
            // adapter.notifyDataSetChanged();
            return null;

        }

        @Override
        protected void onPostExecute(Void args) {


        }
    }
}

