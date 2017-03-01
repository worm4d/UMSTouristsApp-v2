package com.example.sun.umstouristsapp;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends RuntimePermissionsActivity{

    Button newAttraction, newFacility, newAdmin, newPackage, newEVIC;
    Button camera, map;
    ImageView menu;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;

    int IMAGES[] = {R.drawable.ad_1, R.drawable.ad_2, R.drawable.ad_3, R.drawable.ad_4, R.drawable.ad_5, };
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();

    private static final int REQUEST_PERMISSIONS = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        newAttraction = (Button) findViewById(R.id.main_attraction);
        newFacility = (Button) findViewById(R.id.main_facility);
//        newAdmin = (Button) findViewById(R.id.main_admin);
        newPackage = (Button) findViewById(R.id.main_packages);
        newEVIC = (Button) findViewById(R.id.main_evic);
        camera = (Button) findViewById(R.id.main_camera);
        map = (Button) findViewById(R.id.main_map);
        menu = (ImageView) findViewById(R.id.main_menu);

        final PrimaryDrawerItem item1 = new PrimaryDrawerItem().withName("Home");
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withName("Settings");
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withName("Help");
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withName("Contact");
        final PrimaryDrawerItem item5 = new PrimaryDrawerItem().withName("Exit");


        new DrawerBuilder()
                .withActivity(MainActivity.this)
                .withActionBarDrawerToggle(true)
                .withActionBarDrawerToggleAnimated(true)
                .addDrawerItems(item1,item2,item3,item4,item5)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        item5.withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                            @Override
                            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                                onBackPressed();
                                return false;
                            }
                        });

                        return false;
                    }
                })
                .build();


        newAttraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Attraction.class);
                startActivity(intent);
            }
        });

        newFacility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Facility.class);
                startActivity(intent);
            }
        });

//        newAdmin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this,Admin.class);
//                startActivity(intent);
//            }
//        });

        newPackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Packages.class);
                startActivity(intent);
            }
        });

        newEVIC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EvicUms.class);
                startActivity(intent);
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(intent);
            }
        });

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
//                // Create a Uri from an intent string. Use the result to create an Intent.
//                Uri gmmIntentUri = Uri.parse("geo:<6.0362>,<116.1188>(Label+Name)");
//
//                // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
//                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//                // Make the Intent explicit by setting the Google Maps package
//                mapIntent.setPackage("com.google.android.apps.maps");
//
//                // Attempt to start an activity that can handle the Intent
//                startActivity(mapIntent);
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });



    }

    @Override
    public void onPermissionsGranted(final int requestCode) {
        Toast.makeText(this, "Permissions Received.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Are you sure to exit this application?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

//                        onStop();
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
                        startActivity(intent);
                        finish();
                        System.exit(0);
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();

    }

//    private void clickMenu() {
//        //if you want to update the items at a later time it is recommended to keep it in a variable
//        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("home");
//        SecondaryDrawerItem item2 = new SecondaryDrawerItem().withIdentifier(2).withName("settings");
//
////create the drawer and remember the `Drawer` result object
//        Drawer result = new DrawerBuilder()
//                .withActivity(this)
//                .withTranslucentStatusBar(false)
//                .withActionBarDrawerToggle(false)
//                .addDrawerItems(
//                        item1,
//                        new DividerDrawerItem(),
//                        item2,
//                        new SecondaryDrawerItem().withName("settings")
//                )
//                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
//                    @Override
//                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
//                        // do something with the clicked item :D
//                        return false;
//                    }
//                })
//                .build();
//    }

    private void init() {

        MainActivity.super.requestAppPermissions(new
                        String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION}, R.string
                        .runtime_permissions_txt
                , REQUEST_PERMISSIONS);

        for(int i=0;i<IMAGES.length;i++)
            ImagesArray.add(IMAGES[i]);

        mPager = (ViewPager) findViewById(R.id.pager);


        mPager.setAdapter(new SlidingImage_Adapter(MainActivity.this,ImagesArray));


        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES =IMAGES.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }
}
