package com.example.sun.umstouristsapp;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.OnMapReadyCallback;

public class Attraction_mosque extends AppCompatActivity {

    private TextView detailsTxt;
    private ImageView backBtn;
    private Button map, gallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_mosque);

        detailsTxt = (TextView) findViewById(R.id.details_txt);
        detailsTxt.setMovementMethod(new ScrollingMovementMethod());

        map = (Button) findViewById(R.id.main_map);
        gallery = (Button) findViewById(R.id.main_gallery);

        backBtn = (ImageView) findViewById(R.id.backImage);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        setUpListener();
    }

    private void setUpListener() {
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Attraction_mosque.this, Attraction_mosque_map.class);
                startActivity(intent);
            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Attraction_mosque.this);
                alertDialogBuilder.setTitle("Error");
                alertDialogBuilder.setMessage("No Image Found");
                alertDialogBuilder.setIcon(R.drawable.ic_error_black_24dp);
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

    }
}
