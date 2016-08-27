package teampurple.com.bill_prioritization;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ManagementLogic managementLogic = new ManagementLogic();
        managementLogic.getBalance();
    }
}
