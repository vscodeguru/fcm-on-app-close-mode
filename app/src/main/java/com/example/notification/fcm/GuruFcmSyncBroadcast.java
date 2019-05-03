package com.example.notification.fcm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.example.notification.logger.GuruLog;

public class GuruFcmSyncBroadcast extends BroadcastReceiver {
    public static final String SYNC_SERVICE_ACTION = "com.example.notification.fcm.GuruFcmSyncBroadcast";
    private static final int ONE_MIN = 60 * 1000;

    @Override
    public void onReceive(Context context, Intent intent) {
        GuruLog.info("GuruFcmSyncJob.onReceive : broadcast received");
        GuruFcmService.start(context);


        final AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        final Intent mIntent = new Intent();
        mIntent.setAction(SYNC_SERVICE_ACTION);

        final PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, mIntent, 0);

        if (alarm != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                GuruLog.info("GuruFcmSyncJob.setExactAndAllowWhileIdleAlarm : Setting Alarm");
                alarm.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, ONE_MIN, pendingIntent);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                GuruLog.info("GuruFcmSyncJob.setExactAlarm : Setting Alarm");
                alarm.setExact(AlarmManager.RTC_WAKEUP, ONE_MIN, pendingIntent);
            } else {
                GuruLog.info("GuruFcmSyncJob.setAlarm : Setting Alarm");
                alarm.set(AlarmManager.RTC_WAKEUP, ONE_MIN, pendingIntent);
            }
        }
    }

}
