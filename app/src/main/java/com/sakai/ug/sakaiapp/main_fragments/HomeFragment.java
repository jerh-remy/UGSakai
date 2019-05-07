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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sakai.ug.sakaiapp.LoginActivity;
import com.sakai.ug.sakaiapp.R;
import com.sakai.ug.sakaiapp.SharedPreferencesManager;
import com.sakai.ug.sakaiapp.adapters.AnnouncementAdapter;
import com.sakai.ug.sakaiapp.adapters.RecentAnnouncementAdapter;
import com.sakai.ug.sakaiapp.callback.RecentAnnouncementFetchListener;
import com.sakai.ug.sakaiapp.course_site_fragments.AnnouncementFragment;
import com.sakai.ug.sakaiapp.database.SakaiDatabase;
import com.sakai.ug.sakaiapp.helper.Constants;
import com.sakai.ug.sakaiapp.models.announcement.AnnouncementCollection;
import com.sakai.ug.sakaiapp.models.gradebook.Assignment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomeFragment extends Fragment implements RecentAnnouncementAdapter.onRecentAnnouncementItemClickListener, RecentAnnouncementFetchListener {

    TextView welcome, date;
    RecyclerView recyclerView;
    private RecentAnnouncementAdapter adapter;
    SakaiDatabase sakaiDatabase;

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

        sakaiDatabase = new SakaiDatabase(getContext());

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("EEE, d MMMM yyyy");
        String formattedDate = df.format(c);
        date.setText(formattedDate);

        welcome.setText(getText(R.string.welcome) + SharedPreferencesManager.getInstance(getContext()).getFullname());

        recyclerView = view.findViewById(R.id.recent_announcements_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        adapter = new RecentAnnouncementAdapter(getContext(), HomeFragment.this::onItemClick);
        recyclerView.setAdapter(adapter);

        showRecentAnnouncements();

        return view;

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
