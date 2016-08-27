package teampurple.com.bill_prioritization.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import teampurple.com.bill_prioritization.R;

public class AlertsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerts);

        TextView alertsText = (TextView) findViewById(R.id.alerts_text);

    }
}
