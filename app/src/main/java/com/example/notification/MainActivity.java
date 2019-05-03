package com.example.notification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.notification.fcm.GuruFcmSyncJob;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        * ==============================================
        * USING BROADCAST TYPE
        * ===============================================
        */
                /*
                Intent mIntent = new Intent();
                mIntent.setAction(GuruFcmSyncBroadcast.SYNC_SERVICE_ACTION);
                sendBroadcast(mIntent);
                */

        /*
         * ==============================================
         * USING JOB SERVICE
         * ===============================================
         */
        GuruFcmSyncJob.schedule(getApplicationContext());

    }
}
