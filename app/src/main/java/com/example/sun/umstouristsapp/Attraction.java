package com.example.sun.umstouristsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class Attraction extends AppCompatActivity {

    private List<Attraction_Model> dataModelList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Attraction_Adapter mAdapter;

    private ImageView backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        backbtn = (ImageView) findViewById(R.id.backImage);

        mAdapter = new Attraction_Adapter(dataModelList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        setUpRecyclerView();
        setUpListener();
    }

    private void setUpRecyclerView() {

        int[] covers = new int[]{
                R.drawable.ums_dewan_canselor_resize,
                R.drawable.ums_masjid_resize,
                R.drawable.ums_clock_menarajam_resize,
                R.drawable.ums_chancellery,
                R.drawable.ums_aquarium_and_museum,};

        Attraction_Model attraction_model = new Attraction_Model("Chancellor Hall", "Old Building", "1995", covers[0]);
        dataModelList.add(attraction_model);

        attraction_model = new Attraction_Model("Mosque", "Old Building", "1965", covers[1]);
        dataModelList.add(attraction_model);

        attraction_model = new Attraction_Model("Clock Tower", "New Building", "2000", covers[2]);
        dataModelList.add(attraction_model);

        attraction_model = new Attraction_Model("Chancellery", "Old Building", "1985", covers[3]);
        dataModelList.add(attraction_model);

        attraction_model = new Attraction_Model("Aquarium and Museum", "New Building", "2008", covers[4]);
        dataModelList.add(attraction_model);

        mAdapter.notifyDataSetChanged();
    }

    private void setUpListener() {
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(Attraction.this, MainActivity.class);
                startActivity(main);
                finish();
            }
        });
    }

}
