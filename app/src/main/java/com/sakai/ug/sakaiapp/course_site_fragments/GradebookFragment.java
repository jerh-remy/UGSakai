package com.sakai.ug.sakaiapp.course_site_fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sakai.ug.sakaiapp.R;
import com.sakai.ug.sakaiapp.adapters.GradebookAdapter;
import com.sakai.ug.sakaiapp.models.GradebookModel;

import java.util.ArrayList;
import java.util.List;

public class GradebookFragment extends Fragment {

    private List<GradebookModel> gradebookList;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gradebook, container, false);


        recyclerView = view.findViewById(R.id.gradebook_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        gradebookList = new ArrayList<>();


        gradebookList.add(
                new GradebookModel(
                        "Interim Assessment 1",
                        "B",
                        R.drawable.gradebook

                )
        );
        gradebookList.add(
                new GradebookModel(
                        "Assignment 1",
                        "B+",
                        R.drawable.gradebook

                )
        );
        gradebookList.add(
                new GradebookModel(
                        "Class Test 1",
                        "D+",
                        R.drawable.gradebook

                )
        );
        gradebookList.add(
                new GradebookModel(
                        "Interim Assessment 1",
                        "B+",
                        R.drawable.gradebook

                )
        );

        gradebookList.add(
                new GradebookModel(
                        "Class Test 2",
                        "A",
                        R.drawable.gradebook

                )
        );
        gradebookList.add(
                new GradebookModel(
                        "Interim Assessment 2",
                        "B",
                        R.drawable.gradebook

                )
        );
        gradebookList.add(
                new GradebookModel(
                        "Interim Assessment 3",
                        "B+",
                        R.drawable.gradebook

                )
        );
        gradebookList.add(
                new GradebookModel(
                        "Quiz 1",
                        "C+",
                        R.drawable.gradebook

                )
        );
        gradebookList.add(
                new GradebookModel(
                        "Interim Assessment 4",
                        "A",
                        R.drawable.gradebook

                )
        );
        gradebookList.add(
                new GradebookModel(
                        "Quiz 2",
                        "B+",
                        R.drawable.gradebook

                )
        );
        gradebookList.add(
                new GradebookModel(
                        "Interim Assessment 1",
                        "B+",
                        R.drawable.gradebook

                )
        );
        gradebookList.add(
                new GradebookModel(
                        "Pop Quiz 1",
                        "B+",
                        R.drawable.gradebook

                )
        );
        gradebookList.add(
                new GradebookModel(
                        "Assignment 4",
                        "B+",
                        R.drawable.gradebook

                )
        );
        gradebookList.add(
                new GradebookModel(
                        "Interim Assessment 1",
                        "B+",
                        R.drawable.gradebook

                )
        );
        gradebookList.add(
                new GradebookModel(
                        "Interim Assessment 3",
                        "D",
                        R.drawable.gradebook

                )
        );
        gradebookList.add(
                new GradebookModel(
                        "Assignment 1",
                        "B",
                        R.drawable.gradebook

                )
        );

        GradebookAdapter adapter = new GradebookAdapter(gradebookList, this.getActivity());
        recyclerView.setAdapter(adapter);
        return view;
    }
}


