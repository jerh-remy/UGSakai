package com.sakai.ug.sakaiapp.course_site_fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
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
import com.sakai.ug.sakaiapp.APIservices.AssignmentInterface;
import com.sakai.ug.sakaiapp.APIservices.LoginSessionInterface;
import com.sakai.ug.sakaiapp.R;
import com.sakai.ug.sakaiapp.adapters.AssignmentAdapter;
import com.sakai.ug.sakaiapp.callback.AssignmentFetchListener;
import com.sakai.ug.sakaiapp.course_site_details.AssignmentDetailActivity;
import com.sakai.ug.sakaiapp.database.SakaiDatabase;
import com.sakai.ug.sakaiapp.helper.Utils;
import com.sakai.ug.sakaiapp.models.assignment.Assignment;
import com.sakai.ug.sakaiapp.models.assignment.AssignmentCollection;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssignmentsFragment extends Fragment implements AssignmentAdapter.onAssignmentItemClickListener, AssignmentFetchListener {

    private Assignment assignment = new Assignment();
    private AssignmentCollection assignmentCollection = new AssignmentCollection();
    private RecyclerView recyclerView;
    private AssignmentAdapter assignmentAdapter;
    private SakaiDatabase sakaiDatabase;
    ApiClient apiClient = new ApiClient();
    AssignmentInterface assignmentInterface;
    SwipeRefreshLayout swipeRefreshLayout;
    private String courseid;


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
        courseid = bundle2.getString("COURSE_ID");
        Log.d("SiteIDSakai(Assignment)", "Course id: " + courseid );

        View view = inflater.inflate(R.layout.fragment_assignments, container, false);

        sakaiDatabase = new SakaiDatabase(getContext());

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        //swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            retrieveAssignments(courseid);
        });

        recyclerView = view.findViewById(R.id.assignments_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        assignmentAdapter = new AssignmentAdapter(getContext(), AssignmentsFragment.this::onItemClick);
        recyclerView.setAdapter(assignmentAdapter);

        assignmentInterface = apiClient.getApiClient(this.getContext()).create(AssignmentInterface.class);

        loadAssignments();

        return view;
    }


    private void loadAssignments() {

        assignmentAdapter.reset();

        if (getNetworkAvailability()) {
            Log.d("Network status", "Network available");
            retrieveAssignments(courseid);
        } else {
            Log.d("Network status", "loadAssignments: Network unavailable, retrieving from database");
            getAssignmentsFromDatabase();
        }
    }

    private void getAssignmentsFromDatabase() {
        sakaiDatabase.fetchAssignments(this, courseid);
    }


    private void retrieveAssignments(String site_id) {
        Call<Assignment> call = assignmentInterface.getSiteAssignment(site_id);

        call.enqueue(new Callback<Assignment>() {
            @Override
            public void onResponse(Call<Assignment> call, Response<Assignment> response) {
                swipeRefreshLayout.setRefreshing(false);
                Log.d("Success response", "onResponse: Successful");
                assignment = response.body();
                Log.d("Response body", "onResponse: " + assignment.getAssignmentCollection().get(0).getEntityId());

                for (int i = 0; i < assignment.getAssignmentCollection().size(); i++) {
                    assignmentCollection = assignment.getAssignmentCollection().get(i);

                    SaveIntoDatabase task = new SaveIntoDatabase();
                    task.execute(assignmentCollection);

                    assignmentAdapter.addAssignment(assignmentCollection);

                }
               // assignmentAdapter = new AssignmentAdapter(assignment, getContext(), AssignmentsFragment.this::onItemClick);
               // recyclerView.setAdapter(assignmentAdapter);
            }

            @Override
            public void onFailure(Call<Assignment> call, Throwable t) {
                Log.d("Fail", "onFailure: Request failed");
                Log.d("Status", t.getMessage());
                //Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        assignment.getAssignmentCollection().get(position);
        Intent intent = new Intent(getActivity(), AssignmentDetailActivity.class);
        startActivity(intent);
    }


    public boolean getNetworkAvailability() {
        return Utils.isNetworkAvailable(getContext());
    }

    @Override
    public void onDeliverAllAssignments(List<AssignmentCollection> assignmentCollectionList) {

    }

    @Override
    public void onDeliverAssignment(AssignmentCollection assignmentCollection) {
        assignmentAdapter.addAssignment(assignmentCollection);

    }

    @Override
    public void onHideDialog() {

    }

    public class SaveIntoDatabase extends AsyncTask<AssignmentCollection, Void, Void> {


        private final String TAG = SaveIntoDatabase.class.getSimpleName();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(AssignmentCollection... params) {

            AssignmentCollection assignmentCollection = params[0];

            try {
                sakaiDatabase.addAssignment(assignmentCollection);
                Log.d(TAG, "doInBackground: Assignment added:" + assignmentCollection.getEntityId());

            } catch (Exception e) {
                Log.d(TAG, e.getMessage());
            }

            return null;
        }
    }
}

