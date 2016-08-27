package teampurple.com.bill_prioritization;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //test call for merchants
        BillCreationActivity billCreationActivity = new BillCreationActivity();
        billCreationActivity.createMerchant();
    }
}
