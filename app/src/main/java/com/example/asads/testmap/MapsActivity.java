package com.example.asads.testmap;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = "MapsActivity";
    private GoogleMap mMap;
    private double latitude = 0, longitude = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Intent intent = this.getIntent();
        if(intent.getExtras() != null) {
            latitude = intent.getExtras().getDouble("latitude");
            longitude = intent.getExtras().getDouble("longitude");
        }
        Log.d(TAG, "onCreate: latitude -> " + latitude + ", longitude -> " + longitude);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        } else {
            Toast.makeText(this, "Could not load Map", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng markerLocation = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(markerLocation).title("Marker at (" + latitude + ", " + longitude + ")"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(markerLocation));
    }
}
