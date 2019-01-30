package com.example.amir.examtestapp;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MyBroatcastReceiver.ScreenListener {

    private int counter;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyBroatcastReceiver receiver=new MyBroatcastReceiver(this);
        IntentFilter filter=new IntentFilter(Intent.ACTION_SCREEN_OFF);
        registerReceiver(receiver,filter);

        textView=findViewById(R.id.text);
    }

    @Override
    public void icScreenOff() {
        counter++;
        textView.setText(counter+"");
    }
}
