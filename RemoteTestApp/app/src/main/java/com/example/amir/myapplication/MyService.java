package com.example.amir.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Random;

public class MyService extends Service {

    private static final int MIN = 1;
    private static final int MAX = 100;
    private boolean generatorOn=false;
    private int randomNumber;
    private String tag="MyService";
    private final int RANDOM_NUMBER_REQUEST=0;
    private Message message;
    private Messenger sender;

    private Messenger messenger=new Messenger(new RemoteNumberRequestHandaler());

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
                if(sender!=null){
                    message=Message.obtain(null,RANDOM_NUMBER_REQUEST);
                    message.arg1=randomNumber;
                    try {
                        sender.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                Log.i(tag,"Random number "+randomNumber);
            }
        } catch (InterruptedException e) {

        }

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        generatorOn=false;
        Log.i(tag,"service destroyed");
    }

    private class RemoteNumberRequestHandaler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case RANDOM_NUMBER_REQUEST:
                    sender=msg.replyTo;


            }
            super.handleMessage(msg);
        }
    }
}
