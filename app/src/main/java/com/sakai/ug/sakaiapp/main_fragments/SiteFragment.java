package com.sakai.ug.sakaiapp.main_fragments;

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

import com.sakai.ug.sakaiapp.APIservices.AnnouncementInterface;
import com.sakai.ug.sakaiapp.APIservices.ApiClient;
import com.sakai.ug.sakaiapp.APIservices.SitesInterface;
import com.sakai.ug.sakaiapp.CourseSiteActivity;
import com.sakai.ug.sakaiapp.R;
import com.sakai.ug.sakaiapp.adapters.AnnouncementAdapter;
import com.sakai.ug.sakaiapp.adapters.CourseSiteAdapter;
import com.sakai.ug.sakaiapp.course_site_fragments.AnnouncementFragment;
import com.sakai.ug.sakaiapp.models.site.Site;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SiteFragment extends Fragment implements CourseSiteAdapter.onCourseSiteItemClickListener {

    RecyclerView recyclerView;
    CourseSiteAdapter adapter;
    ApiClient apiClient = new ApiClient();
    SitesInterface sitesInterface;
    Site site = new Site();
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


        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        //swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(true);
            retrieveCourseSites();
        });

        recyclerView = view.findViewById(R.id.course_site_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        sitesInterface = apiClient.getApiClient(this.getContext()).create(SitesInterface.class);
        retrieveCourseSites();

        return view;

    }

    private void retrieveCourseSites() {
        Call<Site> siteCall = sitesInterface.getSites();
        siteCall.enqueue(new Callback<Site>() {
            @Override
            public void onResponse(Call<Site> call, Response<Site> response) {
                swipeRefreshLayout.setRefreshing(false);

                if (response.isSuccessful() && response.body() != null) {
                    Log.d("Course site response", "onResponse: " + response.body());
                    site = response.body();
                    adapter = new CourseSiteAdapter(site, getContext(), SiteFragment.this::onItemClick);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(getContext(), "No course sites found. Please try again", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Site> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Log.d("Course site failure", "onFailure: " + t.getMessage());
                Toast.makeText(getContext(), "Please check your connection and try again", Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void onItemClick(int position) {
        site.getSiteCollection().get(position);
        /*Intent intent = new Intent(getActivity(), CourseSiteActivity.class);
        startActivity(intent);*/
    }


}
