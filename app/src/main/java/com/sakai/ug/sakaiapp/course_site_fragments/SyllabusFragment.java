package com.sakai.ug.sakaiapp.course_site_fragments;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sakai.ug.sakaiapp.APIservices.ApiClient;
import com.sakai.ug.sakaiapp.APIservices.SyllabusInterface;
import com.sakai.ug.sakaiapp.R;
import com.sakai.ug.sakaiapp.adapters.AnnouncementAdapter;
import com.sakai.ug.sakaiapp.adapters.AssignmentAdapter;
import com.sakai.ug.sakaiapp.adapters.SyllabusAdapter;
import com.sakai.ug.sakaiapp.callback.SyllabusFetchListener;
import com.sakai.ug.sakaiapp.database.SakaiDatabase;
import com.sakai.ug.sakaiapp.helper.Utils;
import com.sakai.ug.sakaiapp.models.announcement.AnnouncementCollection;
import com.sakai.ug.sakaiapp.models.assignment.Assignment;
import com.sakai.ug.sakaiapp.models.syllabus.Item;
import com.sakai.ug.sakaiapp.models.syllabus.Syllabus;

import org.w3c.dom.Text;

import java.util.HashSet;
import java.util.List;

import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SyllabusFragment extends Fragment implements SyllabusFetchListener {

    Syllabus syllabus = new Syllabus();
    Item syllabusItem = new Item();
    SyllabusInterface syllabusInterface;
    RecyclerView recyclerView;
    SyllabusAdapter syllabusAdapter;
    private SakaiDatabase sakaiDatabase;
    String courseid;
    ApiClient apiClient = new ApiClient();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_syllabus, container, false);

        Bundle bundle2 = this.getArguments();
        courseid = bundle2.getString("COURSE_ID");
        Log.d("SiteIDSakai(Syllabus)", "Course id: " + courseid);

        sakaiDatabase = new SakaiDatabase(getContext());

        recyclerView = view.findViewById(R.id.syllabus_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        syllabusAdapter = new SyllabusAdapter(getContext());
        recyclerView.setAdapter(syllabusAdapter);

        syllabusInterface = apiClient.getApiClient(this.getContext()).create(SyllabusInterface.class);

        loadSyllabus();

        return view;

    }

    public boolean getNetworkAvailability() {
        return Utils.isNetworkAvailable(getContext());
    }

    private void loadSyllabus() {
        syllabusAdapter.reset();

        if (getNetworkAvailability()) {
            Log.d("Network status", "Network available");
            retrieveSyllabus(courseid);
        } else {
            Log.d("Network status", "loadSyllabus: Network unavailable, retrieving from database");
            getSyllabusFromDatabase();
        }
    }

    private void getSyllabusFromDatabase() {
        sakaiDatabase.fetchSyllabus(this, courseid);
    }


    public void retrieveSyllabus(String site_id) {
        Call<Syllabus> call = syllabusInterface.getSiteSyllabus(site_id);

        call.enqueue(new Callback<Syllabus>() {
            @Override
            public void onResponse(Call<Syllabus> call, Response<Syllabus> response) {
                Log.d("Success", "onResponse: Successful");
                if (response.isSuccessful() && response.body() != null) {
                    syllabus = response.body();

                    if (syllabus.getItems() != null) {
                        Log.d("Syllabus response", "onResponse: Syllabus found");

                        for (int i = 0; i < syllabus.getItems().size(); i++) {
                            syllabusItem = syllabus.getItems().get(i);
                            syllabusItem.setSiteID(courseid);

                            SaveIntoDatabase task = new SaveIntoDatabase();
                            task.execute(syllabusItem);

                            syllabusAdapter.addSyllabus(syllabusItem);
                        }
                    }
                } else {
                    Log.d("Response", "onResponse: Error");
                }
            }

            @Override
            public void onFailure(Call<Syllabus> call, Throwable t) {
                Log.d("Fail", "onFailure: Request failed");
                Log.d("Status", t.getMessage());
                Toast.makeText(getContext(), "Please check your connection and try again", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onDeliverAllSyllabus(List<Item> syllabusList) {

    }

    @Override
    public void onDeliverSyllabus(Item syllabusItem) {
        syllabusAdapter.addSyllabus(syllabusItem);
    }

    @Override
    public void onHideDialog() {

    }

    public class SaveIntoDatabase extends AsyncTask<Item, Void, Void> {


        private final String TAG = SyllabusFragment.SaveIntoDatabase.class.getSimpleName();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Item... params) {

            Item syllabusItem = params[0];

            try {
                sakaiDatabase.addSyllabus(syllabusItem);
                Log.d(TAG, "doInBackground: Syllabus added:" + syllabusItem.getTitle());

            } catch (Exception e) {
                Log.d(TAG + "syllabus error", e.getMessage());
            }

            return null;
        }
    }

}

