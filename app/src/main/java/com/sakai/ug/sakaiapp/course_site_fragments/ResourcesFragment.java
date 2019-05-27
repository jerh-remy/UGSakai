package com.sakai.ug.sakaiapp.course_site_fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
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
import com.sakai.ug.sakaiapp.callback.ResourceFetchListener;
import com.sakai.ug.sakaiapp.database.SakaiDatabase;
import com.sakai.ug.sakaiapp.helper.Utils;
import com.sakai.ug.sakaiapp.main_fragments.SiteFragment;
import com.sakai.ug.sakaiapp.models.announcement.Announcement;
import com.sakai.ug.sakaiapp.models.resources.ContentCollection;
import com.sakai.ug.sakaiapp.models.resources.Resources;
import com.sakai.ug.sakaiapp.models.site.SiteCollection;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResourcesFragment extends Fragment implements ResourceFetchListener {
    private static final int PERMISSION_STORAGE_CODE = 1000;
    private static final String TAG = "ResourcesFragment";
    Resources resources = new Resources();
    RecyclerView recyclerView;
    ResourcesAdapter adapter;
    ApiClient apiClient = new ApiClient();
    ResourcesInterface resourcesInterface;
    SakaiDatabase sakaiDatabase;
    ContentCollection contentCollection = new ContentCollection();
    SwipeRefreshLayout swipeRefreshLayout;
    private String courseid, siteTitle;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int REQUEST_CODE = 1;

        ActivityCompat.requestPermissions(this.getActivity(), new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        }, REQUEST_CODE);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Bundle bundle2 = this.getArguments();
        courseid = bundle2.getString("COURSE_ID");
        siteTitle = bundle2.getString("COURSE_TITLE");
        Log.d("ResourcesSiteIDSakai", "Course id: " + courseid );
        Log.d("ResourcesSiteIDSakai", "Course site title: " + siteTitle );

        View view = inflater.inflate(R.layout.fragment_resources, container, false);

        sakaiDatabase = new SakaiDatabase(getContext());
        /*swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            retrieveResources(courseid);
            swipeRefreshLayout.setRefreshing(false);
        });*/

        recyclerView = view.findViewById(R.id.resources_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        adapter = new ResourcesAdapter(getContext());
        recyclerView.setAdapter(adapter);

        resourcesInterface = apiClient.getApiClient(this.getContext()).create(ResourcesInterface.class);

        loadResources();

        return view;
    }

    private void loadResources() {
        adapter.reset();

        if (getNetworkAvailability()) {
            Log.d("Network status", "loadResources: Network available");
            retrieveResources(courseid);
        } else {
            Log.d("Network status", "loadResources: Network unavailable, retrieving from database");
            getResourcesFromDatabase();
        }
    }

    private void getResourcesFromDatabase() {
        sakaiDatabase.fetchResources(this, courseid);
    }



    public void retrieveResources(String courseid) {
        Call<Resources> call = resourcesInterface.getSiteResources(courseid);

        call.enqueue(new Callback<Resources>() {
            @Override
            public void onResponse(Call<Resources> call, Response<Resources> response) {
                Log.d("Success", "onResponse: Successful");
                resources = response.body();

                for (int i = 0; i < resources.getContentCollection().size(); i++) {
                    contentCollection = resources.getContentCollection().get(i);
                    contentCollection.setSiteID(courseid);
                    contentCollection.setSiteTitle(siteTitle);

                    int indexOfSlash = contentCollection.getUrl().lastIndexOf('/');
                    boolean isFolder = contentCollection.getUrl().length() - 1 == indexOfSlash;
                    Log.d(TAG, "onResponse: " + isFolder);

                    SaveIntoDatabase task = new SaveIntoDatabase();
                    task.execute(contentCollection);

                    adapter.addResource(contentCollection);

                }

            }

            @Override
            public void onFailure(Call<Resources> call, Throwable t) {
                Log.d("Fail", "onFailure: Request failed");
                Log.d("Status", t.getMessage());
                //Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDeliverAllResources(List<ContentCollection> contentCollectionList) {

    }

    @Override
    public void onDeliverResource(ContentCollection contentCollection) {
        adapter.addResource(contentCollection);
    }

    @Override
    public void onHideDialog() {

    }

    public boolean getNetworkAvailability() {
        return Utils.isNetworkAvailable(getContext());
    }

    public class SaveIntoDatabase extends AsyncTask<ContentCollection, Void, Void> {


        private final String TAG = ResourcesFragment.SaveIntoDatabase.class.getSimpleName();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(ContentCollection... params) {

            ContentCollection contentCollection= params[0];

            try {
                sakaiDatabase.addResources(contentCollection);
                Log.d(TAG, "doInBackground: Resource added:" + contentCollection.getTitle());

            } catch (Exception e) {
                Log.d(TAG, e.getMessage());
            }

            return null;
        }
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


