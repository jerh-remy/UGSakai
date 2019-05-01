package com.sakai.ug.sakaiapp.course_site_fragments;

import android.os.AsyncTask;
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

import com.sakai.ug.sakaiapp.APIservices.ApiClient;
import com.sakai.ug.sakaiapp.APIservices.GradebookInterface;
import com.sakai.ug.sakaiapp.R;
import com.sakai.ug.sakaiapp.adapters.GradebookAdapter;
import com.sakai.ug.sakaiapp.callback.GradebookFetchListener;
import com.sakai.ug.sakaiapp.database.SakaiDatabase;
import com.sakai.ug.sakaiapp.helper.Utils;
import com.sakai.ug.sakaiapp.models.GradebookModel;
import com.sakai.ug.sakaiapp.models.announcement.AnnouncementCollection;
import com.sakai.ug.sakaiapp.models.gradebook.Assignment;
import com.sakai.ug.sakaiapp.models.gradebook.Gradebook;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GradebookFragment extends Fragment implements GradebookFetchListener {

    Gradebook gradebook = new Gradebook();
    Assignment assignment = new Assignment();
    private List<GradebookModel> gradebookList;
    private SakaiDatabase sakaiDatabase;
    ApiClient apiClient = new ApiClient();
    GradebookInterface gradebookInterface;
    GradebookAdapter gradebookAdapter;
    RecyclerView recyclerView;
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
        Log.d("SiteIDSakai", "Course id: " + courseid);

        View view = inflater.inflate(R.layout.fragment_gradebook, container, false);

        sakaiDatabase = new SakaiDatabase(getContext());

        recyclerView = view.findViewById(R.id.gradebook_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));


        gradebookAdapter = new GradebookAdapter(this.getActivity());
        recyclerView.setAdapter(gradebookAdapter);

        gradebookInterface = apiClient.getApiClient(this.getContext()).create(GradebookInterface.class);

        loadGrades();

        return view;
    }


    private void loadGrades() {
        gradebookAdapter.reset();

        if (getNetworkAvailability()) {
            Log.d("Network status", "Network available");
            retrieveGrades(courseid);
        } else {
            Log.d("Network status", "loadAnnouncements: Network unavailable, retrieving from database");
            getGradesFromDatabase();
        }
    }


    private void retrieveGrades(String siteID) {
        Call<Gradebook> assignmentCall = gradebookInterface.getGrades(siteID);
        assignmentCall.enqueue(new Callback<Gradebook>() {
            @Override
            public void onResponse(Call<Gradebook> call, Response<Gradebook> response) {
                Log.d("Success", "onResponse: Successful");
                gradebook = response.body();
                Log.d("Response body", "onResponse: " + gradebook.getAssignments().get(0).getItemName());

                for (int i = 0; i < gradebook.getAssignments().size(); i++) {
                    assignment = gradebook.getAssignments().get(i);
                    assignment.setSiteID(courseid);

                    SaveIntoDatabase task = new SaveIntoDatabase();
                    task.execute(assignment);

                    gradebookAdapter.addGrade(assignment);
                }
            }

            @Override
            public void onFailure(Call<Gradebook> call, Throwable t) {
                Log.d("Status", t.getMessage());

            }
        });

    }

    private void getGradesFromDatabase() {
        sakaiDatabase.fetchGrades(this, courseid);
    }

    private boolean getNetworkAvailability() {
        return Utils.isNetworkAvailable(getContext());
    }

    @Override
    public void onDeliverAllGrades(List<Assignment> assignmentList) {

    }

    @Override
    public void onDeliverGrade(Assignment assignment) {
        gradebookAdapter.addGrade(assignment);
    }

    @Override
    public void onHideDialog() {

    }

    public class SaveIntoDatabase extends AsyncTask<Assignment, Void, Void> {


        private final String TAG = GradebookFragment.SaveIntoDatabase.class.getSimpleName();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Assignment... params) {

            Assignment assignment = params[0];

            try {
                sakaiDatabase.addGrade(assignment);
                Log.d(TAG, "doInBackground: Announcement added:" + assignment.getItemName());

            } catch (Exception e) {
                Log.d(TAG, e.getMessage());
            }

            return null;
        }
    }

}


