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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import database.Bill;

/**
 * Created by Anthony-Parkour on 8/27/16.
 */
public class ManagementLogic {
    private final String LOG_TAG = "Bills";
    final String API_KEY = BuildConfig.NESSIE_API_KEY;

    double currentBalance = 0;
    ArrayList<Bill> BillArray = new ArrayList<Bill>();
    ArrayList<Bill> Priorities = new ArrayList<Bill>();
    ArrayList<Bill> Urgent = new ArrayList<Bill>();

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

                    getBills();
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

                    if(affordBills()){
                        //user is ok and can afford bills
                    }else{
                        //user can not afford their bills
                    }
                }
            }
        }.start();
    }

    //determine if user can afford bills
    public boolean affordBills(){
        double billBalance = 0;

        for(int i = 0; i < BillArray.size(); i++){
            Bill thisBill = BillArray.get(i);
            //billBalance = billBalance + thisBill.paymentAmt;

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
    //create a priority list in conjunction with standard list
    public void fillPriorityList(){
      /*  double priorityBalance = 0;
        ArrayList<Bill> tempPriorities = new ArrayList<Bill>();
        ArrayList<Bill> tempUrgent = new ArrayList<Bill>();
        ArrayList<Bill> whatsLeft = BillArray;

        for(int i = 0; i < BillArray.size(); i++){
            Bill thisBill = BillArray.get(i);

            //if manually set to priority add to list
            if(thisBill.priority){
                tempPriorities.add(thisBill);
                whatsLeft.remove(thisBill);
//                priorityBalance = priorityBalance + thisBill.paymentAmt;
            }else{
                //convert and compare date
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date dueDate = sdf.parse(thisBill.dueDate);

                //50 days past due
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                calendar.add(Calendar.DATE, -50);

                if(dueDate.before(calendar.getTime())){
                    tempPriorities.add(thisBill);
                    whatsLeft.remove(thisBill);
                }else{
                    if (dueDate.before(new Date())){
                        tempUrgent.add(thisBill);
                        whatsLeft.remove(thisBill);
                    }
                }
            }

            if(i == BillArray.size() -1){
                Urgent = tempUrgent;
                Priorities = tempPriorities;
                BillArray = whatsLeft;
            }

        }*/
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
