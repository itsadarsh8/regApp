package com.example.regapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Thankyou extends AppCompatActivity {

    Intent intent;

    public void newReg(View view){
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thankyou);

        intent= new Intent(getApplicationContext(),MainActivity.class);


    }
}
