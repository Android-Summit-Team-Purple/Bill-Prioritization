package teampurple.com.bill_prioritization.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.ParseException;
import java.util.ArrayList;

import database.Bill;

import teampurple.com.bill_prioritization.R;

public class MainActivity extends Activity {

    public ArrayList <Bill> BillArray = new ArrayList<>();
    public ArrayList <Bill> PastDueArray = new ArrayList<>();
    public ArrayList <Bill> UrgentArray = new ArrayList<>();
    public ManagementLogic managementLogic = new ManagementLogic();
    BroadcastReceiver managemetDone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTable();
            }
        });

        //receivers
        managemetDone = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.e("test", "done received");
            }
        };

        IntentFilter startIntentFilter = new IntentFilter("teampurple.com.bill_prioritization.DoneListener");
        registerReceiver(managemetDone, startIntentFilter);

        //toolbar
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        ManagementLogic managementLogic = new ManagementLogic();
//        managementLogic.getBalance();

        createBills();

    }

    @Override
    public void onStart(){
        super.onStart();

//        updateTable();

        UrgentArray = managementLogic.Priorities;
        PastDueArray = managementLogic.Urgent;
    }


    //generate canned bills
    public void createBills(){
        Log.e("test", "createBills");

        Bill bill1 = new Bill(12345, 52, "Pending", "Pepco", "Essential", 1234, "8/22/2016", 12345, 12345);
        Bill bill2 = new Bill(12345, 52, "Pending", "Rent", "Mortgage", 1234, "8/30/2016", 12345, 12345);
        Bill bill3 = new Bill(12345, 52, "Pending", "Car Loan", "Auto", 1234, "6/15/2016", 12345, 12345);
        Bill bill4 = new Bill(12345, 52, "Pending", "Verizon", "NonEssential", 1234, "9/22/2016", 12345, 12345);
        Bill bill5 = new Bill(12345, 52, "Pending", "Comcast", "NonEssential", 1234, "8/15/2016", 12345, 12345);
        bill5.isPriority = true;

        BillArray.add(bill1);
        BillArray.add(bill2);
        BillArray.add(bill3);
        BillArray.add(bill4);
        BillArray.add(bill5);

        managementLogic.BillArray = BillArray;

        try {
            managementLogic.fillPriorityList();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //table logic
    public void updateTable() {

        TableLayout table = (TableLayout) findViewById(R.id.table);

        table.removeAllViews();

        for(int i = 0; i < BillArray.size(); i++){

            if(i == 0){
                //first row
                final float scale = getResources().getDisplayMetrics().density;
                int pixels = (int) (250 * scale + 0.5f);
                int height = (int) (100 * scale + 0.5f);

                final TableRow row = new TableRow(this);
                row.setBackgroundColor(Color.rgb(211, 211, 211));
                row.setPadding(0, 5, 0, 5);
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(pixels,TableRow.LayoutParams.WRAP_CONTENT);

                TextView name = new TextView(this);
                name.setText("Urgent");
                name.setPadding(15, 10, 20, 5);
                name.setLayoutParams(layoutParams);

                row.addView(name);

                //add divider
                View v = new View(this);
                v.setPadding(0, 5, 0, 0);
                v.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 1));
                v.setBackgroundColor(Color.rgb(51, 51, 51));

                table.addView(row);
                table.addView(v);
            }else{

//                if(UrgentArray.size() > 0){

                    Bill thisBill = BillArray.get(i-1);

                    final float scale = getResources().getDisplayMetrics().density;
                    int pixels = (int) (250 * scale + 0.5f);
                    int height = (int) (50 * scale + 0.5f);

                    final TableRow row = new TableRow(this);
                    row.setBackgroundColor(Color.rgb(211, 211, 211));
                    row.setPadding(0, 5, 0, 5);
                    TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(pixels,height);

                    TextView name = new TextView(this);
                    name.setText(thisBill.payee);
                    name.setPadding(15, 10, 20, 5);
                    name.setLayoutParams(layoutParams);

                    row.addView(name);

                    //add divider
                    View v = new View(this);
                    v.setPadding(0, 5, 0, 0);
                    v.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 1));
                    v.setBackgroundColor(Color.rgb(51, 51, 51));

                    table.addView(row);
                    table.addView(v);
//                }

            }


//            JSONObject venue = jsonArray.getJSONObject(i);
//
//            String nameString = venue.getString("displayname");
//            String addString = venue.getString("address");
//            String catString = venue.getString("cat1") + ", " + venue.getString("cat2");
//
//            final float scale = getResources().getDisplayMetrics().density;
//            int pixels = (int) (250 * scale + 0.5f);
//
//            final TableRow row = new TableRow(this);
//            row.setPadding(0, 5, 0, 5);
//            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(pixels,TableRow.LayoutParams.WRAP_CONTENT);
//
//            LinearLayout HL = new LinearLayout(this);
//            HL.setOrientation(LinearLayout.HORIZONTAL);
//            HL.setPadding(10, 10, 10, 10);
//            HL.setGravity(Gravity.CENTER_VERTICAL);
//
//            final int finalI = i;
//            final JSONObject finalVen = venue;
//
//            final int finalIndex = i;
//            HL.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    Log.e(LOG_TAG, "Row " + finalI + "was clicked");
//                }
//            });


//            //calculate distance
//            Location venLoc = new Location("venloc");
//            venLoc.setLatitude(venue.getDouble("lat"));
//            venLoc.setLongitude(venue.getDouble("lon"));
//
//            LinearLayout LL = new LinearLayout(this);
//            LL.setOrientation(LinearLayout.VERTICAL);
//
//            LL.setWeightSum(6f);
//            LL.setLayoutParams(layoutParams);
//
//            TextView name = new TextView(this);
//            name.setText(nameString);
//            name.setPadding(15, 10, 20, 5);
//            name.setLayoutParams(layoutParams);
//
//            TextView address = new TextView(this);
//            address.setText(addString);
//            address.setPadding(15, 5, 20, 5);
//            address.setLayoutParams(layoutParams);
//
//            TextView categories = new TextView(this);
//            categories.setText(catString);
//            categories.setPadding(15, 5, 20, 5);
//            categories.setLayoutParams(layoutParams);
//
//            LL.addView(name);
//            LL.addView(address);
//            LL.addView(categories);
//
//            //add views to horizontal layout
//            HL.addView(LL);
////            HL.addView(distance);
//
//            row.addView(HL);
//
//            //add divider
//            View v = new View(this);
//            v.setPadding(0, 5, 0, 0);
//            v.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 1));
//            v.setBackgroundColor(Color.rgb(51, 51, 51));
//
//            table.addView(row);
//            table.addView(v);
        }
    }
}
