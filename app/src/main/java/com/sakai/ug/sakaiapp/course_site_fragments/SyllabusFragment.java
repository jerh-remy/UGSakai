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
import android.widget.Toast;

import com.sakai.ug.sakaiapp.APIservices.ApiClient;
import com.sakai.ug.sakaiapp.APIservices.SyllabusInterface;
import com.sakai.ug.sakaiapp.R;
import com.sakai.ug.sakaiapp.adapters.AssignmentAdapter;
import com.sakai.ug.sakaiapp.models.assignment.Assignment;
import com.sakai.ug.sakaiapp.models.syllabus.Syllabus;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SyllabusFragment extends Fragment {

    Syllabus syllabus = new Syllabus();
    SyllabusInterface syllabusInterface;
    ApiClient apiClient = new ApiClient();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_syllabus, container, false);

        Bundle bundle2 = this.getArguments();
        String courseid = bundle2.getString("COURSE_ID");
        Log.d("SiteIDSakai(Syllabus)", "Course id: " + courseid);

        syllabusInterface = apiClient.getApiClient(this.getContext()).create(SyllabusInterface.class);
        retrieveSyllabus(courseid);
        return view;

    }

    public void retrieveSyllabus(String site_id) {
        Call<Syllabus> call = syllabusInterface.getSiteSyllabus(site_id);

        call.enqueue(new Callback<Syllabus>() {
            @Override
            public void onResponse(Call<Syllabus> call, Response<Syllabus> response) {
                Log.d("Success", "onResponse: Successful");
                syllabus = response.body();
                String title = syllabus.getItems().get(0).getTitle();
                String body = syllabus.getItems().get(0).getData();
                TextView tv_title = getView().findViewById(R.id.title);
                tv_title.setText(title);
                TextView tv_body = getView().findViewById(R.id.body);
                tv_body.setText(Html.fromHtml(body));

            }

            @Override
            public void onFailure(Call<Syllabus> call, Throwable t) {
                Log.d("Fail", "onFailure: Request failed");
                Log.d("Status", t.getMessage());
            }
        });
    }
}

