package com.example.sun.umstouristsapp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Packages_gallery_museum extends AppCompatActivity {

    private TextView detailsTxt;
    private ImageView backBtn;
    private Button venues, charge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packages_gallery_museum);

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
                String names[] ={"- Galeri BORNEENSIS;", "- UMS Marine Museum & Aquarium;", "- UMS Museum;", "- UMS Research Gallery;", "- Medical Museum.", "- Nobel Laurette", "- Premium Collections"};
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Packages_gallery_museum.this);
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
                String names[] ={"RM 85.00 (A)/pax","RM 27.00 (C)/pax"," ", "Duration 3 - 4 hours"};
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Packages_gallery_museum.this);
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
