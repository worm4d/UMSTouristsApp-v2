package com.example.sun.umstouristsapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Attraction_aquarium_and_museum extends AppCompatActivity {

    private TextView detailsTxt;
    private ImageView backBtn, detailsImage;
    private Button map, gallery;
    private DatabaseReference mFirebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_aquarium_and_museum);

        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference().child("attraction").child("3");
        detailsImage = (ImageView) findViewById(R.id.attraction_detail_imageview);

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
                Intent intent = new Intent(Attraction_aquarium_and_museum.this, Attraction_aquarium_map.class);
                startActivity(intent);
            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Attraction_aquarium_and_museum.this, Attraction_aquarium_gallery.class);
                startActivity(intent);
            }
        });

        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String detailAttraction = dataSnapshot.child("att_Det").getValue().toString();
                String imageAttraction = dataSnapshot.child("att_ImageURL").child("0").getValue().toString();

                detailsTxt.setText(detailAttraction);
                Glide.with(getApplicationContext())
                        .load(imageAttraction)
                        .into(detailsImage)
                ;

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
