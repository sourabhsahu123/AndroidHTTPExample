package com.gdg.feedbackapp;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import java.util.ArrayList;

public class GDGWebFeedBackFetcher extends AppCompatActivity {

    String gdgfeedbackurl="https://raw.githack.com/sourabhsahu123/gdgcontent/master/gdg.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gdgweb_feed_back_fetcher);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    public ArrayList<GDGFeedback> fetchDataFromWeb(String feedbackUrl){

        ArrayList<GDGFeedback> gfList= new ArrayList<GDGFeedback>();

       // StringRequest stringRequest = new StringRequest()

    return  gfList;
    }
}
