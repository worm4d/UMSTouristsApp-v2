package com.example.sun.umstouristsapp;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sun.umstouristsapp.adapter.AquariumAdapter;
import com.example.sun.umstouristsapp.adapter.ChancelleryBuildingAdapter;

public class Attraction_aquarium_gallery extends AppCompatActivity {

    AquariumAdapter adapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_aquarium_gallery);

        viewPager =(ViewPager)findViewById(R.id.view_pager);
        adapter = new AquariumAdapter(this);
        viewPager.setAdapter(adapter);
    }
}
