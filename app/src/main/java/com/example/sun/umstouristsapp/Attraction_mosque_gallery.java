package com.example.sun.umstouristsapp;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sun.umstouristsapp.adapter.ChancellorHallAdapter;
import com.example.sun.umstouristsapp.adapter.MosqueAdapter;

public class Attraction_mosque_gallery extends AppCompatActivity {

    MosqueAdapter adapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_mosque_gallery);

        viewPager =(ViewPager)findViewById(R.id.view_pager);
        adapter = new MosqueAdapter(this);
        viewPager.setAdapter(adapter);
    }
}
