package za.co.ikworx.crm.Login;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import za.co.ikworx.crm.R;
import za.co.ikworx.crm.Utility.Utility;
import za.co.ikworx.crm.customer_view;
import za.co.ikworx.crm.httpGet;
import za.co.ikworx.crm.user_main;

import static za.co.ikworx.crm.Utility.Utility.getIP;
import static za.co.ikworx.crm.Utility.Utility.setSalesID;
import static za.co.ikworx.crm.Utility.Utility.setStatus;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    //private String TAG = LoginActivity.class.getSimpleName();
    @BindView(R.id.input_email) EditText _emailText;
    @BindView(R.id.input_password) EditText _passwordText;
    @BindView(R.id.btn_login) Button _loginButton;
    @BindView(R.id.link_signup) TextView _signupLink;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        final  ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);

        getSupportActionBar().hide();

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login(progressDialog);
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    public void login(ProgressDialog progressDialog) {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

       // _loginButton.setEnabled(false);


        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();



        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        Utility.setUsername(email);
        Utility.setPassword(password);

        // TODO: Implement your own authentication logic here.

new DownloadJSON().execute();

       /// new android.os.Handler().postDelayed(
        //        new Runnable() {
         //           public void run() {
        //                // On complete call either onLoginSuccess or onLoginFailed
        //                onLoginSuccess();
        //                // onLoginFailed();
         //               progressDialog.dismiss();
         //           }
         //       }, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
                //Toast.makeText(getBaseContext(), "Login pass", Toast.LENGTH_LONG).show();
                // TODO: Implement successful signup logic here
                ;
                // By default we just finish the Activity and log them in automatically
               // this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        Intent myIntent = new Intent(this, customer_view.class);
        // myIntent.putExtra("key", value); //Optional parameters
        this.startActivity(myIntent);
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

    private class DownloadJSON extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {
            httpGet sh = new httpGet();

            // arraylist = new ArrayList<HashMap<String, String>>();
            // Making a request to url and getting response
            String url = getIP() + "login.php";
            String jsonStr = sh.makeServiceCall(url,Utility.getUsername(),Utility.getPassword() );

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray custome = jsonObj.getJSONArray("server_response");
                    // looping through All Contacts
                    for (int i = 0; i < custome.length(); i++) {

                        JSONObject c = custome.getJSONObject(i);


                        setStatus(c.getInt("status"));
                             c.getString("message");
                        setSalesID(c.getString("saleID"));

                        Log.e(TAG, c.getString("message"));





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


                if(Utility.getStatus()==2)
                {
                    onLoginSuccess();
                }
                else if (Utility.getStatus()==1)
                {
                    onLoginFailed();
                }
                else
                {
                    onLoginFailed();
                }

            // Close the progressdialog

        }
    }

}
