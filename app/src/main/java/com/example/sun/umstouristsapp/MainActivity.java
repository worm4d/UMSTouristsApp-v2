package com.example.sun.umstouristsapp;

import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Location;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.sun.umstouristsapp.service.MyService;
import com.google.android.gms.location.DetectedActivity;
import com.google.android.gms.location.Geofence;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.nlopez.smartlocation.OnActivityUpdatedListener;
import io.nlopez.smartlocation.OnGeofencingTransitionListener;
import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.OnReverseGeocodingListener;
import io.nlopez.smartlocation.SmartLocation;
import io.nlopez.smartlocation.geofencing.model.GeofenceModel;
import io.nlopez.smartlocation.geofencing.utils.TransitionGeofence;
import io.nlopez.smartlocation.location.providers.LocationGooglePlayServicesProvider;

public class MainActivity extends RuntimePermissionsActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{

    Button newAttraction, newFacility, newAdmin, newPackage, newEVIC;
    Button map;
    Toolbar toolbar;
    ImageView imageView, menu, camera;
    private SliderLayout sliderLayout;
    private DatabaseReference mFirebaseDatabase;
    private LocationGooglePlayServicesProvider provider;


    private int REQUEST_CAMERA = 0;

    private static final int REQUEST_PERMISSIONS = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startLocation();

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
        newEVIC = (Button) findViewById(R.id.main_evic);
        camera = (ImageView) findViewById(R.id.main_camera);
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

    private void startLocation(){
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
        provider = new LocationGooglePlayServicesProvider();
        SmartLocation smartLocation = new SmartLocation.Builder(this).logging(true).build();

        smartLocation.location(provider).start(new OnLocationUpdatedListener() {
            @Override
            public void onLocationUpdated(Location location) {
                showLocation(location);
            }
        });
        smartLocation.activity().start(new OnActivityUpdatedListener() {
            @Override
            public void onActivityUpdated(DetectedActivity detectedActivity) {

            }
        });
    }

    private void showLocation(Location location) {
        double currentLatitude = location.getLatitude();
        double currentLongitude = location.getLongitude();
        Location currentLocation = new Location("Current Location");
        currentLocation.setLatitude(currentLatitude);
        currentLocation.setLongitude(currentLongitude);

        Location locationAqua = new Location("Aquarium & Marine Museum");
        locationAqua.setLatitude(6.039858);
        locationAqua.setLongitude(116.112751);

        double distanceAqua = locationAqua.distanceTo(currentLocation);
        if (distanceAqua <= 200) {
            NotificationBuilder("Aquarium & Marine Museum");
            Log.d("smartNum", "distanceAqua: " + distanceAqua);
        }

        Location locationDC = new Location("Dewan Canselor UMS");
        locationDC.setLatitude(6.036401);
        locationDC.setLongitude(116.118580);

        double distanceDC = locationDC.distanceTo(currentLocation);
        if (distanceDC <= 200) {
            NotificationBuilder("Dewan Canselor UMS");
            Log.d("smartNum", "distanceDC: " + distanceDC);
        }

        Location locationCanselori = new Location("Canselori UMS");
        locationCanselori.setLatitude(6.036503);
        locationCanselori.setLongitude(116.115639);

        double distanceCanselori = locationCanselori.distanceTo(currentLocation);
        if (distanceCanselori <= 200) {
            NotificationBuilder("Canselori UMS");
            Log.d("smartNum", "distanceCanselori: " + distanceCanselori);
        }

        Location locationLibrary = new Location("UMS Library");
        locationLibrary.setLatitude(6.034358);
        locationLibrary.setLongitude(116.117660);

        double distanceLibrary = locationLibrary.distanceTo(currentLocation);
        if (distanceLibrary <= 200) {
            NotificationBuilder("UMS Library");
            Log.d("smartNum", "distanceLibrary: " + distanceLibrary);
        }

        Location locationClock = new Location("Clock Tower UMS");
        locationClock.setLatitude(6.034606);
        locationClock.setLongitude(116.119632);

        double distanceClock = locationClock.distanceTo(currentLocation);
        if (distanceClock <= 200) {
            NotificationBuilder("Clock Tower UMS");
            Log.d("smartNum", "distanceClock: " + distanceClock);
        }
        /*
        6.039858, 116.112751)).title("Aquarium & Marine Museum"));
        6.036401, 116.118580)).title("Dewan Canselor UMS"));
        6.036503, 116.115639)).title("Canselori UMS"));
        6.034358, 116.117660)).title("UMS Library"));
        6.043048, 116.111757)).title("UMS ODEC Beach"));
        6.042461, 116.119541)).title("UMS Peak"));
        6.038148, 116.125190)).title("Mosque UMS"));
        6.034606, 116.119632)).title("Clock Tower UMS"));
        6.032509, 116.121645)).title("EcoCampus Visitor Information Centre"));
        6.042852, 116.127930)).title("Kompleks Sukan UMS"));
        */
        if (location != null) {
            final String text = String.format("Latitude %.6f, Longitude %.6f",
                    location.getLatitude(),
                    location.getLongitude());


            float[] dist = new float[1];
            Location.distanceBetween(location.getLatitude(),location.getLongitude(),6.039858, 116.112751,dist);
            if(dist[0]/1000 < 0.2){
                //here your code or alert box for outside 1Km radius area
//                NotificationBuilder();
            }

            Log.d("smartlocation", "showLocation: " + text);

            Log.d("smartDist", "showDist: " + dist[0]/1000);
//            if (location.getLatitude() == 5.00) {
//                NotificationBuilder();
//            }
//            locationText.setText(text);

            // We are going to get the address for the current position
            SmartLocation.with(this).geocoding().reverse(location, new OnReverseGeocodingListener() {
                @Override
                public void onAddressResolved(Location original, List<Address> results) {
                    if (results.size() > 0) {
                        Address result = results.get(0);
                        StringBuilder builder = new StringBuilder(text);
                        builder.append("\n[Reverse Geocoding] ");
                        List<String> addressElements = new ArrayList<>();
                        for (int i = 0; i <= result.getMaxAddressLineIndex(); i++) {
                            addressElements.add(result.getAddressLine(i));
                        }
                        builder.append(TextUtils.join(", ", addressElements));
//                        locationText.setText(builder.toString());
                    }
                }
            });
        } else {
//            locationText.setText("Null location");
        }
    }


    private void NotificationBuilder(String name) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder b = new NotificationCompat.Builder(this);

        b.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("Hearty365")
                .setContentTitle("You are now nearby to")
                .setContentText(name)
                .setDefaults(Notification.DEFAULT_LIGHTS| Notification.DEFAULT_SOUND)
                .setContentIntent(contentIntent)
                .setContentInfo("Info");


        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, b.build());
    }
}
