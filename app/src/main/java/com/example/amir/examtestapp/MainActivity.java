package com.example.amir.examtestapp;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    private Intent serviceIntent;
    private MyService myService;
    private String tag="my service";
    private boolean isServiceBounded=false;
    private ServiceConnection connection;
    private TextView textView;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serviceIntent=new Intent(this,MyService.class);

        Button startService=findViewById(R.id.start_service);
        startService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(serviceIntent);
            }
        });

        Button stopService=findViewById(R.id.stop_service);
        stopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(serviceIntent);
            }
        });

        Button bindService=findViewById(R.id.bind_service);

        bindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isServiceBounded){
                    showToast("Service already bounded");
                }else {
                    bindService();
                    showToast("service bounded");

                }
            }
        });

        Button unbindService=findViewById(R.id.unbind_service);
        unbindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isServiceBounded){
                    unbindService(connection);
                    isServiceBounded=false;
                    myService.setHandler(null);
                    showToast("service unbinded");
                    isServiceBounded=false;
                }else {
                    showToast("there is no service to unbind");
                }
            }
        });

        textView=findViewById(R.id.textView);

        handler=new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                textView.setText(msg.getData().getInt("number")+"");

            }
        };
    }

    
    private void bindService() {
        connection=new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                MyService.MyBinder binder = (MyService.MyBinder) iBinder;
                myService=binder.getService();
                isServiceBounded=true;
                myService.setHandler(handler);
                showToast("Service bounded");

            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                isServiceBounded=false;
                myService.setHandler(null);
            }
        };
        bindService(serviceIntent,connection,Service.BIND_AUTO_CREATE);
    }

    private void showToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }


}
