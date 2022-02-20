package com.application.personalchef;



import androidx.appcompat.app.AppCompatActivity;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class ChoosePic extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosepic);
        getSupportActionBar().hide();

        Context context = this;

        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.listPic);

        File dir = new File(Environment.getExternalStorageDirectory().toString() + "/chefdir/images");
        File[] list = dir.listFiles();

        //Log.d("DIR", String.valueOf(list.length));

        for (int i = 0; i < list.length; i++){

            //Log.d("cyc", String.valueOf(i));
            //LinearLayout ll = new LinearLayout(context);
            //ll.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            //ll.setBackgroundResource(R.drawable.layout_bg);
            //ll.setOrientation(LinearLayout.VERTICAL);
            //ImageView image = new ImageView(context);
            //Log.d("cyc", String.valueOf(Uri.fromFile(list[i])));
            //image.setImageURI(Uri.fromFile(list[i]));
            //ll.addView(image);
            //mainLayout.addView(ll);


            LinearLayout ll = new LinearLayout(this);
            ll.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            ll.setBackgroundResource(R.drawable.layout_bg);
            ll.setOrientation(LinearLayout.VERTICAL);


            ImageView image1 = new ImageView(this);
            Log.d("cyc", String.valueOf(Uri.fromFile(list[i])));
            image1.setImageURI(Uri.fromFile(list[i]));
            LinearLayout.LayoutParams picParams = new LinearLayout.LayoutParams(800, 800);
            picParams.setMargins(50,0,0,0);
            image1.setLayoutParams(picParams);
            LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            textParams.setMargins(50,0,0,0);
            TextView name_path = new TextView(this);
            name_path.setLayoutParams(textParams);
            name_path.setText(String.valueOf(list[i]));
            name_path.setTextSize(20);
            Button choosePic = new Button(this);
            choosePic.setText("Выбрать");

            choosePic.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v){
                    try {
                        File myFile = new File(Environment.getExternalStorageDirectory().toString() + "/chefdir/cache/cache.txt");
                        myFile.createNewFile();
                        FileOutputStream outputStream = new FileOutputStream(myFile);
                        String text = (String) name_path.getText();
                        outputStream.write(text.getBytes());
                        outputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            });


            ll.addView(image1);
            ll.addView(name_path);
            ll.addView(choosePic);
            mainLayout.addView(ll);


        }
    }


}