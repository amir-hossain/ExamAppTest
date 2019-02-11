package com.example.amir.examtestapp;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;

public class MyIntentService extends IntentService {
    private int counter;
    public MyIntentService() {
        super("");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        while (true){
            Intent broadcastIntent=new Intent("test");
            broadcastIntent.putExtra("extra",""+counter++);
            SystemClock.sleep(1000);
            sendBroadcast(broadcastIntent);
        }
    }
}
