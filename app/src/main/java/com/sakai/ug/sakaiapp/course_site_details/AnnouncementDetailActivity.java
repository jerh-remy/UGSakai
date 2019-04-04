package com.sakai.ug.sakaiapp.course_site_details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.sakai.ug.sakaiapp.R;

public class AnnouncementDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_detail);

        final String announcementTitle = getIntent().getStringExtra("AN_TITLE");
        final String savedBy = getIntent().getStringExtra("AN_SAVED_BY");
        final String announcementBody = getIntent().getStringExtra("AN_BODY");

        final Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Announcement detail");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView title = findViewById(R.id.textView6);
        title.setText(announcementTitle);
        TextView saved_by = findViewById(R.id.savedby);
        saved_by.setText(savedBy);
        TextView body = findViewById(R.id.ann_body);
        body.setText(Html.fromHtml(announcementBody));
    }
}
