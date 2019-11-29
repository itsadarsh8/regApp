package com.example.regapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText name, phoneNumber, emailId, ticketNumber;
    private static final int Write_External_Storage_Code = 1;
    Date currentTime;
    public static String name_data,phoneNumber_data,emailId_data,ticketNumber_data,time_data;
    Intent intent;
    public static int total_member;

    public void userData(){

        name_data=name.getText().toString();
        phoneNumber_data=phoneNumber.getText().toString();
        emailId_data=emailId.getText().toString();
        ticketNumber_data=ticketNumber.getText().toString();
        currentTime = Calendar.getInstance().getTime();
        time_data=currentTime.toString();

        startActivity(intent);

    }

    public void addMember(View view){
        total_member+=1;

        Intent intent2=new Intent(getApplicationContext(), AddMembers.class);
        startActivity(intent2);
    }

    public void onClick(View v) {

        if(name.getText().toString().isEmpty() || phoneNumber.getText().toString().isEmpty() || emailId.getText().toString().isEmpty() || ticketNumber.getText().toString().isEmpty()) {

            Toast.makeText(MainActivity.this, "Kindly fill all entries!", Toast.LENGTH_SHORT).show();
        }
        else{

            userData();
        }


        //we need to handle runtime permission for devices with marshmallow and above
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            //system OS >= Marshmallow(6.0), check if permission is enabled or not
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED) {
                //permission was not granted, request it
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, Write_External_Storage_Code);
            } else {
                //permission already granted, call save userData method

            }
        } else {
            //system OS < Marshmallow, call save userData method

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.nameId);
        Button button = findViewById(R.id.saveId);
        phoneNumber = findViewById(R.id.numberId);
        emailId = findViewById(R.id.emailId);
        ticketNumber = findViewById(R.id.ticketId);

        intent=new Intent(this,TnC.class);


    }


    //handle permission result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case Write_External_Storage_Code: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                    Toast.makeText(MainActivity.this, "Storage permsission is required to store data", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }




    }







