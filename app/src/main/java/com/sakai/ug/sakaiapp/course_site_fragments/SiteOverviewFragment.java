package com.sakai.ug.sakaiapp.course_site_fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sakai.ug.sakaiapp.R;

public class SiteOverviewFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        Bundle bundle2 = this.getArguments();
        String courseid = bundle2.getString("COURSE_IDD");

        Log.d("SiteIDSakai", "Course id: " + courseid );
        return inflater.inflate(R.layout.fragment_site_overview, container, false);
    }
}
