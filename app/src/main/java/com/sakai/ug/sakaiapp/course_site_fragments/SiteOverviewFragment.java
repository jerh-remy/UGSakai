package com.sakai.ug.sakaiapp.course_site_fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sakai.ug.sakaiapp.R;

public class SiteOverviewFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_site_overview, container, false);


        Bundle bundle2 = this.getArguments();
        String courseid = bundle2.getString("COURSE_ID");
        String coursedescription = bundle2.getString("COURSE_DESCRIPTION");
        String coursetitle = bundle2.getString("COURSE_TITLE");

        TextView courseTitle = view.findViewById(R.id.course_code);
        TextView courseDescription = view.findViewById(R.id.course_desc_body);
        courseTitle.setText(coursetitle);

        if (coursedescription != null) {
            courseDescription.setText(Html.fromHtml(coursedescription));
        } else {
            courseDescription.setText("No course description available");
        }

        Log.d("SiteIDSakai", "Course id: " + courseid);
        return view;
    }
}
