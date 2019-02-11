package com.example.amir.examtestapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button nextPage=findViewById(R.id.nxt);
        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });

//        normalThreadMessage();
//        asyncTaskMessage();
        serviceMessage();
    }

    private void serviceMessage() {
        startService(new Intent(this,MyIntentService.class));
        registerReceiver(new MyBroadcastReceiver(),new IntentFilter("test"));

    }

    private void asyncTaskMessage() {
        new MyAsyncTask().execute();
    }

    private void normalThreadMessage() {
        Handler handler=new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                Toast.makeText(MainActivity.this,""+msg.arg1,Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        new NormalThread(handler).start();
    }

    private class MyAsyncTask extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(MainActivity.this,"finished",Toast.LENGTH_SHORT).show();
        }
    }

    private class NormalThread extends Thread implements Runnable{
        private Handler handler;
        private int counter;

        public NormalThread(Handler handler) {
            this.handler = handler;
        }

        @Override
        public void run() {
            while (true){
                Message message=Message.obtain();
                message.arg1=counter++;
                handler.sendMessage(message);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private class MyBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,intent.getStringExtra("extra"),Toast.LENGTH_SHORT).show();
        }
    }
}
