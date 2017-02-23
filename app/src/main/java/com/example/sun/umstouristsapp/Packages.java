package com.example.sun.umstouristsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Packages extends AppCompatActivity {

    private List<Packages_Model> modelList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Packages_Adapter adapter;
    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packages);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        backBtn = (ImageView) findViewById(R.id.backImage);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        adapter = new Packages_Adapter(modelList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        setUpRecyclerView();
        setUpListener();
    }

    private void setUpRecyclerView() {

        int[] covers = new int[]{
                R.drawable.package_museum,
                R.drawable.package_nature_delight,
                R.drawable.package_architecture,
                R.drawable.package_sunset,
                R.drawable.package_education_camp,};

        Packages_Model packages_model = new Packages_Model("Gallery & Museum Tour", "Old Building", "1990", covers[0]);
        modelList.add(packages_model);

        packages_model = new Packages_Model("Nature Delight Tour", "Old Building", "1990", covers[1]);
        modelList.add(packages_model);

        packages_model = new Packages_Model("Architecture Heritage", "New Building", "1990", covers[2]);
        modelList.add(packages_model);

        packages_model = new Packages_Model("Sunset Tour", "Old Building", "1990", covers[3]);
        modelList.add(packages_model);

        packages_model = new Packages_Model("Nature Education Camp", "New Building", "1990", covers[4]);
        modelList.add(packages_model);

        adapter.notifyDataSetChanged();

    }

    private void setUpListener() {
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(Packages.this, MainActivity.class);
                startActivity(main);
                finish();
            }
        });
    }
}







