package com.application.personalchef;

import android.content.Intent;
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
import java.util.Locale;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;

public class RecipeViewer extends AppCompatActivity {

    private TextToSpeech TTS;
    TTSManager ttsManager = null;
    private static final int Print_Words = 100;

    private TextView enteredText;

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
                int time = 0;
                while ((line = bufferedReader.readLine()) != null){
                    Log.d("GG", line);
                    stringBuilder.append(line);
                    if (iterator % 4 == 1){
                        text.setText(line);
                        Log.d("what", line);
                    }
                    if (iterator % 4 == 2){
                        time = Integer.parseInt(line);
                        Log.d("what", line);
                    }
                    if (iterator % 4 == 3){
                        text2.setText(line);
                        Log.d("what", line);
                    }
                    if (iterator % 4 == 0){
                        image.setImageURI(Uri.fromFile(new File(line)));
                        Log.d("what", line);
                        String ttsText = String.valueOf(text.getText());
                        Log.d("what", ttsText);
                        //ttsManager.initQueue(ttsText);
                        //textToSpeech.speak("hi",TextToSpeech.QUEUE_FLUSH,null);
                        int finalTime = time;
                        //SystemClock.sleep(1000 * finalTime);


                    }
                    //text.setText(line);
                    iterator = iterator + 1;

                }
                //text.setText(stringBuilder);
                //text2.setText(stringBuilder);
                Log.d("GG", String.valueOf(stringBuilder));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }











    }




/*    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //Проверяем успешность получения обратного ответа:
        if (requestCode==Print_Words && resultCode==RESULT_OK) {
            //Как результат получаем строковый массив слов, похожих на произнесенное:
            ArrayList<String> result=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            //и отображаем их в элементе TextView:
            enteredText.setText(result.toString());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

*/

}
