package com.application.personalchef;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

public class ImageCreator extends AppCompatActivity {

    private final static String FILE_NAME = "content.txt";
    static final int GALLERY_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagecreator);
        getSupportActionBar().hide();
        ImageView imageView = (ImageView) findViewById(R.id.imageView2);
        Log.d("LOL", Environment.getExternalStorageDirectory().toString() + "/chefdir");
        Button button = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavePicture(imageView, (Environment.getExternalStorageDirectory().toString() + "/chefdir/images"));
                Log.d("LOL", SavePicture(imageView, (Environment.getExternalStorageDirectory().toString() + "/chefdir/images")));
                Toast.makeText(getApplicationContext(),"Сохранено успешно!", Toast.LENGTH_SHORT).show();
                closeActivity();
            }
        });
    }

    private void closeActivity() {
        this.finish();
    }

    private String SavePicture(ImageView iv, String folderToSave)
    {

        OutputStream fOut = null;
        Time time = new Time();
        time.setToNow();

        try {
            File file = new File(folderToSave, Integer.toString(time.year) + Integer.toString(time.month) + Integer.toString(time.monthDay) + Integer.toString(time.hour) + Integer.toString(time.minute) + Integer.toString(time.second) +".jpg");
            fOut = new FileOutputStream(file);

            Bitmap bitmap = ((BitmapDrawable) iv.getDrawable()).getBitmap();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
            fOut.flush();
            fOut.close();
            MediaStore.Images.Media.insertImage(getContentResolver(), file.getAbsolutePath(), file.getName(),  file.getName());
        }
        catch (Exception e)
        {
            return e.getMessage();
        }
        return "";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        Bitmap bitmap = null;


        switch (requestCode) {
            case GALLERY_REQUEST:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    Log.d("LOL", imageReturnedIntent.getData().toString());
                    ImageView imageView = (ImageView) findViewById(R.id.imageView2);
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    imageView.setImageBitmap(bitmap);
                }
        }

    }
}