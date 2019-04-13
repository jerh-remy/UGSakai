package com.sakai.ug.sakaiapp;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    //@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

    TextView fullname, username;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        fullname = findViewById(R.id.fullname);
        username = findViewById(R.id.userID);

        fullname.setText(SharedPreferencesManager.getInstance(getApplicationContext()).getFullname());
        username.setText(SharedPreferencesManager.getInstance(getApplicationContext()).getUsername());

        final Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setBackground(getResources().getDrawable(R.color.colorPrimary));
        toolbar.setTitle("My Profile");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
