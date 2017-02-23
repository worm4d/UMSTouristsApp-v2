package com.example.sun.umstouristsapp;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import static com.example.sun.umstouristsapp.R.id.map;

public class MapsActivity extends Activity implements OnMapReadyCallback {

    private GoogleMap googleMap;
    LatLngBounds.Builder builder;
    CameraUpdate cu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

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
        Marker AquariumMarineMuseum = googleMap.addMarker(new MarkerOptions().position(new LatLng(
                6.039858, 116.112751)).title("Aquarium & Marine Museum"));
        Marker DewanCanselor = googleMap.addMarker(new MarkerOptions().position(new LatLng(
                6.036401, 116.118580)).title("Dewan Canselor UMS"));
        Marker CanseloriUMS = googleMap.addMarker(new MarkerOptions().position(new LatLng(
                6.036503, 116.115639)).title("Canselori UMS"));
        Marker LibraryUMS = googleMap.addMarker(new MarkerOptions().position(new LatLng(
                6.034358, 116.117660)).title("UMS Library"));
        Marker OdecUMS = googleMap.addMarker(new MarkerOptions().position(new LatLng(
                6.043048, 116.111757)).title("UMS ODEC Beach"));
        Marker UMSPeak = googleMap.addMarker(new MarkerOptions().position(new LatLng(
                6.042461, 116.119541)).title("UMS Peak"));
        Marker MosqueUMS = googleMap.addMarker(new MarkerOptions().position(new LatLng(
                6.038148, 116.125190)).title("Mosque UMS"));
        Marker ClockTowerUMS = googleMap.addMarker(new MarkerOptions().position(new LatLng(
                6.034606, 116.119632)).title("Clock Tower UMS"));
        Marker EVIC = googleMap.addMarker(new MarkerOptions().position(new LatLng(
                6.032509, 116.121645)).title("EcoCampus Visitor Information Centre"));
        Marker KompleksSukan = googleMap.addMarker(new MarkerOptions().position(new LatLng(
                6.042852, 116.127930)).title("Kompleks Sukan UMS"));

        /**Put all the markers into arraylist*/
        markersList.add(AquariumMarineMuseum);
        markersList.add(DewanCanselor);
        markersList.add(CanseloriUMS);
        markersList.add(LibraryUMS);
        markersList.add(OdecUMS);
        markersList.add(UMSPeak);
        markersList.add(MosqueUMS);
        markersList.add(ClockTowerUMS);
        markersList.add(EVIC);
        markersList.add(KompleksSukan);

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
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
                        6.036401, 116.118580),17));

            }
        });
    }


}
