package com.sakai.ug.sakaiapp.course_site_details;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.sakai.ug.sakaiapp.R;
import com.sakai.ug.sakaiapp.course_site_fragments.AssignmentsFragment;
import com.sakai.ug.sakaiapp.course_site_fragments.SiteOverviewFragment;

public class AssignmentDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_detail);

        final String assignmenttitle = getIntent().getStringExtra("AS_TITLE");
        final String assignmentduedate = getIntent().getStringExtra("AS_DUEDATE");
        final String status = getIntent().getStringExtra("AS_STATUS");
        final String instructions = getIntent().getStringExtra("AS_INSTRUCTION");

        final Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Assignment detail");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView as_title = findViewById(R.id.assignmenttitletext);
        as_title.setText(assignmenttitle);
        TextView as_duedate = findViewById(R.id.textView3);
        as_duedate.setText(assignmentduedate);
        TextView as_status = findViewById(R.id.textView5);
        as_status.setText(status);
        TextView as_instructions = findViewById(R.id.instructionContent);
        as_instructions.setText(Html.fromHtml(instructions));

    }
}