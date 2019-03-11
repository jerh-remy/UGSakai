package com.sakai.ug.sakaiapp.course_site_fragments;

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

import com.sakai.ug.sakaiapp.R;
import com.sakai.ug.sakaiapp.adapters.AssignmentAdapter;
import com.sakai.ug.sakaiapp.course_site_details.AssignmentDetailActivity;
import com.sakai.ug.sakaiapp.models.AssignmentModel;

import java.util.ArrayList;
import java.util.List;

public class AssignmentsFragment extends Fragment implements AssignmentAdapter.onAssignmentItemClickListener {

    private List<AssignmentModel> assignmentList;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assignments, container, false);


        recyclerView = view.findViewById(R.id.assignments_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));


        assignmentList = new ArrayList<>();


        assignmentList.add(
                new AssignmentModel(
                        "Computer System Security",
                        "10:27am",
                        R.drawable.assignment,
                        R.drawable.ic_attach_file_black_24dp

                )
        );
        assignmentList.add(
                new AssignmentModel(
                        "Computer System Security",
                        "10:27am",
                        R.drawable.assignment,
                        R.drawable.ic_attach_file_black_24dp

                )
        );
        assignmentList.add(
                new AssignmentModel(
                        "Computer System Security",
                        "10:27am",
                        R.drawable.assignment,
                        R.drawable.ic_attach_file_black_24dp

                )
        );
        assignmentList.add(
                new AssignmentModel(
                        "Computer System Security",
                        "10:27am",
                        R.drawable.assignment,
                        R.drawable.ic_attach_file_black_24dp

                )
        );
        assignmentList.add(
                new AssignmentModel(
                        "Computer System Security",
                        "10:27am",
                        R.drawable.assignment,
                        R.drawable.ic_attach_file_black_24dp

                )
        );
        assignmentList.add(
                new AssignmentModel(
                        "Computer System Security",
                        "10:27am",
                        R.drawable.assignment,
                        R.drawable.ic_attach_file_black_24dp

                )
        );
        assignmentList.add(
                new AssignmentModel(
                        "Computer System Security",
                        "10:27am",
                        R.drawable.assignment,
                        R.drawable.ic_attach_file_black_24dp

                )
        );
        assignmentList.add(
                new AssignmentModel(
                        "Computer System Security",
                        "10:27am",
                        R.drawable.assignment,
                        R.drawable.ic_attach_file_black_24dp

                )
        );
        assignmentList.add(
                new AssignmentModel(
                        "Computer System Security",
                        "10:27am",
                        R.drawable.assignment,
                        R.drawable.ic_attach_file_black_24dp

                )
        );
        assignmentList.add(
                new AssignmentModel(
                        "Computer System Security",
                        "10:27am",
                        R.drawable.assignment,
                        R.drawable.ic_attach_file_black_24dp

                )
        );
        assignmentList.add(
                new AssignmentModel(
                        "Computer System Security",
                        "10:27am",
                        R.drawable.assignment,
                        R.drawable.ic_attach_file_black_24dp

                )
        );
        assignmentList.add(
                new AssignmentModel(
                        "Computer System Security",
                        "10:27am",
                        R.drawable.assignment,
                        R.drawable.ic_attach_file_black_24dp

                )
        );
        assignmentList.add(
                new AssignmentModel(
                        "Computer System Security",
                        "10:27am",
                        R.drawable.assignment,
                        R.drawable.ic_attach_file_black_24dp

                )
        );


        AssignmentAdapter adapter = new AssignmentAdapter(assignmentList, this.getActivity(), this);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onItemClick(int position) {
        assignmentList.get(position);
        Intent intent = new Intent(getActivity(), AssignmentDetailActivity.class);
        startActivity(intent);
    }
}

