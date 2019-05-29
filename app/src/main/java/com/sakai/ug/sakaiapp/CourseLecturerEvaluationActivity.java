package com.sakai.ug.sakaiapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sakai.ug.sakaiapp.database.SakaiDatabase;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;


public class CourseLecturerEvaluationActivity extends AppCompatActivity {
    List<Question> quesList;
    int qid = 0;
    Question currentQ;
    TextView txtQuestion;
    RadioButton rda, rdb, rdc, rdd, rde;
    Button butNext;
    SakaiDatabase db;
    String courseid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_lecturer_evalaution);

        Bundle bundle = this.getIntent().getExtras();
        courseid = bundle.getString("COURSE_ID");

        db = new SakaiDatabase(this);
        quesList = db.getAllQuestions();
        Log.d("Question list", "onCreate: " + quesList);
        currentQ = quesList.get(qid);
        txtQuestion = findViewById(R.id.textView1);
        rda = findViewById(R.id.radio0);
        rdb = findViewById(R.id.radio1);
        rdc = findViewById(R.id.radio2);
        rdd = findViewById(R.id.radio3);
        rde = findViewById(R.id.radio4);
        butNext = findViewById(R.id.button1);
        setQuestionView();
        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup grp = findViewById(R.id.radioGroup1);
                RadioButton answer = findViewById(grp.getCheckedRadioButtonId());

                if (answer != null) {
                    grp.clearCheck();
                    Log.d("response", answer.getText().toString());

                    db.addResponse(answer.getText().toString(), String.valueOf(qid), courseid);
                    if (qid < 10) {
                        currentQ = quesList.get(qid);
                        setQuestionView();
                    } else {
                        Intent intent = new Intent(CourseLecturerEvaluationActivity.this, ResponseSubmittedActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please make a selection", LENGTH_SHORT);
                }

            }
        });

        final Toolbar toolbar = findViewById(R.id.toolbarCLE);
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


    private void setQuestionView() {
        txtQuestion.setText(currentQ.getQUESTION());
        rda.setText(currentQ.getOPTA());
        rdb.setText(currentQ.getOPTB());
        rdc.setText(currentQ.getOPTC());
        rdd.setText(currentQ.getOPTD());
        rde.setText(currentQ.getOPTE());
        qid++;
    }
}