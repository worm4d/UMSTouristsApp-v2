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

public class Facility_busstop extends AppCompatActivity implements OnMapReadyCallback{

    private GoogleMap googleMap;
    LatLngBounds.Builder builder;
    CameraUpdate cu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facility_busstop);

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
        Marker fkjBus = googleMap.addMarker(new MarkerOptions().position(new LatLng(
                6.033830, 116.122356)).title("Faculty of Engineering Bus Stop").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
        Marker fssaBus = googleMap.addMarker(new MarkerOptions().position(new LatLng(
                6.033545, 116.122048)).title("Faculty of Science and Natural Resources Bus Stop").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
        Marker libraryBus = googleMap.addMarker(new MarkerOptions().position(new LatLng(
                6.033285, 116.118011)).title("Library Bus Stop").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
        Marker ppibBus = googleMap.addMarker(new MarkerOptions().position(new LatLng(
                6.032622, 116.117076)).title("PPIB Bus Stop").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
        Marker fpepBus = googleMap.addMarker(new MarkerOptions().position(new LatLng(
                6.033257, 116.113075)).title("Fakulti Perniagaan, Ekonomi dan Perakaunan Bus Stop").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
        Marker fkiBus = googleMap.addMarker(new MarkerOptions().position(new LatLng(
                6.036402, 116.121112)).title("Faculty of Computing and Informatic Bus Stop ").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
        Marker onestopBus = googleMap.addMarker(new MarkerOptions().position(new LatLng(
                6.036442, 116.120924)).title("One Stop Centre Bus Stop").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));

        /**Put all the markers into arraylist*/
        markersList.add(fkjBus);
        markersList.add(fssaBus);
        markersList.add(libraryBus);
        markersList.add(ppibBus);
        markersList.add(fpepBus);
        markersList.add(fkiBus);
        markersList.add(onestopBus);



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
