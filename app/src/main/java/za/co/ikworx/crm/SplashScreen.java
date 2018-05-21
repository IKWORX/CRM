package za.co.ikworx.crm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import za.co.ikworx.crm.Login.MainActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
       // this.requestWindowFeature(this.FEATURE_NO_TITLE);
        //this hides the title bar
        this.getSupportActionBar().hide();
        Thread myThread = new Thread(){
            @Override

            public void run(){

                try{
                    sleep(4000);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }

        };
            myThread.start();

    }
}
