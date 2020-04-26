package com.gdg.feedbackapp;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;

import androidx.annotation.Nullable;

public class GDGLocationService extends Service implements LocationListener {


    Context mContext;

    static long MIN_DISTANCE_CHANGE_FOR_UPDATES=5; //in meters
    static long MIN_TIME_BW_UPDATE=100*60*1;  //1 min in milliseconds
    LocationManager locationManager;
    boolean isGPSEnabled = false;
    boolean isNetworkEnabled= false;
    boolean canGetLocation =false;
    Location location;
    double latitude;
    double longitude;


    public  GDGLocationService(Context context){
        mContext=context;

    }


    public  Location getLocation(){
        try{
            locationManager=(LocationManager)mContext.getSystemService(LOCATION_SERVICE);

            // is gps enabled
            isGPSEnabled  = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            //getting network status
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if(!isNetworkEnabled && !isGPSEnabled){

            }
            else{
                this.canGetLocation= true;
                if(isNetworkEnabled){
                    try {
                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATE, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                    if(locationManager!=null){
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                        latitude = location.getLatitude();
                        longitude= location.getLongitude();
                    }
                    }
                    catch (SecurityException ee){

                    }
                    }

                if(isGPSEnabled){
                    try {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATE, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                        if(locationManager!=null){
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                            latitude = location.getLatitude();
                            longitude= location.getLongitude();
                        }
                    }
                    catch (SecurityException ee){

                    }
                }

            }


        }catch (Exception ex){

        }

        return  location;
    }

    public double getLatitude(){
        if(location!=null){
            latitude=location.getLatitude();
        }
        return latitude;
    }


    public double getLongitude(){
        if(location!=null){
            longitude=location.getLongitude();
        }
        return longitude;
    }

    public  boolean canGetLocation(){

        return  canGetLocation;
    }

    public  void showSettings(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
        alertDialog.setTitle("Enable GPS Settings");
        alertDialog.setMessage("GPS is not enabled, Opening Settings to enable gps");
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                mContext.startActivity(intent);
            }
        });

        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        alertDialog.show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
