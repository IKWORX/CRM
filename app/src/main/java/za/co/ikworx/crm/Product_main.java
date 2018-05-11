package za.co.ikworx.crm;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import za.co.ikworx.crm.adapters.ProductsAdapter;
import za.co.ikworx.crm.helpers.EndlessScrollListener;
import za.co.ikworx.crm.helpers.Space;
import za.co.ikworx.crm.models.Product;

import static za.co.ikworx.crm.Utility.IP;

public class Product_main extends AppCompatActivity {
    ProductsAdapter productsAdapter;
    JSONObject jsonobject;
    JSONArray jsonarray;

    ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Bind RecyclerView from layout to recyclerViewProducts object
        RecyclerView recyclerViewProducts = findViewById(R.id.recyclerViewProducts);

        //Create new ProductsAdapter
        productsAdapter = new ProductsAdapter(this);
        //Create new GridLayoutManager
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,
                2,//span count no of items in single row
                GridLayoutManager.VERTICAL,//Orientation
                false);//reverse scrolling of recyclerview
        //set layout manager as gridLayoutManager
        recyclerViewProducts.setLayoutManager(gridLayoutManager);

        //Crete new EndlessScrollListener fo endless recyclerview loading
        EndlessScrollListener endlessScrollListener = new EndlessScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                if (!productsAdapter.loading)
                    new DownloadJSON().execute();
            }
        };
        //to give loading item full single row
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (productsAdapter.getItemViewType(position)) {
                    case ProductsAdapter.PRODUCT_ITEM:
                        return 1;
                    case ProductsAdapter.LOADING_ITEM:
                        return 2; //number of columns of the grid
                    default:
                        return -1;
                }
            }
        });
        //add on on Scroll listener
       // recyclerViewProducts.addOnScrollListener(endlessScrollListener);
        //add space between cards
        recyclerViewProducts.addItemDecoration(new Space(2, 20, true, 0));
        //Finally set the adapter
        recyclerViewProducts.setAdapter(productsAdapter);
        //load first page of recyclerview
        endlessScrollListener.onLoadMore(0, 0);
    }

    //Load Data from your server here
    // loading data from server will make it very large
    // that's why i created data locally
    /*private void feedData() {
        //show loading in recyclerview
        productsAdapter.showLoading();
        final List<Product> products = new ArrayList<>();
        int[] imageUrls = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4};
        String[] ProductName = {"Kingsmon Top", "Adidas Top", "Butterfly Top", "White Top"};
        String[] ProductPrice = {"₹594", "₹5000", "₹200", "₹1999"};
        boolean[] isNew = {true, false, false, true};
        for (int i = 0; i < imageUrls.length; i++) {
            Product product = new Product(imageUrls[i],
                    ProductName[i],
                    ProductPrice[i],
                    isNew[i]);
            products.add(product);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //hide loading
                productsAdapter.hideLoading();
                //add products to recyclerview
                productsAdapter.addProducts(products);
            }
        }, 2000);

    }*/

    private class DownloadJSON extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            productsAdapter.showLoading();

        }

        @Override
        protected Void doInBackground(Void... params) {
            // Create an array
          products = new ArrayList<>();
            // Retrieve JSON Objects from the given URL address
            jsonobject = JSONfunctions
                    .getJSONfromURL(IP+"product.php");

            try {
                // Locate the array name in JSON
                jsonarray = jsonobject.getJSONArray("server_response");

                for (int i = 0; i < jsonarray.length(); i++) {

                    jsonobject = jsonarray.getJSONObject(i);
                    // Retrive JSON Objects
                     String ID= jsonobject.getString("ID");
                    String name= jsonobject.getString("name");
                    String duration= jsonobject.getString("duration");
                    String price= jsonobject.getString("price");
                    String picture= ID+".jpg";

                    Product product= new Product(picture,name,price,false,ID,duration);
                    products.add(product);


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
            productsAdapter.hideLoading();
            //add products to recyclerview
            productsAdapter.addProducts(products);

        }
    }
}
