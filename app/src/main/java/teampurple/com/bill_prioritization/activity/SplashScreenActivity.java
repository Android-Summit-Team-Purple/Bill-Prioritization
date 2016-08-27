package teampurple.com.bill_prioritization.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import teampurple.com.bill_prioritization.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, BillPriorityDisplayActivity.class);
        startActivity(intent);
        finish();
    }
}
