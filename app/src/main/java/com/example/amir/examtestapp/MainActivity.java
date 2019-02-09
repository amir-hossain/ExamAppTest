package com.example.amir.examtestapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(this,MyIntentService.class));
        Toast.makeText(MainActivity.this,"started",Toast.LENGTH_LONG).show();

        IntentFilter filter=new IntentFilter("test");
        registerReceiver(new MyBroadcastReceiver(),filter);
    }

    private class MyBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(MainActivity.this,intent.getExtras().getString("msg"),Toast.LENGTH_SHORT).show();
        }
    }
}
