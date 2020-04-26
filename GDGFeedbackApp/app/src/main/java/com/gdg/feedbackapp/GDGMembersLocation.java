package com.gdg.feedbackapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GDGMembersLocation extends AppCompatActivity {

    TextView tvLocation;
    Button btnLocation;
    String mFineLocationPermission= Manifest.permission.ACCESS_FINE_LOCATION;
    String mCoarseLocationPermission=Manifest.permission.ACCESS_COARSE_LOCATION;
    int REQUEST_CODE_PERMISSION =100;
    GDGLocationService gls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gdgmembers_location);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tvLocation=(TextView)findViewById(R.id.mylocationtv);
        btnLocation=(Button)findViewById(R.id.mylocationbtn);

        try{
            if(ActivityCompat.checkSelfPermission(this,mFineLocationPermission)== PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this,mCoarseLocationPermission)== PackageManager.PERMISSION_GRANTED
            ){


            }
            else{
                ActivityCompat.requestPermissions(this, new String[]{mCoarseLocationPermission,mFineLocationPermission},REQUEST_CODE_PERMISSION);
            }

        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gls = new GDGLocationService(GDGMembersLocation.this);
                if(gls.canGetLocation()){
                    double lat = gls.getLatitude();
                    double lon = gls.getLongitude();
                    tvLocation.setText("Your current location is :\n ("+lat+","+lon+")");
                }
                else{
                  //  tvLocation.setText("Your current location is :\n ("+23.777+","+77.555+")");
                    gls.showSettings();
                }
            }
        });

    }

}
