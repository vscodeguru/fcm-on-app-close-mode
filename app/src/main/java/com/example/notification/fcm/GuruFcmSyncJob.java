package com.example.notification.fcm;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;

import com.example.notification.logger.GuruLog;


public class GuruFcmSyncJob extends JobService {
    private static final int JOB_ID = 1;
    private static final int ONE_MIN = 60 * 1000;
    public static void schedule(Context context) {
        GuruLog.info("Job=>GuruFcmSyncJob : schedule()");
        ComponentName component = new ComponentName(context, GuruFcmSyncJob.class);
        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, component)
//                .setMinimumLatency(ONE_MIN)
//                .setOverrideDeadline(ONE_MIN)
                .setPeriodic(ONE_MIN);

        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(builder.build());
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        GuruLog.info("Job=>GuruFcmSyncJob : onStartJob()");
        GuruFcmService.start(getApplicationContext());
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        GuruLog.info("Job=>GuruFcmSyncJob : onStopJob()");
        return false;
    }

}