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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import za.co.ikworx.crm.models.Product;
import za.co.ikworx.crm.models.productModel;

import static za.co.ikworx.crm.Utility.Utility.getIP;

public class confirmPage extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener{


    private static final String TAG = "confirmPage" ;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_page);
        TextView prodName = findViewById(R.id.productNAme);
        TextView custName = findViewById(R.id.custname);


        prodName.setText(productModel.getProdName());
        custName.setText(productModel.getCustname());
        ImageView image = findViewById(R.id.image);
        Picasso.with(this).load(productModel.getPicUrl()).into(image);
        new increment().execute();



        NavigationView mNavigationView =  findViewById(R.id.Drawer);

        if (mNavigationView != null) {
            mNavigationView.setNavigationItemSelectedListener(this);
        }


        mDrawerLayout = findViewById(R.id.confirm);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //set default

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
            Intent myIntent = new Intent(confirmPage.this, customer_view.class);
            // myIntent.putExtra("key", value); //Optional parameters
            confirmPage.this.startActivity(myIntent);

        }
        return false;
    }
    //confirm button
    public void  confirm(View view)
    {

    }

    //cancel activity
    public void  cancel(View view)
    {
        Intent myIntent = new Intent(confirmPage.this, customer_view.class);
        // myIntent.putExtra("key", value); //Optional parameters
        confirmPage.this.startActivity(myIntent);

        overridePendingTransition(R.anim.left_enter, R.anim.right_out);
    }

    private class increment extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
Product product = new Product();
        @Override
        protected Void doInBackground(Void... params) {
            httpGet sh = new httpGet();

            // arraylist = new ArrayList<HashMap<String, String>>();
            // Making a request to url and getting response
            String url = getIP() + "increment.php";
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

                        product.setInvoiceID(c.getString("invoiceID"));
                        product.setLeadID(c.getString("leadID"));

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

            return null;

        }

        @Override
        protected void onPostExecute(Void args) {

            TextView leadID= findViewById(R.id.leadID);
            TextView invoiceID=findViewById(R.id.invoiceID);
            leadID.setText(product.getLeadID());
            invoiceID.setText(product.getInvoiceID());


            // Close the progressdialog

        }
    }
}
