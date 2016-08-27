package teampurple.com.bill_prioritization.service;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

public class NotificationService extends FirebaseMessagingService {

    private static final String TAG = "NotificationService";
    public NotificationService() {
    }


    public void onMessageReceived(final RemoteMessage message) {
        // Check if message contains a notification payload.
        if (message.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + message.getNotification().getBody());
        }
        Handler mainHandler = new Handler(Looper.getMainLooper());
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(NotificationService.this,message.getNotification().getBody(),Toast.LENGTH_LONG).show();
            }
        });

    }
}
