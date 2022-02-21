package com.application.personalchef;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();

        Context context = this;

        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.listRes);

        File dir = new File(Environment.getExternalStorageDirectory().toString() + "/chefdir/recipes");
        File[] list = dir.listFiles();


        for (int i = 0; i < list.length; i++){



            LinearLayout ll = new LinearLayout(this);
            ll.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            ll.setBackgroundResource(R.drawable.layout_bg);
            ll.setOrientation(LinearLayout.VERTICAL);


            LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            textParams.setMargins(50,0,0,0);
            TextView name_path = new TextView(this);
            name_path.setLayoutParams(textParams);
            name_path.setText(String.valueOf(list[i]));
            name_path.setTextSize(20);

            TextView name = new TextView(this);
            name.setLayoutParams(textParams);
            String nameOf = String.valueOf(list[i]);
            String toReplace = (String) (Environment.getExternalStorageDirectory().toString() + "/chefdir/recipes/");
            nameOf = nameOf.replace(toReplace, "");

            name.setText("Рецепт: " + nameOf);
            name.setTextSize(20);

            Button chooseRes = new Button(this);
            chooseRes.setText("Выбрать");

            chooseRes.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v){

                    String data = (String) name_path.getText();

                    Intent i = new Intent(Menu.this, RecipeViewer.class);
                    i.putExtra("Data", data);
                    startActivity(i);

                }

            });


            ll.addView(name);
            ll.addView(name_path);
            ll.addView(chooseRes);
            mainLayout.addView(ll);


        }
    }

}