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
    private Messenger sender, receiver;
    private int randomNumberValue=0;
    private ServiceConnection connection;

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
        serviceIntent.setClassName("com.example.amir.myapplication","com.example.amir.myapplication.MyService");
    }

    @OnClick(R.id.bind)
    void bindService(){
        if(isBound){
            showToast("Service already bounded");
        }else {
            connection=new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder binder) {
                    sender =new Messenger(binder);
                    receiver =new Messenger(new RandomNumberReceiveHandler());
                    sendNumberRequest();
                    isBound=true;
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    receiver =null;
                    sender =null;
                    isBound=false;
                }
            };
            bindService(serviceIntent,connection,Service.BIND_AUTO_CREATE);
            showToast("Service bound");
        }


    }

    @OnClick(R.id.unbind)
    void unbindService(){
        if(isBound){
            Message message=Message.obtain();
            unbindService(connection);
//            message without replay to will stop sending number
            message.arg1=RANDOM_NUMBER_FLAG;
            isBound=false;
            showToast("Service unbounded");
            try {

                sender.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }else {
            showToast("No service to unbind");
        }

    }

    private void sendNumberRequest() {
        Message message=Message.obtain(null,RANDOM_NUMBER_FLAG);
        message.replyTo= receiver;
        try {
            sender.send(message);
//            receiver.send(message);
        } catch (RemoteException e) {
            Log.i("ClientApp",e.getMessage());
        }
    }

    private void showToast(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }






    class RandomNumberReceiveHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case RANDOM_NUMBER_FLAG:
                    randomNumberValue=msg.arg1;
                    textView.setText("Random Number: "+ randomNumberValue);

            }
        }
    }



    @Override
    protected void onDestroy() {
        connection=null;
        super.onDestroy();

    }
}
