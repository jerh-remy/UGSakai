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
import com.sakai.ug.sakaiapp.R;
import com.sakai.ug.sakaiapp.adapters.AnnouncementAdapter;
import com.sakai.ug.sakaiapp.course_site_details.AnnouncementDetailActivity;
import com.sakai.ug.sakaiapp.models.announcement.Announcement;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnnouncementFragment extends Fragment implements AnnouncementAdapter.onAnnouncementItemClickListener {

    Announcement announcement = new Announcement();
    RecyclerView recyclerView;
    private AnnouncementAdapter adapter;
    ApiClient apiClient = new ApiClient();
    ApiInterface apiInterface;
    SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_announcements, container, false);

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            retrieveAnnouncements();
            swipeRefreshLayout.setRefreshing(false);
        });

        recyclerView = view.findViewById(R.id.announcement_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));


        apiInterface = apiClient.getApiClient(this.getContext()).create(ApiInterface.class);
        retrieveAnnouncements();

        return view;

    }

    @Override
    public void onItemClick(int position) {
        announcement.getAnnouncementCollection().get(position);
        Intent intent = new Intent(getActivity(), AnnouncementDetailActivity.class);
        startActivity(intent);
    }

    public void retrieveAnnouncements()
    {
        Call<Announcement> call = apiInterface.getSiteAnnouncement();

        call.enqueue(new Callback<Announcement>() {
            @Override
            public void onResponse(Call<Announcement> call, Response<Announcement> response) {
                Log.d("Success", "onResponse: Successful");
                announcement = response.body();
                adapter = new AnnouncementAdapter(announcement, getContext(), AnnouncementFragment.this::onItemClick);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Announcement> call, Throwable t) {
                Log.d("Fail", "onFailure: Request failed");
                Log.d("Status", t.getMessage());
                //Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
