package com.example.sun.umstouristsapp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EvicUms extends AppCompatActivity {

    private TextView detailsTxt;
    private ImageView backBtn;
    private Button activities, opendays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evic_ums);

        detailsTxt = (TextView) findViewById(R.id.details_txt);
        detailsTxt.setMovementMethod(new ScrollingMovementMethod());

        activities = (Button) findViewById(R.id.main_activities);
        opendays = (Button) findViewById(R.id.main_open_days);

        backBtn = (ImageView) findViewById(R.id.backImage);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        setupListener();

    }

    private void setupListener() {
        activities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String names[] ={"- Information centre for visitors about UMS in particular", "- Video Screening", "- Souvenirs shopping", "- Pedal boat rental at EcoCampus Park lake", "- Buggies rental for visitors to explore around the campus", "Entrance Fee: No"};
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(EvicUms.this);
                alertDialogBuilder.setTitle("Activities:");
                alertDialogBuilder.setItems(names, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        opendays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String names[] ={"Monday to Thursday (09.00 am – 12.00 pm & 01.00 pm – 04.00 pm) and Friday (09.00 am – 12.00 pm & 02.00 pm – 04.00 pm) ","Contact: +6088 320 000 EXT. 5156/5152"};
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(EvicUms.this);
                alertDialogBuilder.setTitle("Open Days:");
                alertDialogBuilder.setItems(names, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

    }
}