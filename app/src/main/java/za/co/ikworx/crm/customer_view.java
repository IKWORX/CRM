package za.co.ikworx.crm;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.util.Attributes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import za.co.ikworx.crm.Utility.Connectivity;
import za.co.ikworx.crm.adapters.CustomersAdapter_edit;
import za.co.ikworx.crm.models.Customer_model;

import static za.co.ikworx.crm.Utility.Utility.getIP;

public class customer_view extends AppCompatActivity implements
 NavigationView.OnNavigationItemSelectedListener{
private TextView tvEmptyTextView;
private RecyclerView mRecyclerView;
private ArrayList<Customer_model> arraylist1;
        CustomersAdapter_edit adapter;

        ProgressDialog mProgressDialog;

private DrawerLayout mDrawerLayout;
private ActionBarDrawerToggle mToggle;
private Toolbar mToolbar;

private EditText editSearchCust;
private android.support.v7.app.AlertDialog alerts;

ArrayAdapter<String> custArray;

// private static final String TAG = "Sales_Reinstate";
private static final String TAG = "customer_view";

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view);
        //Navigation Drawer
    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        NavigationView mNavigationView =  findViewById(R.id.Drawer);

        editSearchCust = findViewById(R.id.custFilter);
        editSearchCust.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

            }
        });

        if (mNavigationView != null) {
        mNavigationView.setNavigationItemSelectedListener(this);
        }

        mDrawerLayout = findViewById(R.id.customerDrawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        tvEmptyTextView = (TextView) findViewById(R.id.cust_empty_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.cust_recycler_view);
        // mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager llm =new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(llm);


        arraylist1 = new ArrayList<Customer_model>();


            new DownloadJSON().execute();


        adapter = new CustomersAdapter_edit(customer_view.this, arraylist1,customer_view.this);
        // ((SwipeRecyclerViewAdapter) adapter).setMode(Attributes.Mode.Single);
        // Set the adapter to the ListView
        mRecyclerView.setAdapter(adapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, llm.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        // step 1. create a MenuCreator
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        Log.e("RecyclerView", "onScrollStateChanged");
        }
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        }
        });
        }

        //function for edit text filter
    private void filter(String text){

    ArrayList<Customer_model> filteredList = new ArrayList<>();

    for( Customer_model item: arraylist1){

        if(item.getCust_name().toLowerCase().contains(text.toLowerCase())){
            filteredList.add(item);
        }

    }
        adapter.filterList(filteredList);

    }
//get Selected Menu Item

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
        Intent myIntent = new Intent(customer_view.this, customer_view.class);
        // myIntent.putExtra("key", value); //Optional parameters
        customer_view.this.startActivity(myIntent);

        }
        return false;
        }



        private class DownloadJSON extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
        super.onPreExecute();
        // Create a progressdialog
        mProgressDialog = new ProgressDialog(customer_view.this);
        // Set progressdialog title
        mProgressDialog.setTitle("Loading Customers");
        // Set progressdialog message
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setIndeterminate(false);
        // Show progressdialog
        mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
        // Create an array
        arraylist1 = new ArrayList<Customer_model>();

        httpGet sh = new httpGet();

        // arraylist = new ArrayList<HashMap<String, String>>();
        // Making a request to url and getting response
        String url = getIP() + "customer_view.php";
        String jsonStr = sh.makeServiceCall(url);

        Log.e(TAG, "Response from url: " + jsonStr);
        if (jsonStr != null) {
        try {
        JSONObject jsonObj = new JSONObject(jsonStr);

        // Getting JSON Array node
        JSONArray custome = jsonObj.getJSONArray("server_response");
        // looping through All Contacts
        for (int i = 0; i < custome.length(); i++) {

        JSONObject c = custome.getJSONObject(i);


        Customer_model custModel = new Customer_model(c.getString("C_ID"),c.getString("C_Name"), c.getString("C_Surname"),c.getString("C_Email"),c.getString("C_Phone"),c.getString("C_Company"),c.getString("C_Designation"),c.getString("C_Address"),c.getString("C_City"),c.getString("C_Province"),c.getString("C_Country"),c.getString("C_Comment"));
            arraylist1.add(custModel);


        }

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
        // Locate the listview in listview_main.xml
        // mRecyclerView = findViewById(R.id.my_recycler_view);
        // Pass the results into ListViewAdapter.java
        //  Collections.

//adapter.notifyDataSetChanged();

        // Close the progressdialog
        adapter = new CustomersAdapter_edit(customer_view.this, arraylist1,customer_view.this);
        ((CustomersAdapter_edit) adapter).setMode(Attributes.Mode.Single);
        // Set the adapter to the ListView
        mRecyclerView.setAdapter(adapter);
        mProgressDialog.dismiss();
        }
        }
}

