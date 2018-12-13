package com.example.amir.examtestapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Persion persion=new Persion("amir");
        Intent intent=new Intent(this,SecondActivity.class);
        intent.putExtra("person",persion);
        startActivity(intent);
    }
}
