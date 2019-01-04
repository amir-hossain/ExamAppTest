package com.example.amir.examtestapp;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import java.util.Random;

public class MyService extends Service {
    private static String tag="my service";
    private int ramndomNumber;
    private int MAX=100;
    private int MIN=1;
    private boolean generatorOn=false;
    private IBinder myBinder=new MyBinder();
    private Handler handler;
    private Message message;
    private Bundle bundle;

    public int getRamndomNumber() {
        return ramndomNumber;
    }

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

        bundle=new Bundle();
        try {
            while (generatorOn){
                Thread.sleep(1000);
                ramndomNumber=new Random().nextInt(MAX)+MIN;
                if(handler!=null){
                    bundle.putInt("number",ramndomNumber);
                    message=Message.obtain();
                    message.setData(bundle);
                    handler.sendMessage(message);
                }
                Log.i(tag,"generated number "+ramndomNumber);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void setHandler(Handler handler) {
        this.handler=handler;
    }


    class MyBinder extends Binder{
        MyService getService(){
            return MyService.this;
        }


    }

    @Override
    public IBinder onBind(Intent intent) {

        return myBinder;
    }

    @Override
    public void onDestroy() {

        generatorOn=false;
        Log.i(tag,"service destroyed");
        super.onDestroy();
    }

}
