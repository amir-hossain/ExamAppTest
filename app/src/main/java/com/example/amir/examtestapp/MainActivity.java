package com.example.amir.examtestapp;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<User> userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();

        insert(db);

        fetch(db);

        Handler handler=new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                Toast.makeText(MainActivity.this,msg.getData().getString("data"),Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        new DisplayClass(handler).start();


    }

    private void fetch(final AppDatabase db) {
        Thread fetchThread=new Thread(new Runnable() {
            @Override
            public void run() {
               userList =db.userDao().getAll();
            }
        });

        fetchThread.start();
        try {
            fetchThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void insert(final AppDatabase db){

        Thread insertThread=new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<User> users=new ArrayList<>();

                for(int i=0;i<4;i++){
                    User user=new User();
                    user.uid=i+1;
                    users.add(user);
                }
                db.userDao().insertAll(users.toArray(new User[users.size()]));
            }
        });

        insertThread.start();
        try {
            insertThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class DisplayClass extends Thread implements Runnable{
        private Handler handler;

        public DisplayClass(Handler handler) {
            this.handler = handler;
        }

        @Override
        public void run() {
            for (int i=0;i<4;i++){
                Message message=Message.obtain();
                Bundle bundle =new Bundle();
                bundle.putString("data",userList.get(i).uid+"");
                message.setData(bundle);
                message.setData(bundle);
                handler.sendMessage(message);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
