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

public class Facility_restroom extends AppCompatActivity implements OnMapReadyCallback{

    private GoogleMap googleMap;
    LatLngBounds.Builder builder;
    CameraUpdate cu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facility_restroom);

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
        Marker libraryToilet = googleMap.addMarker(new MarkerOptions().position(new LatLng(
                6.034366, 116.117677)).title("UMS Library Rest Room").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
        Marker dcToilet = googleMap.addMarker(new MarkerOptions().position(new LatLng(
                6.036378, 116.118590)).title("UMS Dewan Canselor Rest Room").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
        Marker marineToilet = googleMap.addMarker(new MarkerOptions().position(new LatLng(
                6.039827, 116.112754)).title("UMS Aquarium and Marine Museum Rest Room").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
        Marker biotechToilet = googleMap.addMarker(new MarkerOptions().position(new LatLng(
                6.037297, 116.113357)).title("UMS Biotechnology Research Institute Rest Room").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
        Marker fssaToilet = googleMap.addMarker(new MarkerOptions().position(new LatLng(
                6.032800, 116.120689)).title("Faculty of Science and Natural Resources Rest Room").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
        Marker fpeToilet = googleMap.addMarker(new MarkerOptions().position(new LatLng(
                6.029732, 116.119016)).title("Faculty of Psychology and Education Rest Room").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
        Marker fpepToilet = googleMap.addMarker(new MarkerOptions().position(new LatLng(
                6.032767, 116.112735)).title("Fakulti Perniagaan, Ekonomi dan Perakaunan Rest Room").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));

        /**Put all the markers into arraylist*/
        markersList.add(libraryToilet);
        markersList.add(dcToilet);
        markersList.add(marineToilet);
        markersList.add(biotechToilet);
        markersList.add(fssaToilet);
        markersList.add(fpeToilet);
        markersList.add(fpepToilet);



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
