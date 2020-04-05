package com.gdg.feedbackapp;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class GDGWebFeedBackFetcher extends AppCompatActivity {

    String gdgfeedbackurl="https://raw.githack.com/sourabhsahu123/gdgcontent/master/gdg.json";
    FeedbackAdapter fadapter;
    RecyclerView feedbackRCV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gdgweb_feed_back_fetcher);


        feedbackRCV = (RecyclerView)findViewById(R.id.feedbackRecyclerView1);
        feedbackRCV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));



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

        ArrayList<GDGFeedback>fList = fetchDataFromWeb(gdgfeedbackurl);
        System.out.println(fList);
    }


    public ArrayList<GDGFeedback> fetchDataFromWeb(String feedbackUrl){

        final ArrayList<GDGFeedback> gfList= new ArrayList<GDGFeedback>();
        //
       StringRequest stringRequest = new StringRequest(Request.Method.GET, feedbackUrl, new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {
               try {
                   JSONObject jso = new JSONObject(response);
                   JSONArray jr = jso.getJSONArray("feedback");
                   for (int i =0;i<jr.length();i++){

                       JSONObject jso1 = jr.getJSONObject(i);
                       GDGFeedback gf = new GDGFeedback(jso1.getString("name"),jso1.getString("occupation"),jso1.getInt("rating"),
                               jso1.getString("qualification"),jso1.getString("suggestion"),jso1.getInt("age"),false);
                       gfList.add(gf);
                       fadapter = new FeedbackAdapter(gfList);
                       feedbackRCV.setAdapter(fadapter);
                       fadapter.notifyDataSetChanged();
                   }


                   System.out.print(jso.toString());
               } catch (Exception ex) {
                    System.out.println(ex.toString());
               }
           }

       },
               new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {
                       System.out.println(error.toString());
                   }
               }

       );
//Create a Queue
        RequestQueue reqQueue= Volley.newRequestQueue(this);
     //add a request to the queue
        reqQueue.add(stringRequest);
       // StringRequest stringRequest = new StringRequest()

    return  gfList;
    }
}
