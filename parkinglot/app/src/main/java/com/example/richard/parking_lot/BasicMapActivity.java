package com.example.richard.parking_lot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by richard on 2/16/2016.
 */
public class BasicMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Intent intent;

    private static final LatLng Parking_Structure = new LatLng(34.060318, -117.816793);
    private static final LatLng A = new LatLng(34.060525, -117.824564);
    private static final LatLng B = new LatLng(34.052760, -117.815332);
    private static final LatLng C = new LatLng(34.058598, -117.819093);
    private static final LatLng E1 = new LatLng(34.061544, -117.811581);
    private static final LatLng E2 = new LatLng(34.060737, -117.812638);

    protected static final LatLng[] latLngs = {Parking_Structure,A,B,C,E1,E2 };

    protected static final String[] lotName = {"Parking Structure",
                                            "Parking Lot A",
                                            "Parking Lot B",
                                            "Parking Lot C",
                                            "Parking Lot E1",
                                            "Parking Lot E2"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_demo);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        intent = getIntent();
       int position = intent.getIntExtra("position",0);
        System.out.println("1234abcd"+ position);
        mMap.addMarker(new MarkerOptions().position(latLngs[position]).title(lotName[position]));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLngs[position]));
    }
}
