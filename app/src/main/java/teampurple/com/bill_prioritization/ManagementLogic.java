package teampurple.com.bill_prioritization;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Anthony-Parkour on 8/27/16.
 */
public class ManagementLogic {
    private final String LOG_TAG = "Bills";
    final String API_KEY = BuildConfig.NESSIE_API_KEY;

    double currentBalance;
    ArrayList<Bill> BillArray = new ArrayList<Bill>();

    //check user's balance
    public void getBalance(){
        new Thread() {
            public void run() {
                HttpURLConnection connection = null;
                try {

                    //need to add account id
                    URL myURL = new URL("http://api.reimaginebanking.com/accounts/"+ "id" +"?key=" + API_KEY);
                    connection = (HttpURLConnection) myURL.openConnection();
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setRequestMethod("GET");

                    InputStream iStream = new BufferedInputStream(connection.getInputStream());
                    String response = readStream2(iStream);
                    JSONArray merchantArray = new JSONArray(response);
                    Log.e(LOG_TAG, "Balance: " + merchantArray);

                } catch (MalformedURLException ex) {
                    Log.e(LOG_TAG, "Invalid URL", ex);
                } catch (IOException ex) {
                    Log.e(LOG_TAG, "IO/Connection Error", ex);
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    if (connection == null) {
                        connection.disconnect();
                    }
                }
            }
        }.start();
    }

    //get all their bills
    public void getBills(){
        new Thread() {
            public void run() {
                HttpURLConnection connection = null;
                try {

                    //need to add account id
                    URL myURL = new URL("http://api.reimaginebanking.com/accounts/"+ "id" +"/bills?key=" + API_KEY);
                    connection = (HttpURLConnection) myURL.openConnection();
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setRequestMethod("GET");

                    InputStream iStream = new BufferedInputStream(connection.getInputStream());
                    String response = readStream2(iStream);
                    JSONArray billArray = new JSONArray(response);
                    Log.e(LOG_TAG, "Bills: " + billArray);

                } catch (MalformedURLException ex) {
                    Log.e(LOG_TAG, "Invalid URL", ex);
                } catch (IOException ex) {
                    Log.e(LOG_TAG, "IO/Connection Error", ex);
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    if (connection == null) {
                        connection.disconnect();
                    }
                }
            }
        }.start();
    }

    //determine if user can afford bills
    public boolean affordBills(){
        double billBalance = 0;

        for(int i = 0; i < BillArray.size(); i++){
            billBalance = billBalance + thisBill.paymentAmt;

            if(i == BillArray.size() - 1){
                if(billBalance > currentBalance){
                    return false;
                }else{
                    return true;
                }
            }
        }

        return false;
    }

    //look at their manual priority
    //determine rest of priority based on categories

    //read inputstream into string
    private String readStream2(InputStream is) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while(i != -1) {
                bo.write(i);
                i = is.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }
}
