package com.example.sun.umstouristsapp;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class MainActivity extends RuntimePermissionsActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{

    Button newAttraction, newFacility, newAdmin, newPackage, newEVIC;
    Button camera, map;
    Toolbar toolbar;
    ImageView imageView, menu;
    private SliderLayout sliderLayout;
    private DatabaseReference mFirebaseDatabase;

    private int REQUEST_CAMERA = 0;

    private static final int REQUEST_PERMISSIONS = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setTitleTextColor(-1);
        sliderLayout = (SliderLayout) findViewById(R.id.slideshow);
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference().child("advertisement");
        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String ad_0 = dataSnapshot.child("0").child("ad_ImageURL").getValue().toString();
                String ad_1 = dataSnapshot.child("1").child("ad_ImageURL").getValue().toString();
                String ad_2 = dataSnapshot.child("2").child("ad_ImageURL").getValue().toString();
                String ad_3 = dataSnapshot.child("3").child("ad_ImageURL").getValue().toString();
                String ad_4 = dataSnapshot.child("4").child("ad_ImageURL").getValue().toString();

                HashMap<String,String> url_maps = new HashMap<String, String>();
                url_maps.put("Hannibal", ad_3);
                url_maps.put("Big Bang Theory", ad_1);
                url_maps.put("House of Cards", ad_4);
                url_maps.put("Game of Thrones", ad_0);
                url_maps.put("Deadpool", ad_2);

                for(String name : url_maps.keySet()){
                    TextSliderView textSliderView = new TextSliderView(getApplicationContext());
                    // initialize a SliderLayout
                    textSliderView
//                    .description(name)
                            .image(url_maps.get(name))
                            .setScaleType(BaseSliderView.ScaleType.Fit)
                            .setOnSliderClickListener(MainActivity.this);

                    //add your extra information
                    textSliderView.bundle(new Bundle());
                    textSliderView.getBundle()
                            .putString("extra",name);

                    sliderLayout.addSlider(textSliderView);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
//        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(4000);
        sliderLayout.addOnPageChangeListener(this);

        clickMenu();

        newAttraction = (Button) findViewById(R.id.main_attraction);
        newFacility = (Button) findViewById(R.id.main_facility);
        newPackage = (Button) findViewById(R.id.main_packages);
        newEVIC = (Button) findViewById(R.id.main_evic);
        camera = (Button) findViewById(R.id.main_camera);
        map = (Button) findViewById(R.id.main_map);


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
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CAMERA);
            }
        });

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
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

    private void clickMenu() {

        MainActivity.super.requestAppPermissions(new
                        String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION}, R.string
                        .runtime_permissions_txt
                , REQUEST_PERMISSIONS);

        menu = (ImageView) findViewById(R.id.menu);
        menu.setColorFilter(-1);

        final PrimaryDrawerItem item1 = new PrimaryDrawerItem().withName("Home");
        item1.withTextColorRes(R.color.colorButtonGreen).withIcon(R.drawable.ic_home_black_24dp).withIconColor(-1);

        final PrimaryDrawerItem item2 = new PrimaryDrawerItem().withName("Contact Us");
        item2.withTextColorRes(R.color.colorButtonGreen).withIcon(R.drawable.ic_contact_phone_black_24dp).withIconColor(-1);
        item2.withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                Intent viewintent = new Intent(MainActivity.this,SliderContactUs.class);
                startActivity(viewintent);
                return true;
            }
        });

        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withName("About Us");
        item3.withTextColorRes(R.color.colorButtonGreen).withIcon(R.drawable.ic_people_black_24dp).withIconColor(-1);
        item3.withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setTitle("Message from the Vice-Chancellor:");
                alertDialogBuilder.setMessage(getString(R.string.slider_about_us));
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return true;
            }
        });

        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withName("Disclaimer");
        item4.withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setTitle("Terms and Condition:");
                alertDialogBuilder.setMessage(getString(R.string.slider_disclaimer_term_and_condition));
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return true;
            }
        });
        item4.withTextColorRes(R.color.colorButtonGreen).withIcon(R.drawable.ic_insert_comment_black_24dp).withIconColor(-1);

        final PrimaryDrawerItem item5 = new PrimaryDrawerItem().withName("Exit");
        item5.withTextColorRes(R.color.colorButtonGreen).withIcon(R.drawable.ic_cancel_black_24dp).withIconColor(-1);
        item5.withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                onBackPressed();
                return true;
            }
        });

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.visit_ums)
//                .addProfiles(
//                        new ProfileDrawerItem().withName("Universiti Malaysia Sabah").withEmail("www.ums.edu.my").withIcon(getResources().getDrawable(R.drawable.visit_ums))
//                )
//                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
//                    @Override
//                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
//                        return false;
//                    }
//                })
                .build();


        final Drawer result = new DrawerBuilder()
                .withAccountHeader(headerResult)
                .withActivity(MainActivity.this)
                .withActionBarDrawerToggle(true)
                .withActionBarDrawerToggleAnimated(true)
                .addDrawerItems(item1,item2,item4,item5)
                .build();
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.openDrawer();
            }
        });

    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Intent i1=new Intent(MainActivity.this,MyCameraActivity.class);
        i1.putExtra("data",thumbnail);
        startActivity(i1);

    }

    @Override
    protected void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        sliderLayout.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
//        Toast.makeText(this,slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}
}
