package com.sakai.ug.sakaiapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class ResponseSubmittedActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response_submitted);

        TextView t = (TextView) findViewById(R.id.textResult);

    }


}