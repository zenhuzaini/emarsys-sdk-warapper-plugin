package com.lla.emarsyssdk;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;

import com.emarsys.Emarsys;
import com.emarsys.service.EmarsysFirebaseMessagingServiceUtils;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class EmarsysPushNotification extends FirebaseMessagingService {

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);

        Log.d("what Token ", token);

        Emarsys.getPush().setPushToken(token);
        Log.d("Working", token);

    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        boolean handledByEmarsysSDK = EmarsysFirebaseMessagingServiceUtils.handleMessage(this, remoteMessage);

        if (!handledByEmarsysSDK) {
            //handle your custom push message here
            Log.d("Message ", "handledByEmarsysSDK");
        }
    }

    public void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.d("CreateNotChannel ", "Notiiftcain channel initialization satrting ...");
            CharSequence name = "Test";
            String description = "Test Test";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("1", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            Log.d("CreateNotChannel ", "This is the error  ...");


//            notificationManager =
            NotificationManager notificationManager =  getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

            Log.d("CreateNotChannel ", "Notiiftcain channel initialized");

        }
    }

    //this will be the data
//    for ssending contacts
    public void setContactUser(String value) {
        Integer fieldId = 3;
        Log.d("setContact", "get Contact starts ... ");
        Emarsys.setContact(fieldId, value );
    }

//    Use the clearContact method to remove the device details from the contact record,
//    for example, if the user signs out of the app,
//    and they should not receive personalised messages. The CompletionListener is optional.
    public void clearContactUser() {
        Log.d("Clear contact user", "get Contact starts ... ");
        Emarsys.clearContact( );
    }

}