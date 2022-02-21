package com.application.personalchef;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;

public class RecipeViewer extends AppCompatActivity {

    private TextToSpeech TTS;
    TTSManager ttsManager = null;
    private static final int Print_Words = 100;

    private TextView enteredText;
    List<String> steps = new ArrayList<String>();

    int time;

    int stepper = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipeviewer);
        getSupportActionBar().hide();

        String data = getIntent().getExtras().getString("Data");
        Log.d("GG", data);


        TextView text = (TextView) findViewById(R.id.textView3);
        TextView text2 = (TextView) findViewById(R.id.textView2);
        ImageView image = (ImageView) findViewById(R.id.imageforpic);
        Button next = (Button) findViewById(R.id.next);
        Button leave = (Button) findViewById(R.id.leave);

        ttsManager = new TTSManager();
        ttsManager.init(this);



        File myFile = new File(data);
        try {
            FileInputStream inputStream = new FileInputStream(myFile);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            try {
                int iterator = 1;
                while ((line = bufferedReader.readLine()) != null){
                    steps.add(line);
                    Log.d("GG", String.valueOf(steps));
                    stringBuilder.append(line);

                    iterator = iterator + 1;

                }

                Log.d("GG", String.valueOf(stringBuilder));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }





    text.setText(steps.get(0));
    time = Integer.parseInt(steps.get(1));
    text2.setText(steps.get(2));
    image.setImageURI(Uri.fromFile(new File(steps.get(3))));


        if (steps.size() == stepper + 0){
            text.setText("Финиш!");
            text2.setText("");
            image.setImageResource(R.drawable.backgr);
        }
    next.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v)
        {
            if (steps.size() == stepper - 4){
                finish();

            }
            else if (steps.size() == stepper + 0){
                text.setText("");
                text2.setText("");
                next.setText("Финиш!");
                image.setImageResource(R.drawable.finally_pic);

            }
            else {
                text.setText(steps.get(0 + stepper));
                time = Integer.parseInt(steps.get(1 + stepper));
                text2.setText(steps.get(2 + stepper));
                image.setImageURI(Uri.fromFile(new File(steps.get(3 + stepper))));
                String textic = (String) text.getText();
                ttsManager.initQueue(textic);

            }
            stepper = stepper + 4;
        }
    });

        leave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                finish();
            }
        });





    }






}
