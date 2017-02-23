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

public class Packages_education_camp extends AppCompatActivity {

    private TextView detailsTxt;
    private ImageView backBtn;
    private Button charge, venues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packages_education_camp);

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
                String names[] ={"- UMS Hill", " ", "Implementers:", "- ITBC;", "- FSSA;", "- EcoCampus Mgmt. Centre"};
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Packages_education_camp.this);
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
                String names[] ={"{Depends on camp duration and specificity}"};
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Packages_education_camp.this);
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
