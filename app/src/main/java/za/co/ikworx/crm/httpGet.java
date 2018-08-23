package za.co.ikworx.crm;

/**
 * Created by Thulani on 2017/09/08.
 */
import android.util.Log;
import android.widget.Toast;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public
class httpGet {
    private static final String TAG = httpGet.class.getSimpleName();

    public httpGet() {
    }

    public String makeServiceCall(String reqUrl) {
        String response = null;
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        return response;
    }
    public String makeServiceCall(String reqUrl,String user,String password) {
        String response = null;
        try {
            URL url = new URL(reqUrl);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            OutputStream output = conn.getOutputStream();
            BufferedWriter bWritter = new BufferedWriter(new OutputStreamWriter(output, "UTF-8"));
            String post_data = URLEncoder.encode("user", "UTF-8") + "=" + URLEncoder.encode(user, "UTF-8") + "&"
                    +  URLEncoder.encode("pass", "UTF-8")+ "=" + URLEncoder.encode(password, "UTF-8");

            bWritter.write(post_data);
            bWritter.flush();
            output.close();
            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        return response;
    }

    public String makeServiceCall(String reqUrl,String user) {
        String response = null;
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            OutputStream output = conn.getOutputStream();
            BufferedWriter bWritter = new BufferedWriter(new OutputStreamWriter(output, "UTF-8"));
            String post_data = URLEncoder.encode("user", "UTF-8") + "=" + URLEncoder.encode(user, "UTF-8");
            bWritter.write(post_data);
            bWritter.flush();
            output.close();
            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        return response;
    }

    public String makeServiceCall(String reqUrl,String user, String name, String surname, String email, String phone, String company, String designation, String address, String city, String province, String country, String comment) {
        String response = null;
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            OutputStream output = conn.getOutputStream();
            BufferedWriter bWritter = new BufferedWriter(new OutputStreamWriter(output, "UTF-8"));
            String post_data = URLEncoder.encode("user", "UTF-8") + "=" + URLEncoder.encode(user, "UTF-8") + "&"
                    + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&"
                    + URLEncoder.encode("surname", "UTF-8") + "=" + URLEncoder.encode(surname, "UTF-8") + "&"
                    + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&"
                    + URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(phone, "UTF-8") + "&"
                    + URLEncoder.encode("company", "UTF-8") + "=" + URLEncoder.encode(company, "UTF-8") + "&"
                    + URLEncoder.encode("designation", "UTF-8") + "=" + URLEncoder.encode(designation, "UTF-8") + "&"
                    + URLEncoder.encode("address", "UTF-8") + "=" + URLEncoder.encode(address, "UTF-8") + "&"
                    + URLEncoder.encode("city", "UTF-8") + "=" + URLEncoder.encode(city, "UTF-8") + "&"
                    + URLEncoder.encode("province", "UTF-8") + "=" + URLEncoder.encode(province, "UTF-8") + "&"
                    + URLEncoder.encode("country", "UTF-8") + "=" + URLEncoder.encode(country, "UTF-8") + "&"
                    + URLEncoder.encode("comment", "UTF-8") + "=" + URLEncoder.encode(comment, "UTF-8");
            bWritter.write(post_data);
            bWritter.flush();
            output.close();
            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        return response;
    }
    public String makeServiceCall1(String reqUrl,String user, String saleID, String leadID, String prodPrice, String invoiceID, String ProdID) {
        String response = null;
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            OutputStream output = conn.getOutputStream();
            BufferedWriter bWritter = new BufferedWriter(new OutputStreamWriter(output, "UTF-8"));
            String post_data = URLEncoder.encode("custID", "UTF-8") + "=" + URLEncoder.encode(user, "UTF-8") + "&"
                    + URLEncoder.encode("saleID  ", "UTF-8") + "=" + URLEncoder.encode(saleID, "UTF-8") + "&"
                    + URLEncoder.encode("leadID", "UTF-8") + "=" + URLEncoder.encode(leadID, "UTF-8") + "&"
                    + URLEncoder.encode("invoice", "UTF-8") + "=" + URLEncoder.encode(invoiceID, "UTF-8") + "&"
                    + URLEncoder.encode("prodPrice", "UTF-8") + "=" + URLEncoder.encode(prodPrice, "UTF-8") + "&"
                    + URLEncoder.encode("prodID", "UTF-8") + "=" + URLEncoder.encode(ProdID, "UTF-8");
            bWritter.write(post_data);
            bWritter.flush();
            output.close();
            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        return response;
    }


    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }
}
