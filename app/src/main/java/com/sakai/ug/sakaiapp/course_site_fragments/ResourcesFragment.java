package com.sakai.ug.sakaiapp.course_site_fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sakai.ug.sakaiapp.R;
import com.sakai.ug.sakaiapp.adapters.ResourcesAdapter;
import com.sakai.ug.sakaiapp.models.ResourcesModel;

import java.util.ArrayList;
import java.util.List;

public class ResourcesFragment extends Fragment {

    private List<ResourcesModel> resourcesList;
    RecyclerView recyclerView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Bundle bundle2 = this.getArguments();
        String courseid = bundle2.getString("COURSE_ID");
        Log.d("SiteIDSakai", "Course id: " + courseid );

        View view = inflater.inflate(R.layout.fragment_resources, container, false);

        recyclerView = view.findViewById(R.id.resources_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        resourcesList = new ArrayList<>();


        resourcesList.add(
                new ResourcesModel(
                        "frankwood.pdf",
                        R.drawable.pdf

                )
        );
        resourcesList.add(
                new ResourcesModel(
                        "frankwood.pdf",
                        R.drawable.pdf

                )
        );
        resourcesList.add(
                new ResourcesModel(
                        "Management principles.pdf",
                        R.drawable.pdf

                )
        );
        resourcesList.add(
                new ResourcesModel(
                        "frankwood.pdf",
                        R.drawable.pdf

                )
        );
        resourcesList.add(
                new ResourcesModel(
                        "RIPS.pdf",
                        R.drawable.pdf

                )
        );
        resourcesList.add(
                new ResourcesModel(
                        "Management.pdf",
                        R.drawable.pdf

                )
        );
        resourcesList.add(
                new ResourcesModel(
                        "Management.pdf",
                        R.drawable.pdf

                )
        );
        resourcesList.add(
                new ResourcesModel(
                        "CIPS.pdf",
                        R.drawable.pdf

                )
        );
        resourcesList.add(
                new ResourcesModel(
                        "Management.pdf",
                        R.drawable.pdf

                )
        );
        resourcesList.add(
                new ResourcesModel(
                        "Management principles.pdf",
                        R.drawable.pdf

                )
        );
        resourcesList.add(
                new ResourcesModel(
                        "Management Principles.pdf",
                        R.drawable.pdf

                )
        );
        resourcesList.add(
                new ResourcesModel(
                        "Management Principles.pdf",
                        R.drawable.pdf

                )
        );
        resourcesList.add(
                new ResourcesModel(
                        "Management Principles.pdf",
                        R.drawable.pdf

                )
        );

        ResourcesAdapter adapter = new ResourcesAdapter(resourcesList, this.getActivity());
        recyclerView.setAdapter(adapter);

        return view;
    }
}


