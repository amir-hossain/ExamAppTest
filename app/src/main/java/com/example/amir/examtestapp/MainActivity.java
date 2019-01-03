package com.example.amir.examtestapp;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {
    private static final int RANDOM_NUMBER_FLAG = 0;
    private Messenger request, receive;
    private int randomNumberValue=0;

    @BindView(R.id.textView)
    TextView textView;

    private Intent serviceIntent;

    private boolean isBound=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        serviceIntent=new Intent();
        serviceIntent.setComponent(new ComponentName("com.example.amir.myapplication","com.example.amir.myapplication.MyService"));
    }

    @OnClick(R.id.bind)
    void bindService(){
        boolean a=bindService(serviceIntent,connection,Service.BIND_AUTO_CREATE);
        showToast("Service bound");
    }

    private void showToast(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.unbind)
    void unBindService(){
        if(isBound){
            unbindService(connection);
            isBound=false;
            showToast("Service unbound");
        }else {
            showToast("No service to unbind");
        }

    }

    @OnClick(R.id.random_number)
    void getRandomNumber(){
        if(isBound){
            Message randomNumberRequestMassage=Message.obtain(null,RANDOM_NUMBER_FLAG);
            randomNumberRequestMassage.replyTo= receive;
            try {
                request.send(randomNumberRequestMassage);
            } catch (RemoteException e) {
                Log.i("ClientApp",e.getMessage());
            }
        }else {
            showToast("Service is not bounded");
        }
    }



    class RandomNumberReceiveHadeler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case RANDOM_NUMBER_FLAG:
                    randomNumberValue=msg.arg1;
                    textView.setText("Random Number: "+ randomNumberValue);

            }
            super.handleMessage(msg);
        }
    }

    ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            request =new Messenger(binder);
            receive =new Messenger(new RandomNumberReceiveHadeler());
            isBound=true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            receive =null;
            request =null;
            isBound=false;
        }
    };

    @Override
    protected void onDestroy() {
        connection=null;
        super.onDestroy();

    }
}
