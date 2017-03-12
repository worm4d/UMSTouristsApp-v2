package com.example.sun.umstouristsapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static android.R.attr.bitmap;

public class MyCameraActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;

    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private Button btnSelect, btnSave;
    private ImageView ivImage;
    private String userChoosenTask;
    private EditText mEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_camera);

        fixMediaDir();

        ivImage = (ImageView) findViewById(R.id.imageview);

        btnSave = (Button) findViewById(R.id.button_save);

        final Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Bitmap image = (Bitmap) extras.get("data");
            if (image != null) {

                Bitmap alteredBitmap = Bitmap.createBitmap(image.getWidth(), image.getHeight(), image.getConfig());
                Canvas canvas = new Canvas(alteredBitmap);
                Point size = new Point();
                this.getWindowManager().getDefaultDisplay().getSize(size);
                int width = size.x;
                int height = size.y;
                Paint paint = new Paint();
                canvas.drawBitmap(image, 0, 0, paint);
                paint.setColor(Color.WHITE);
                paint.setTextSize(20);
                canvas.drawText("UMS SBDTHFYHTGDRFE", 0, alteredBitmap.getHeight(), paint);

                ivImage.setImageBitmap(alteredBitmap);
            }
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivImage.setDrawingCacheEnabled(true);

                String imgSaved = MediaStore.Images.Media.insertImage(
                        getContentResolver(), ivImage.getDrawingCache(),
                        UUID.randomUUID().toString()+".png", "drawing");

                if(imgSaved!=null){
                    Toast savedToast = Toast.makeText(getApplicationContext(),
                            "Drawing saved to Gallery!", Toast.LENGTH_SHORT);
                    savedToast.show();
                }
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                } else {
                    //code for deny
                }
                break;
        }
    }

    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);

    }

    void fixMediaDir() {
        File sdcard = Environment.getExternalStorageDirectory();
        if (sdcard != null) {
            File mediaDir = new File(sdcard, "DCIM/Camera");
            if (!mediaDir.exists()) {
                mediaDir.mkdirs();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Bitmap image = (Bitmap) extras.get("data");
            if (image != null) {
                ivImage.setImageBitmap(image);
            }
        }
    }

//    public Bitmap combineImages(Bitmap background, Bitmap foreground) {
//
//        Point size = new Point();
//        this.getWindowManager().getDefaultDisplay().getSize(size);
//        int width = size.x;
//        int height = size.y;
//
//        Bitmap cs;
//
//        cs = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
//        Canvas comboImage = new Canvas(cs);
//        background = Bitmap.createScaledBitmap(background, width, height, true);
//        comboImage.drawBitmap(background, 0, 0, null);
//        comboImage.drawBitmap(foreground, 0, cs.getHeight(), null);
//
//        return cs;
//    }
//


}
