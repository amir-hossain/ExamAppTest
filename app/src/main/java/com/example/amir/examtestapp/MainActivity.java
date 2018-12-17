package com.example.amir.examtestapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private String tag="life cycle method";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.i(tag,"activity on create(initialize)");
        setContentView(R.layout.activity_main);


    }

    @Override
    protected void onStart() {

        super.onStart();
        Log.i(tag,"activity on start(visible)");
    }

    @Override
    protected void onResume() {

        super.onResume();
        Log.i(tag,"activity on resume(user interaction)");


    }

    @Override
    protected void onPause() {

        super.onPause();
        Log.i(tag,"activity on pause(stop user interaction)");
    }

    @Override
    protected void onStop() {

        super.onStop();
        Log.i(tag,"activity on stop(invisible)");
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        Log.i(tag,"activity on destroy(free memory)");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(tag,"activity on re start (reopen)");
    }
}
