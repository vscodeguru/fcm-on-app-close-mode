package com.example.notification.fcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.example.notification.MainActivity;
import com.example.notification.R;
import com.example.notification.logger.GuruLog;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class GuruFcmService extends FirebaseMessagingService {
    public GuruFcmService() {}

    public static void start(Context context) {
        GuruLog.info("GuruFcmService.start : start Called");
        context.startService( new Intent(context, GuruFcmService.class));
    }

    @Override
    public void onNewToken(String token) {
        GuruLog.info("GuruFcmService.onNewToken : " + token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        GuruLog.info("GuruFcmService.onMessageReceived : From => " + remoteMessage.getFrom());
        GuruLog.info("GuruFcmService.onMessageReceived : body => " + remoteMessage.getNotification().getBody());

        showNotification(remoteMessage.getNotification().getBody(), remoteMessage.getNotification().getTitle());
    }

    private void showNotification(String message, String title) {

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());
    }
}
