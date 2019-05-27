package com.sakai.ug.sakaiapp.main_fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sakai.ug.sakaiapp.APIservices.AnnouncementInterface;
import com.sakai.ug.sakaiapp.APIservices.ApiClient;
import com.sakai.ug.sakaiapp.LoginActivity;
import com.sakai.ug.sakaiapp.R;
import com.sakai.ug.sakaiapp.SharedPreferencesManager;
import com.sakai.ug.sakaiapp.adapters.AnnouncementAdapter;
import com.sakai.ug.sakaiapp.adapters.RecentAnnouncementAdapter;
import com.sakai.ug.sakaiapp.callback.RecentAnnouncementFetchListener;
import com.sakai.ug.sakaiapp.course_site_fragments.AnnouncementFragment;
import com.sakai.ug.sakaiapp.database.SakaiDatabase;
import com.sakai.ug.sakaiapp.helper.Constants;
import com.sakai.ug.sakaiapp.helper.Utils;
import com.sakai.ug.sakaiapp.models.announcement.Announcement;
import com.sakai.ug.sakaiapp.models.announcement.AnnouncementCollection;
import com.sakai.ug.sakaiapp.models.gradebook.Assignment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements RecentAnnouncementAdapter.onRecentAnnouncementItemClickListener, RecentAnnouncementFetchListener {

    TextView welcome, date, message, beforeRecentAnnouncements;
    RecyclerView recyclerView;
    private RecentAnnouncementAdapter adapter;
    SakaiDatabase sakaiDatabase;
    ApiClient apiClient = new ApiClient();
    AnnouncementInterface announcementInterface;
    Announcement announcement = new Announcement();
    AnnouncementCollection announcementCollection = new AnnouncementCollection();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        welcome = view.findViewById(R.id.welcome_username);
        date = view.findViewById(R.id.current_date);
        message = view.findViewById(R.id.message);
        beforeRecentAnnouncements = view.findViewById(R.id.no_recent_announcements);

        sakaiDatabase = new SakaiDatabase(getContext());

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("EEE, d MMMM yyyy");
        String formattedDate = df.format(c);
        date.setText(formattedDate);

        welcome.setText(getText(R.string.welcome) + SharedPreferencesManager.getInstance(getContext()).getFullname());

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimaryDark));
        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(true);
            getMOTDfromSP();
            showRecentAnnouncements();
        });

        recyclerView = view.findViewById(R.id.recent_announcements_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        adapter = new RecentAnnouncementAdapter(getContext(), HomeFragment.this::onItemClick);
        recyclerView.setAdapter(adapter);

        loadMOTD();
        loadRecentAnnouncements();

        return view;

    }


    private void loadRecentAnnouncements() {
        if (sakaiDatabase.isAnnouncementTableEmpty() == false) {
            beforeRecentAnnouncements.setVisibility(View.GONE);
            showRecentAnnouncements();
        } else {
            recyclerView.setVisibility(View.GONE);
            beforeRecentAnnouncements.setVisibility(View.VISIBLE);
        }
    }

    private void loadMOTD() {
        if (Utils.isNetworkAvailable(getContext())) {
            getMOTD();
        } else {
            getMOTDfromSP();
        }
    }


    private void getMOTD() {
        announcementInterface = apiClient.getApiClient(getContext()).create(AnnouncementInterface.class);

        Call<Announcement> getMOTDAnnouncement = announcementInterface.getMOTD();
        getMOTDAnnouncement.enqueue(new Callback<Announcement>() {
            @Override
            public void onResponse(Call<Announcement> call, Response<Announcement> response) {
                swipeRefreshLayout.setRefreshing(false);
                announcement = response.body();
                if (announcement != null) {
                    if (announcement.getAnnouncementCollection() != null) {
                        for (int i = 0; i < announcement.getAnnouncementCollection().size(); i++) {
                            announcementCollection = announcement.getAnnouncementCollection().get(i);
                            String motd = announcementCollection.getBody();
                            SharedPreferencesManager.getInstance(getContext()).MOTD(motd);
                            message.setText(Html.fromHtml(motd));

                        }
                    } else {
                        //message.setText("Nothing to display yet.");

                    }
                }

            }

            @Override
            public void onFailure(Call<Announcement> call, Throwable t) {

            }
        });

    }

    private void getMOTDfromSP() {
        String MOTD = SharedPreferencesManager.getInstance(getContext()).getMOTD();
        if (MOTD == null) {
            message.setText("Nothing to display yet.");
            swipeRefreshLayout.setRefreshing(false);
        } else {
            message.setText(Html.fromHtml(MOTD));
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    private void showRecentAnnouncements() {
        adapter.reset();
        sakaiDatabase.fetchRecentAnnouncements(this);
    }


    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onDeliverRecentAnnouncement(AnnouncementCollection announcementCollection) {
        adapter.addRecentAnnouncement(announcementCollection);
    }
}
