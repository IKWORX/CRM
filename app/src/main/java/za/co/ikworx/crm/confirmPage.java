package za.co.ikworx.crm;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.squareup.picasso.Picasso;

import za.co.ikworx.crm.models.productModel;

public class confirmPage extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener{


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
}
