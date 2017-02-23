package com.example.sun.umstouristsapp;

import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import static com.example.sun.umstouristsapp.R.id.map;

public class Facility_cafe extends AppCompatActivity implements OnMapReadyCallback{

    private GoogleMap googleMap;
    LatLngBounds.Builder builder;
    CameraUpdate cu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facility_cafe);

        /**get the reference of map from layout*/
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {

        googleMap = map;

        mSetUpMap();
    }

    /**create method to set map view*/
    public void mSetUpMap() {

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMap.setMyLocationEnabled(true);
        googleMap.setTrafficEnabled(true);
        googleMap.setIndoorEnabled(true);
        googleMap.setBuildingsEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        /**clear the map before redraw to them*/
        googleMap.clear();
        /**Create dummy Markers List*/
        List<Marker> markersList = new ArrayList<>();
        Marker MarineCafe = googleMap.addMarker(new MarkerOptions().position(new LatLng(
                6.039552, 116.113189)).title("The Marine Cafe").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        Marker AnugerahCafe = googleMap.addMarker(new MarkerOptions().position(new LatLng(
                6.042940, 116.124398)).title("Anugerah Rasa Cafe").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        Marker LibraryKiosk = googleMap.addMarker(new MarkerOptions().position(new LatLng(
                6.033423, 116.117930)).title("UMS Library Kiosk").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        Marker KampungABCafe = googleMap.addMarker(new MarkerOptions().position(new LatLng(
                6.041731, 116.123386)).title("Kolej Kediaman AB Cafe").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        Marker MyKitchen = googleMap.addMarker(new MarkerOptions().position(new LatLng(
                6.036585, 116.120312)).title("My Kitchen").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        Marker KampungECafe = googleMap.addMarker(new MarkerOptions().position(new LatLng(
                6.046561, 116.126077)).title("Kolej Kediaman E Cafe").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        Marker DKPBaruKiosk = googleMap.addMarker(new MarkerOptions().position(new LatLng(
                6.045306, 116.129653)).title("DKP Baru Kiosk").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));




        /**Put all the markers into arraylist*/
        markersList.add(MarineCafe);
        markersList.add(AnugerahCafe);
        markersList.add(LibraryKiosk);
        markersList.add(KampungABCafe);
        markersList.add(KampungECafe);
        markersList.add(MyKitchen);
        markersList.add(DKPBaruKiosk);



        /**create for loop for get the latLngbuilder from the marker list*/
        builder = new LatLngBounds.Builder();
        for (Marker m : markersList) {
            builder.include(m.getPosition());
        }
        /**initialize the padding for map boundary*/
        int padding = 50;
        /**create the bounds from latlngBuilder to set into map camera*/
        LatLngBounds bounds = builder.build();
        /**create the camera with bounds and padding to set into map*/
        cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        /**call the map call back to know map is loaded or not*/
        googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                /**set animated zoom camera into map*/
                googleMap.moveCamera(cu);

            }
        });
    }
}
