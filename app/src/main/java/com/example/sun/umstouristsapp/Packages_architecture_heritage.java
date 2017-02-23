package com.example.sun.umstouristsapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Packages_architecture_heritage extends AppCompatActivity {

    private TextView detailsTxt;
    private ImageView backBtn;
    private Button venues, charge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packages_architecture_heritage);


        detailsTxt = (TextView) findViewById(R.id.details_txt);
        detailsTxt.setMovementMethod(new ScrollingMovementMethod());

        venues = (Button) findViewById(R.id.main_venues);
        charge = (Button) findViewById(R.id.main_charge);

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
        venues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String names[] ={"- Clock Tower;", "- UMS Mosque;", "- Chancellor Hall;", "- Chancellery Building;", "- UMS Jetty."};
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Packages_architecture_heritage.this);
                alertDialogBuilder.setTitle("Venues:");
                alertDialogBuilder.setItems(names, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        charge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String names[] ={"RM 53.00 (A)/pax","RM 15.00 (C)/pax"," ", "Duration 2 - 3 hours"};
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Packages_architecture_heritage.this);
                alertDialogBuilder.setTitle("Charge:");
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
