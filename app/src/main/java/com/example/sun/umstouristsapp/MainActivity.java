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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

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

public class MainActivity extends RuntimePermissionsActivity{

    Button newAttraction, newFacility, newAdmin, newPackage, newEVIC;
    Button camera, map;
    Toolbar toolbar;
    ImageView imageView, menu;
    private int REQUEST_CAMERA = 0;

    private static final int REQUEST_PERMISSIONS = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setTitleTextColor(-1);

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
}
