package com.example.richard.parking_lot;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by richard on 2/19/2016.
 */
public class WholeMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    BasicMapActivity basicMapActivity = new BasicMapActivity();


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

        for (int i = 0; i < 5; i++) {
            mMap.addMarker(new MarkerOptions().position(basicMapActivity.latLngs[i]).
                    title(basicMapActivity.lotName[i]));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(basicMapActivity.latLngs[i]));
        }
    }

}
