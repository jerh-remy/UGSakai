package com.sakai.ug.sakaiapp.main_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.sakai.ug.sakaiapp.CourseSiteActivity;
import com.sakai.ug.sakaiapp.R;
import com.sakai.ug.sakaiapp.adapters.AnnouncementAdapter;
import com.sakai.ug.sakaiapp.adapters.CourseSiteAdapter;
import com.sakai.ug.sakaiapp.models.AnnouncementModel;
import com.sakai.ug.sakaiapp.models.CourseSiteModel;

import java.util.ArrayList;
import java.util.List;

public class SiteFragment extends Fragment implements CourseSiteAdapter.onCourseSiteItemClickListener {

    List<CourseSiteModel> courseSiteList;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_site, container, false);

        recyclerView = view.findViewById(R.id.course_site_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        courseSiteList = new ArrayList<>();

        courseSiteList.add(
                new CourseSiteModel(
                        "CSCD 418: Systems Security",
                        "Dr. Winfred Yaokumah",
                        R.drawable.ic_sites
                ));

        courseSiteList.add(
                new CourseSiteModel(
                        "CSCD 416: Systems Programming",
                        "Dr. Solomon Mensah",
                        R.drawable.ic_sites
                ));

        courseSiteList.add(
                new CourseSiteModel(
                        "CSCD 434: Mobile Computing",
                        "Mr. Paul Ammah",
                        R.drawable.ic_sites
                ));

        courseSiteList.add(
                new CourseSiteModel(
                        "CSCD 424: Expert Systems",
                        "Dr. Ernest Gyebi",
                        R.drawable.ic_sites
                ));

        courseSiteList.add(
                new CourseSiteModel(
                        "CSCD 408: Networking 2",
                        "Dr. Ernest Gyebi",
                        R.drawable.ic_sites
                ));

        courseSiteList.add(
                new CourseSiteModel(
                        "CSCD 428: Compilers",
                        "Mr. Michael Soli",
                        R.drawable.ic_sites
                ));

        CourseSiteAdapter adapter = new CourseSiteAdapter(courseSiteList, this.getActivity(), this);
        recyclerView.setAdapter(adapter);
        return view;

    }


    @Override
    public void onItemClick(int position) {
        courseSiteList.get(position);
        Intent intent = new Intent(getActivity(), CourseSiteActivity.class);
        startActivity(intent);
    }
}
