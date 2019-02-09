package com.sakai.ug.sakaiapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class SyllabusActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setBackground(getResources().getDrawable(R.color.colorPrimary));
        toolbar.setTitle("Syllabus");

        setSupportActionBar(toolbar);
        NavigationDrawerUtil.getDrawer(this,toolbar);
    }
}
