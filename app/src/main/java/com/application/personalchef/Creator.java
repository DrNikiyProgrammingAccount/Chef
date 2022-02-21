package com.application.personalchef;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Creator extends AppCompatActivity {

    final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creator);
        getSupportActionBar().hide();
        Context context = this;




        EditText name_recipe = (EditText) findViewById(R.id.textView);
        Button add = (Button) findViewById(R.id.button_add);
        Button save = (Button) findViewById(R.id.button_finish);
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.linearLayout_scroll);
        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Log.d("LOL", "ok");
                LinearLayout ll = new LinearLayout(context);
                ll.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                ll.setBackgroundResource(R.drawable.layout_bg);
                ll.setOrientation(LinearLayout.VERTICAL);
                LinearLayout picLayout = new LinearLayout(context);
                picLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                picLayout.setOrientation(LinearLayout.HORIZONTAL);
                EditText stepActivity = new EditText(context);
                stepActivity.setHint("Введите то, что необходимо сделать на этом шаге");
                stepActivity.setTextSize(20);
                EditText time = new EditText(context);
                time.setHint("Сколько времени необходимо для этого шага? (в секундах)");
                time.setTextSize(20);
                EditText products = new EditText(context);
                products.setHint("Какие нужны продукты, и в каком количестве?");
                products.setTextSize(20);
                TextView image = new TextView(context);
                Button addPic = new Button(context);
                addPic.setText("Обзор...");
                addPic.setGravity(Gravity.RIGHT);

                Button setPic = new Button(context);
                setPic.setText("Применить!");
                setPic.setGravity(Gravity.RIGHT);

                image.setHint("Выберите картинку для шага");
                image.setTextSize(20);


                addPic.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v){
                        Intent intent = new Intent(Creator.this, ChoosePic.class);
                        startActivity(intent);

                    }
                });
                setPic.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v){
                        File myFile = new File(Environment.getExternalStorageDirectory().toString() + "/chefdir/cache/cache.txt");
                        try {
                            FileInputStream inputStream = new FileInputStream(myFile);
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                            StringBuilder stringBuilder = new StringBuilder();
                            String line;
                            try {
                                while ((line = bufferedReader.readLine()) != null){
                                    stringBuilder.append(line);
                                }
                                image.setText(stringBuilder);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                    }
                });




                ll.addView(stepActivity);
                ll.addView(time);
                ll.addView(products);
                picLayout.addView(image);
                picLayout.addView(addPic);
                picLayout.addView(setPic);
                ll.addView(picLayout);

                mainLayout.addView(ll);

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                int count = mainLayout.getChildCount();
                View vi = null;
                LinearLayout linearLittle = null;
                File myFile = new File(Environment.getExternalStorageDirectory().toString() + "/chefdir/recipes/" + String.valueOf(name_recipe.getText()));
                try {
                    myFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < count; i++) {
                    vi = mainLayout.getChildAt(i);

                    linearLittle = (LinearLayout) vi;

                    View vi1 = linearLittle.getChildAt(0);
                    TextView text1 = (TextView) vi1;
                    String strForWrite1 = String.valueOf(text1.getText()) + "\n";

                    View vi2 = linearLittle.getChildAt(1);
                    TextView text2 = (TextView) vi2;
                    String strForWrite2 = String.valueOf(text2.getText() + "\n");

                    View vi3 = linearLittle.getChildAt(2);
                    TextView text3 = (TextView) vi3;
                    String strForWrite3 = String.valueOf(text3.getText() + "\n");

                    View vi4 = linearLittle.getChildAt(3);
                    LinearLayout horiz = (LinearLayout) vi4;

                    TextView text4 = (TextView) horiz.getChildAt(0);
                    String strForWrite4 = String.valueOf(text4.getText() + "\n");


                    try {
                        FileOutputStream outputStream = new FileOutputStream(myFile, true);
                        outputStream.write(strForWrite1.getBytes());
                        outputStream.write(strForWrite2.getBytes());
                        outputStream.write(strForWrite3.getBytes());
                        outputStream.write(strForWrite4.getBytes());
                        outputStream.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                finish();
            }
        });

    }

}