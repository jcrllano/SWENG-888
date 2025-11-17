package com.example.journeytales;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapLocation extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap gMap;
    private LatLng selectedLatLng = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_location);

        // Get map fragment
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        } else {
            Toast.makeText(this, "Error loading map!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;

        // DEFAULT camera position (USA zoom)
        LatLng defaultLocation = new LatLng(39.8283, -98.5795); // USA center
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 4f));

        Toast.makeText(this, "Tap a location on the map", Toast.LENGTH_SHORT).show();

        // When user taps the map
        gMap.setOnMapClickListener(latLng -> {

            // Remember selected location
            selectedLatLng = latLng;

            // Remove old markers and add new one
            gMap.clear();
            gMap.addMarker(new MarkerOptions().position(latLng).title("Selected Location"));

            // Return the result immediately
            Intent returnIntent = new Intent();
            returnIntent.putExtra("lat", latLng.latitude);
            returnIntent.putExtra("lng", latLng.longitude);

            setResult(RESULT_OK, returnIntent);
            finish();
        });
    }
}
