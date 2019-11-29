package com.example.regapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;

import java.io.FileWriter;

import static com.example.regapp.MainActivity.emailId_data;
import static com.example.regapp.MainActivity.name_data;
import static com.example.regapp.MainActivity.phoneNumber_data;
import static com.example.regapp.MainActivity.ticketNumber_data;
import static com.example.regapp.MainActivity.time_data;

public class TnC extends AppCompatActivity {

    CheckBox checkbox;
    File dir;
    Button photoButton;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    String mCameraFileName;
    Intent intent;



    private void cameraIntent() {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

        String newPicFile = ticketNumber_data + ".jpg";
        String outPath = "/sdcard/RegApp/" + newPicFile;
        File outFile = new File(outPath);

        mCameraFileName = outFile.toString();
        Uri outuri = Uri.fromFile(outFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outuri);
        startActivityForResult(intent, 2);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 2) {
                savetxt();

                File file = new File(mCameraFileName);
                if (!file.exists()) {
                    file.mkdir();
                }

                startActivity(intent);


            }
        }
    }


    public void cameraClick(View view) {
        {
            if (checkbox.isChecked()) {
                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                } else {

                    cameraIntent();

                }
            }
            else{
                    Toast.makeText(getApplicationContext(), "Kindly accept TnC to continue", Toast.LENGTH_SHORT).show();
                }
            }
        }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                cameraIntent();
            }
            else
            {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }






     void savetxt() {
        try {
            File path = Environment.getExternalStorageDirectory();
            dir = new File(path + "/RegApp/");
            dir.mkdir();

            File file = new File(dir, ticketNumber_data + ".txt");
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Time ->" + time_data + "\n Name ->" + name_data + "\n Mobile ->" + phoneNumber_data + "\n Email Id ->" + emailId_data + "\n Ticket Number ->" + ticketNumber_data);
            bw.close();

            Toast.makeText(getApplicationContext(), "Saved in location /sdcard/RegApp/"+ticketNumber_data, Toast.LENGTH_SHORT).show();

        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tn_c);

        checkbox = findViewById(R.id.checkBox);
        photoButton=findViewById(R.id.photoButton);

        intent=new Intent(getApplicationContext(),Thankyou.class);





    }


}
