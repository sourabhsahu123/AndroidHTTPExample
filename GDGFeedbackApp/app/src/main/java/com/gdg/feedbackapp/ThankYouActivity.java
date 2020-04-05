package com.gdg.feedbackapp;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class ThankYouActivity extends AppCompatActivity {

    TextView thankyouTV;
    ArrayList<GDGFeedback> gfList;
    FeedbackAdapter fadapter;
    RecyclerView feedbackRCV;
    SharedPreferences mypref=null;
    DBHelper dbHelper =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mypref=getSharedPreferences("MYPREF",MODE_PRIVATE);
       // thankyouTV = (TextView)findViewById(R.id.thankyouTV);
        // initialize the array list
        gfList = new ArrayList<GDGFeedback>();
     //  String name= getIntent().getStringExtra("name");
       //get the object from mainactivity
    //   GDGFeedback gf = (GDGFeedback) getIntent().getSerializableExtra("feedback");


      //  gfList.add(gf); // add the object into the list

        String n = mypref.getString("name","guest");
        int a = mypref.getInt("age",18);
        String occupation = mypref.getString("occupation","student");
        String sug = mypref.getString("suggestion","Good");
        int rat = mypref.getInt("rating",2);
        String qual = mypref.getString("qualification","Graduate");

        GDGFeedback g2 = new GDGFeedback(n,occupation,rat,qual,sug,a,false);
        gfList.add(g2);
        dbHelper = new DBHelper(this);
        gfList = dbHelper.getAllFeedBack();
        fadapter = new FeedbackAdapter(gfList);
        feedbackRCV = (RecyclerView)findViewById(R.id.feedbackRecyclerView);
        feedbackRCV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        feedbackRCV.setAdapter(fadapter);
        fadapter.notifyDataSetChanged();




    //   thankyouTV.setText(thankyouTV.getText()+" "+name);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public  void setGlist(ArrayList<GDGFeedback> gFList){
        gfList=gFList;
    }

}
