package com.sakai.ug.sakaiapp.course_site_fragments;

import android.content.pm.PackageManager;
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
import com.sakai.ug.sakaiapp.APIservices.ResourcesInterface;
import com.sakai.ug.sakaiapp.R;
import com.sakai.ug.sakaiapp.adapters.AnnouncementAdapter;
import com.sakai.ug.sakaiapp.adapters.ResourcesAdapter;
import com.sakai.ug.sakaiapp.models.announcement.Announcement;
import com.sakai.ug.sakaiapp.models.resources.Resources;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResourcesFragment extends Fragment {
    private static final int PERMISSION_STORAGE_CODE = 1000;

    Resources resources = new Resources();
    RecyclerView recyclerView;
    private ResourcesAdapter adapter;
    ApiClient apiClient = new ApiClient();
    ResourcesInterface resourcesInterface;
    SwipeRefreshLayout swipeRefreshLayout;
    private Resources resourcesList;


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
        String courseid = bundle2.getString("COURSE_ID");
        Log.d("ResourcesSiteIDSakai", "Course id: " + courseid );

        View view = inflater.inflate(R.layout.fragment_resources, container, false);

        /*swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            retrieveResources(courseid);
            swipeRefreshLayout.setRefreshing(false);
        });*/

        recyclerView = view.findViewById(R.id.resources_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));


        resourcesInterface = apiClient.getApiClient(this.getContext()).create(ResourcesInterface.class);
        retrieveResources(courseid);

        return view;
    }


    public void retrieveResources(String courseid) {
        Call<Resources> call = resourcesInterface.getSiteResources(courseid);

        call.enqueue(new Callback<Resources>() {
            @Override
            public void onResponse(Call<Resources> call, Response<Resources> response) {
                Log.d("Success", "onResponse: Successful");
                resourcesList = response.body();
                adapter = new ResourcesAdapter(resourcesList, getContext());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Resources> call, Throwable t) {
                Log.d("Fail", "onFailure: Request failed");
                Log.d("Status", t.getMessage());
                //Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

   /* @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case PERMISSION_STORAGE_CODE:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    adapter.startDownloading("http://sakaiapp.ngrok.io/access/content/group/eba67216-dd9c-4535-9a96-7c703ba0499f/Assignment%201%20-%20Computer%20Systems%20Security.docx");
                }
                else{
                    Toast.makeText(getActivity(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }*/
}


