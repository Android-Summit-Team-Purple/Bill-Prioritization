package teampurple.com.bill_prioritization;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Anthony-Parkour on 8/27/16.
 */
public class BillCreationActivity {
    private final String LOG_TAG = "Bills";

    //get all existing merchants for drop downs
    public void getExistingMerchants(){
        final String API_KEY = BuildConfig.NESSIE_API_KEY;

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
    //create new bill

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
