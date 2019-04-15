package com.sakai.ug.sakaiapp.main_fragments;

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
import android.widget.Toast;

import com.sakai.ug.sakaiapp.APIservices.AnnouncementInterface;
import com.sakai.ug.sakaiapp.APIservices.ApiClient;
import com.sakai.ug.sakaiapp.APIservices.SitesInterface;
import com.sakai.ug.sakaiapp.CourseSiteActivity;
import com.sakai.ug.sakaiapp.R;
import com.sakai.ug.sakaiapp.adapters.AnnouncementAdapter;
import com.sakai.ug.sakaiapp.adapters.AssignmentAdapter;
import com.sakai.ug.sakaiapp.adapters.CourseSiteAdapter;
import com.sakai.ug.sakaiapp.callback.CourseSiteFetchListener;
import com.sakai.ug.sakaiapp.course_site_fragments.AnnouncementFragment;
import com.sakai.ug.sakaiapp.course_site_fragments.AssignmentsFragment;
import com.sakai.ug.sakaiapp.database.SakaiDatabase;
import com.sakai.ug.sakaiapp.helper.Utils;
import com.sakai.ug.sakaiapp.models.assignment.AssignmentCollection;
import com.sakai.ug.sakaiapp.models.site.Site;
import com.sakai.ug.sakaiapp.models.site.SiteCollection;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SiteFragment extends Fragment implements CourseSiteAdapter.onCourseSiteItemClickListener, CourseSiteFetchListener {

    RecyclerView recyclerView;
    CourseSiteAdapter adapter;
    ApiClient apiClient = new ApiClient();
    SitesInterface sitesInterface;
    Site site = new Site();
    private SakaiDatabase sakaiDatabase;
    SiteCollection siteCollection = new SiteCollection();
    SwipeRefreshLayout swipeRefreshLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_site, container, false);

        sakaiDatabase = new SakaiDatabase(getContext());
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(true);
            retrieveCourseSites();
        });

        recyclerView = view.findViewById(R.id.course_site_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        adapter = new CourseSiteAdapter(getContext(), SiteFragment.this::onItemClick);

        recyclerView.setAdapter(adapter);

        sitesInterface = apiClient.getApiClient(this.getContext()).create(SitesInterface.class);

        loadCourseSites();

        return view;

    }

    private void loadCourseSites() {

        adapter.reset();

        if (getNetworkAvailability()) {
            Log.d("Network status", "loadCourseSites: Network available");
            retrieveCourseSites();
        } else {
            Log.d("Network status", "loadCourseSites: Network unavailable, retrieving from database");
            getCourseSitesFromDatabase();
        }
    }

    private void getCourseSitesFromDatabase() {
        sakaiDatabase.fetchCourseSites(this);
    }


    private void retrieveCourseSites() {
        Call<Site> siteCall = sitesInterface.getSites();
        swipeRefreshLayout.setRefreshing(true);

        siteCall.enqueue(new Callback<Site>() {
            @Override
            public void onResponse(Call<Site> call, Response<Site> response) {
                swipeRefreshLayout.setRefreshing(false);

                if (response.isSuccessful() && response.body() != null) {
                    Log.d("Course site response", "onResponse: " + response.body().getSiteCollection().size());
                    site = response.body();

                    for (int i = 0; i < site.getSiteCollection().size(); i++) {
                        siteCollection = site.getSiteCollection().get(i);

                        SiteFragment.SaveIntoDatabase task = new SiteFragment.SaveIntoDatabase();
                        task.execute(siteCollection);

                        adapter.addCourseSite(siteCollection);

                    }
                } else {
                    Toast.makeText(getContext(), "No course sites found. Please try again", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Site> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Log.d("Course site failure", "onFailure: " + t.getMessage());
                //Toast.makeText(getContext(), "Please check your connection and try again", Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void onItemClick(int position) {
        //siteCollectionList.get(position);
        /*Intent intent = new Intent(getActivity(), CourseSiteActivity.class);
        startActivity(intent);*/
    }

    public boolean getNetworkAvailability() {
        return Utils.isNetworkAvailable(getContext());
    }


    @Override
    public void onDeliverAllCourseSites(List<SiteCollection> siteCollectionList) {

    }

    @Override
    public void onDeliverCourseSite(SiteCollection siteCollection) {
        adapter.addCourseSite(siteCollection);
    }

    @Override
    public void onHideDialog() {

    }

    public class SaveIntoDatabase extends AsyncTask<SiteCollection, Void, Void> {


        private final String TAG = SiteFragment.SaveIntoDatabase.class.getSimpleName();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(SiteCollection... params) {

            SiteCollection siteCollection = params[0];

            try {
                sakaiDatabase.addCourseSite(siteCollection);
                Log.d(TAG, "doInBackground: Course site added:" + siteCollection.getEntityId());

            } catch (Exception e) {
                Log.d(TAG, e.getMessage());
            }

            return null;
        }
    }


}
