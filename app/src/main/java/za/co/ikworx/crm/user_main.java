package za.co.ikworx.crm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import za.co.ikworx.crm.adapters.ListViewAdapter;

import static za.co.ikworx.crm.Utility.Utility.IP;


public class user_main extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener{
    // Declare Variables

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    JSONObject jsonobject;
    JSONArray jsonarray;
    SeekBar seekBar;
    ListView listview;
    public int change;
    ListViewAdapter adapter;
    ProgressDialog mProgressDialog;
    ProgressDialog loc;
    ArrayList<HashMap<String, String>> arraylist;
    private LocationManager locationManager;
    private LocationListener listener;
    double longitude=0,latitude=0;
TextView textView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from listview_main.xml
        setContentView(R.layout.activity_user_main);
        // email.setEmail(getIntent().getExtras().getString("email"));

        NavigationView mNavigationView =  findViewById(R.id.Drawer);

        if (mNavigationView != null) {
            mNavigationView.setNavigationItemSelectedListener(this);
        }


        mDrawerLayout = findViewById(R.id.listvalue);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        new DownloadJSON().execute();
        // Execute DownloadJSON AsyncTask

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
            Intent myIntent = new Intent(user_main.this, user_main.class);
            // myIntent.putExtra("key", value); //Optional parameters
            user_main.this.startActivity(myIntent);

        }
        return false;
    }

    // DownloadJSON AsyncTask
    private class DownloadJSON extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(user_main.this);
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
            arraylist = new ArrayList<HashMap<String, String>>();
            // Retrieve JSON Objects from the given URL address
            jsonobject = JSONfunctions
                    .getJSONfromURL(IP+"customers.php");

            try {
                // Locate the array name in JSON
                jsonarray = jsonobject.getJSONArray("server_response");

                for (int i = 0; i < jsonarray.length(); i++) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    jsonobject = jsonarray.getJSONObject(i);
                    // Retrive JSON Objects
                    map.put("fullname", jsonobject.getString("fullname"));
                    map.put("company", jsonobject.getString("company"));
                    map.put("position", jsonobject.getString("position"));
                    map.put("ID", jsonobject.getString("ID"));
                    arraylist.add(map);

                }
            } catch (JSONException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            // Locate the listview in listview_main.xml
            listview = (ListView) findViewById(R.id.listmain);
            // Pass the results into ListViewAdapter.java
          //  Collections.

            adapter = new ListViewAdapter(user_main.this, arraylist);
            adapter.notifyDataSetChanged();
            // Set the adapter to the ListView
            listview.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();
        }
    }

}
