package com.example.amir.examtestapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyBroatcastReceiver extends BroadcastReceiver {
    private ScreenListener listener;

    public MyBroatcastReceiver(ScreenListener listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        listener.icScreenOff();
    }

    public interface ScreenListener{
        void icScreenOff();
    }
}
