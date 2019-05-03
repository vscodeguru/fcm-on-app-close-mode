package com.example.notification.logger;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GuruLog {
    public  static  void info(String tag, String msg){
        Log.d(tag + "(" + GetTime() + ")" , msg);
    }
    public  static  void info(String msg){
        Log.d("GURU (" + GetTime() + ")" , msg);
    }


    private static String GetTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date());
    }
}
