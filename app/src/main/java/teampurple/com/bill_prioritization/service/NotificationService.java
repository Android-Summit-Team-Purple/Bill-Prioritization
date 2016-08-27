package teampurple.com.bill_prioritization.service;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import android.util.Log;
import android.widget.Toast;

public class NotificationService extends FirebaseMessagingService {

    private static final String TAG = "NotificationService";
    public NotificationService() {
    }


    public void onMessageReceived(RemoteMessage message) {
        // Check if message contains a data payload.
        if (message.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + message.getData());
        }

        // Check if message contains a notification payload.
        if (message.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + message.getNotification().getBody());
        }
    }
}
