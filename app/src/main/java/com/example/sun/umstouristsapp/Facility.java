package com.example.sun.umstouristsapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Facility extends AppCompatActivity {

    Button diningBtn, restroomBtn, bankBtn, busstopBtn;
    ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facility);

        backBtn = (ImageView) findViewById(R.id.backImage);

        diningBtn = (Button) findViewById(R.id.dining_logo);
        restroomBtn = (Button) findViewById(R.id.restroom_logo);
        bankBtn = (Button) findViewById(R.id.bank_logo);
        busstopBtn = (Button) findViewById(R.id.bus_stop_logo);

        diningBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Facility.this, Facility_cafe.class);
                startActivity(intent);
            }
        });

        restroomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Facility.this, Facility_restroom.class);
                startActivity(intent);
            }
        });

        bankBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Facility.this, Facility_atm.class);
                startActivity(intent);
            }
        });

        busstopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Facility.this, Facility_busstop.class);
                startActivity(intent);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
