package com.example.sun.umstouristsapp;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sun.umstouristsapp.adapter.ChancelleryBuildingAdapter;
import com.example.sun.umstouristsapp.adapter.ChancellorHallAdapter;

public class Attraction_chancellery_gallery extends AppCompatActivity {

    ChancelleryBuildingAdapter adapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_chancellery_gallery);

        viewPager =(ViewPager)findViewById(R.id.view_pager);
        adapter = new ChancelleryBuildingAdapter(this);
        viewPager.setAdapter(adapter);
    }
}
