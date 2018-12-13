package com.example.amir.examtestapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Persion persion= (Persion) getIntent().getSerializableExtra("person");
        TextView textView=findViewById(R.id.textView);
        textView.setText(persion.getFirstName());


    }
}
