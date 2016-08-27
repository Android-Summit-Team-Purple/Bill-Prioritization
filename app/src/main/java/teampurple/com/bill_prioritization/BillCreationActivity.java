package teampurple.com.bill_prioritization;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Anthony-Parkour on 8/27/16.
 */
public class BillCreationActivity {
    private final String LOG_TAG = "Bills";
    final String API_KEY = BuildConfig.NESSIE_API_KEY;

    //get all existing merchants for drop downs
    public void getExistingMerchants(){

        new Thread() {
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL myURL = new URL("http://api.reimaginebanking.com/merchants?key=" + API_KEY);
                    connection = (HttpURLConnection) myURL.openConnection();
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setRequestMethod("GET");

                    InputStream iStream = new BufferedInputStream(connection.getInputStream());
                    String response = readStream2(iStream);
                    JSONArray merchantArray = new JSONArray(response);
                    Log.e(LOG_TAG, "Merchants: " + merchantArray);

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

    //create new merchant if they want
    public void createMerchant(){

        new Thread() {
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL myURL = new URL("http://api.reimaginebanking.com/merchants?key=" + API_KEY);
                    connection = (HttpURLConnection) myURL.openConnection();
                    connection.setRequestProperty("Content-Type","application/json");
                    connection.setRequestProperty("Accept", "application/json");
                    connection.setRequestMethod("POST");

                    JSONObject addressJson = new JSONObject();
                    addressJson.put("street_number", "Test");
                    addressJson.put("street_name", "Test");
                    addressJson.put("city", "Test");
                    addressJson.put("state", "DC");
                    addressJson.put("zip", "20003");

                    JSONObject geocodeJson = new JSONObject();
                    geocodeJson.put("lat", 38.924537);
                    geocodeJson.put("lng", -77.213224);

                    JSONObject json = new JSONObject();
                    json.put("name", "Test");
                    json.put("category", "Electric");
                    //optional
//                    json.put("address", addressJson);
//                    json.put("geocode", geocodeJson);

                    OutputStream os = connection.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));

                    writer.write(json.toString());
                    writer.flush();
                    writer.close();
                    os.close();

                    InputStream iStream = new BufferedInputStream(connection.getInputStream());

                    String response = readStream2(iStream);

                    Log.e(LOG_TAG, "Merchant Creation: " + response);

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

    //create new bill
    //get system id for user
    public void createNewBill(){

        new Thread() {
            public void run() {
                HttpURLConnection connection = null;
                try {
                    //need acct ID
                    URL myURL = new URL("http://api.reimaginebanking.com/accounts/id/bills/?key=" + API_KEY);
                    connection = (HttpURLConnection) myURL.openConnection();
                    connection.setRequestProperty("Content-Type","application/json");
                    connection.setRequestProperty("Accept", "application/json");
                    connection.setRequestMethod("POST");

                    JSONObject json = new JSONObject();
                    json.put("status", "Test");
                    json.put("payee", "Electric");
                    json.put("payment_date", "date");
                    json.put("recurring_date", 1);
                    json.put("payment_amount", 0.01);


                    OutputStream os = connection.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));

                    writer.write(json.toString());
                    writer.flush();
                    writer.close();
                    os.close();

                    InputStream iStream = new BufferedInputStream(connection.getInputStream());

                    String response = readStream2(iStream);

                    Log.e(LOG_TAG, "Merchant Creation: " + response);

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
    //read input stream into json
    private JSONObject readStream(InputStream is) {
        try {
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);

            JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());

            //returns the json object
            return jsonObject;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //if something went wrong, return null
        return null;
    }

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
