package com.example.amir.examtestapp;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;

public class MyIntentService extends IntentService {


    public MyIntentService() {
        super("name");

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SystemClock.sleep(5000);
        Intent myIntent=new Intent("test");
        myIntent.putExtra("msg","finished");
        sendBroadcast(myIntent);
    }
}
