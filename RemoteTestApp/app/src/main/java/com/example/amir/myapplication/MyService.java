package com.example.amir.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Random;

public class MyService extends Service {

    private static final int MIN = 1;
    private static final int MAX = 100;
    private boolean generatorOn=false;
    private int randomNumber;
    private String tag="MyService";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        generatorOn=true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                generateRandomNumber();
            }
        }).start();

        return START_STICKY;
    }

    private void generateRandomNumber() {
        try {

            while (generatorOn){
                Thread.sleep(1000);
                randomNumber=new Random().nextInt(MAX)+MIN;
                Log.i(tag,"Random number "+randomNumber);
            }
        } catch (InterruptedException e) {

        }

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        generatorOn=false;
        Log.i(tag,"service destroyed");
    }
}
