package com.gdg.feedbackapp;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GDGDisplayMember extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gdgdisplay_member);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

       GDGLocationService gls = new GDGLocationService(GDGDisplayMember.this);
        if(gls.canGetLocation()){
            double lat = gls.getLatitude();
            double lon = gls.getLongitude();
            LatLng sydney = new LatLng(lat, lon);
            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            //tvLocation.setText("Your current location is :\n ("+lat+","+lon+")");
        }
        else{
          //  tvLocation.setText("Your current location is :\n ("+23.777+","+77.555+")");
             gls.showSettings();
          //  LatLng sydney = new LatLng(23.182859, 77.454526);
         //   mMap.addMarker(new MarkerOptions().position(sydney).title("Appointy office"));
          //  mMap.addCircle(new CircleOptions().center(sydney).radius(10));
          //  mMap.setMaxZoomPreference(16);
          //  mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }

    }
}
