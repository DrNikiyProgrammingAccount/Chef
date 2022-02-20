package com.application.personalchef;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        Button start = (Button) findViewById(R.id.start);
        Button exit = (Button) findViewById(R.id.exit);
        Button creator = (Button) findViewById(R.id.upload);
        Button imagecreator = (Button) findViewById(R.id.image_creator);
        isStoragePermissionGranted();
        File Directory = new File(Environment.getExternalStorageDirectory().toString() + "/chefdir");
// have the object build the directory structure, if needed.
        Directory.mkdirs();
        File Directory2 = new File(Environment.getExternalStorageDirectory().toString() + "/chefdir/images");
// have the object build the directory structure, if needed.
        Directory2.mkdirs();
        File Directory3 = new File(Environment.getExternalStorageDirectory().toString() + "/chefdir/recipes");
// have the object build the directory structure, if needed.
        Directory3.mkdirs();
        File Directory4 = new File(Environment.getExternalStorageDirectory().toString() + "/chefdir/cache");
// have the object build the directory structure, if needed.
        Directory4.mkdirs();



        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, Menu.class);
                startActivity(intent);
            }
        });

        creator.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, Creator.class);
                startActivity(intent);
            }
        });

        imagecreator.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, ImageCreator.class);
                startActivity(intent);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                System.exit(0);
            }
        });

    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG","Permission is granted");
                return true;
            } else {

                Log.v("TAG","Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG","Permission is granted");
            return true;
        }
    }




}