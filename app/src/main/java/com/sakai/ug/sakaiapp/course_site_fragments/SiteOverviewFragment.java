package com.sakai.ug.sakaiapp.course_site_fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sakai.ug.sakaiapp.APIservices.ApiClient;
import com.sakai.ug.sakaiapp.APIservices.RosterInterface;
import com.sakai.ug.sakaiapp.R;
import com.sakai.ug.sakaiapp.models.roster.Roster;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SiteOverviewFragment extends Fragment {

    //roster stuff
    ApiClient apiClient = new ApiClient();
    RosterInterface rosterInterface;
    Roster roster = new Roster();
    int class_size;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_site_overview, container, false);


        Bundle bundle2 = this.getArguments();
        String courseid = bundle2.getString("COURSE_ID");
        String coursedescription = bundle2.getString("COURSE_DESCRIPTION");
        String coursetitle = bundle2.getString("COURSE_TITLE");
        String lecturer = bundle2.getString("COURSE_INSTRUCTOR");

        TextView courseTitle = view.findViewById(R.id.course_code);
        TextView courseDescription = view.findViewById(R.id.course_desc_body);
        TextView lecturer_name = view.findViewById(R.id.lecturer_name);
        TextView roster_size = view.findViewById(R.id.class_size);


        courseTitle.setText(coursetitle);
        lecturer_name.setText(lecturer);

        //finding roster size
        rosterInterface = apiClient.getApiClient(getContext()).create(RosterInterface.class);
        Call<Roster> rosterCall = rosterInterface.getRoster(courseid);
        rosterCall.enqueue(new Callback<Roster>() {
            @Override
            public void onResponse(Call<Roster> call, Response<Roster> response) {
                roster = response.body();
                try {
                    if (roster != null) {
                        class_size = roster.getRosterCollection().size() - 1;
                        roster_size.setText(class_size + " students");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("roster worked?", "onResponse: " + class_size);
            }

            @Override
            public void onFailure(Call<Roster> call, Throwable t) {
                Log.d("Roster failure", " " + t.getMessage());
            }
        });



        if (coursedescription != null) {
            courseDescription.setText(Html.fromHtml(coursedescription));
        } else {
            courseDescription.setText("No course description available");
        }

        Log.d("SiteIDSakai", "Course id: " + courseid);
        return view;
    }
}
