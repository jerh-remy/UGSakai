package com.sakai.ug.sakaiapp.course_site_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sakai.ug.sakaiapp.APIservices.ApiClient;
import com.sakai.ug.sakaiapp.APIservices.ApiInterface;
import com.sakai.ug.sakaiapp.CourseSiteActivity;
import com.sakai.ug.sakaiapp.R;
import com.sakai.ug.sakaiapp.adapters.AssignmentAdapter;
import com.sakai.ug.sakaiapp.course_site_details.AssignmentDetailActivity;
import com.sakai.ug.sakaiapp.models.assignment.Assignment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssignmentsFragment extends Fragment implements AssignmentAdapter.onAssignmentItemClickListener {

    private Assignment assignment = new Assignment();
    RecyclerView recyclerView;
    private AssignmentAdapter assignmentAdapter;
    ApiClient apiClient = new ApiClient();
    ApiInterface apiInterface;
    SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assignments, container, false);


        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            retrieveAssignments();
            swipeRefreshLayout.setRefreshing(false);
        });

        recyclerView = view.findViewById(R.id.assignments_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        apiInterface = apiClient.getApiClient(this.getContext()).create(ApiInterface.class);
        retrieveAssignments();

        return view;
    }

    private void retrieveAssignments() {
        Call<Assignment> call = apiInterface.getSiteAssignment();

        call.enqueue(new Callback<Assignment>() {
            @Override
            public void onResponse(Call<Assignment> call, Response<Assignment> response) {
                Log.d("Success", "onResponse: Successful");
                assignment = response.body();
                assignmentAdapter = new AssignmentAdapter(assignment, getContext(), AssignmentsFragment.this::onItemClick);
                recyclerView.setAdapter(assignmentAdapter);
            }

            @Override
            public void onFailure(Call<Assignment> call, Throwable t) {
                Log.d("Fail", "onFailure: Request failed");
                Log.d("Status", t.getMessage());
                Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        assignment.getAssignmentCollection().get(position);
        Intent intent = new Intent(getActivity(), AssignmentDetailActivity.class);
        startActivity(intent);
    }
}

