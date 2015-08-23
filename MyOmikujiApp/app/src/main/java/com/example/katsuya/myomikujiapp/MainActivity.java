package com.example.katsuya.myomikujiapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getOmikuji(View view){
        TextView tv = (TextView) findViewById(R.id.myTextView);
        String[] results = {"大吉", "吉", "凶"};
        Random randomGenerator = new Random();
        int num = randomGenerator.nextInt(results.length);

        if(num == 0){
            tv.setTextColor(Color.RED);
        }else{
            tv.setTextColor(Color.BLACK);
        }

        tv.setText(results[num]);
    }
}
