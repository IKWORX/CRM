package za.co.ikworx.crm;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.util.Attributes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import za.co.ikworx.crm.adapters.SwipeRecyclerViewAdapter;
import za.co.ikworx.crm.models.Sales_model;

import static za.co.ikworx.crm.Utility.getIP;

public class Sales_main extends AppCompatActivity {
    private TextView tvEmptyTextView;
    private RecyclerView mRecyclerView;
    private ArrayList<Sales_model> arraylist;
    SwipeRecyclerViewAdapter adapter;

    ProgressDialog mProgressDialog;





    private static final String TAG = "Sales_Reinstate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales__reinstate);
        tvEmptyTextView = (TextView) findViewById(R.id.empty_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        arraylist = new ArrayList<>();
        new DownloadJSON().execute();





        adapter = new SwipeRecyclerViewAdapter(Sales_main.this, arraylist);
       // ((SwipeRecyclerViewAdapter) adapter).setMode(Attributes.Mode.Single);
        // Set the adapter to the ListView
        mRecyclerView.setAdapter(adapter);
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


    private class DownloadJSON extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(Sales_main.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Loading Stuff");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Create an array
            arraylist = new ArrayList<>();

            httpGet sh = new httpGet();

            // arraylist = new ArrayList<HashMap<String, String>>();
            // Making a request to url and getting response
            String url = getIP() + "consultants.php";
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


                        Sales_model salesModel = new Sales_model(c.getString("Name"), c.getString("Surname"),c.getString("Role"),c.getString("Email"),String.valueOf(c.getInt("Status")),c.getString("Sales_id"));
                        arraylist.add(salesModel);


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

adapter.notifyDataSetChanged();

            // Close the progressdialog
         //   adapter = new SwipeRecyclerViewAdapter(Sales_main.this, arraylist);
            // ((SwipeRecyclerViewAdapter) adapter).setMode(Attributes.Mode.Single);
            // Set the adapter to the ListView
           // mRecyclerView.setAdapter(adapter);
            mProgressDialog.dismiss();
        }
    }
}
