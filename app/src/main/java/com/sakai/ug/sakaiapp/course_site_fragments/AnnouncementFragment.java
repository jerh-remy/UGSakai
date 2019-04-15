package com.sakai.ug.sakaiapp.course_site_fragments;

import android.content.Intent;
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

import com.sakai.ug.sakaiapp.APIservices.AnnouncementInterface;
import com.sakai.ug.sakaiapp.APIservices.ApiClient;
import com.sakai.ug.sakaiapp.APIservices.LoginSessionInterface;
import com.sakai.ug.sakaiapp.R;
import com.sakai.ug.sakaiapp.adapters.AnnouncementAdapter;
import com.sakai.ug.sakaiapp.callback.AnnouncementFetchListener;
import com.sakai.ug.sakaiapp.course_site_details.AnnouncementDetailActivity;
import com.sakai.ug.sakaiapp.database.SakaiDatabase;
import com.sakai.ug.sakaiapp.helper.Utils;
import com.sakai.ug.sakaiapp.models.announcement.Announcement;
import com.sakai.ug.sakaiapp.models.announcement.AnnouncementCollection;
import com.sakai.ug.sakaiapp.models.assignment.AssignmentCollection;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnnouncementFragment extends Fragment implements AnnouncementAdapter.onAnnouncementItemClickListener, AnnouncementFetchListener {

    Announcement announcement = new Announcement();
    AnnouncementCollection announcementCollection = new AnnouncementCollection();
    RecyclerView recyclerView;
    private AnnouncementAdapter adapter;
    private SakaiDatabase sakaiDatabase;
    ApiClient apiClient = new ApiClient();
    AnnouncementInterface announcementInterface;
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
        Log.d("SiteIDSakai(Announcement)", "Course id: " + courseid);

        View view = inflater.inflate(R.layout.fragment_announcements, container, false);

        sakaiDatabase = new SakaiDatabase(getContext());


        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            retrieveAnnouncements(courseid);
            swipeRefreshLayout.setRefreshing(false);
        });

        recyclerView = view.findViewById(R.id.announcement_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        adapter = new AnnouncementAdapter(getContext(), AnnouncementFragment.this::onItemClick);
        recyclerView.setAdapter(adapter);

        announcementInterface = apiClient.getApiClient(this.getContext()).create(AnnouncementInterface.class);

        loadAnnouncements();

        return view;

    }

    private void loadAnnouncements() {
        adapter.reset();

        if (getNetworkAvailability()) {
            Log.d("Network status", "Network available");
            retrieveAnnouncements(courseid);
        } else {
            Log.d("Network status", "loadAnnouncements: Network unavailable, retrieving from database");
            getAnnouncementsFromDatabase();
        }
    }

    private void getAnnouncementsFromDatabase() {
        sakaiDatabase.fetchAnnouncements(this);
    }

    @Override
    public void onItemClick(int position) {
        //announcement.getAnnouncementCollection().get(position);
        /*Intent intent = new Intent(getActivity(), AnnouncementDetailActivity.class);
        startActivity(intent);*/
    }


    public void retrieveAnnouncements(String site_id) {
        Call<Announcement> call = announcementInterface.getSiteAnnouncement(site_id);

        call.enqueue(new Callback<Announcement>() {
            @Override
            public void onResponse(Call<Announcement> call, Response<Announcement> response) {
                Log.d("Success", "onResponse: Successful");
                announcement = response.body();
                Log.d("Response body", "onResponse: " + announcement.getAnnouncementCollection().get(0).getAnnouncementId());

                for (int i = 0; i < announcement.getAnnouncementCollection().size(); i++) {
                    announcementCollection = announcement.getAnnouncementCollection().get(i);

                    SaveIntoDatabase task = new SaveIntoDatabase();
                    task.execute(announcementCollection);

                    adapter.addAnnouncement(announcementCollection);

                }


            }

            @Override
            public void onFailure(Call<Announcement> call, Throwable t) {
                Log.d("Fail", "onFailure: Request failed");
                Log.d("Status", t.getMessage());
                //Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public boolean getNetworkAvailability() {
        return Utils.isNetworkAvailable(getContext());
    }

    @Override
    public void onDeliverAllAnnouncements(List<AnnouncementCollection> announcementCollectionList) {

    }

    @Override
    public void onDeliverAnnouncement(AnnouncementCollection announcementCollection) {
        adapter.addAnnouncement(announcementCollection);
    }

    @Override
    public void onHideDialog() {

    }

    public class SaveIntoDatabase extends AsyncTask<AnnouncementCollection, Void, Void> {


        private final String TAG = AnnouncementFragment.SaveIntoDatabase.class.getSimpleName();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(AnnouncementCollection... params) {

            AnnouncementCollection announcementCollection = params[0];

            try {
                sakaiDatabase.addAnnouncement(announcementCollection);
                Log.d(TAG, "doInBackground: Announcement added:" + announcementCollection.getEntityId());

            } catch (Exception e) {
                Log.d(TAG, e.getMessage());
            }

            return null;
        }
    }

}
