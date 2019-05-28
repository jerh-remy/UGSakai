package com.sakai.ug.sakaiapp;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sakai.ug.sakaiapp.callback.QueryCallback;
import com.sakai.ug.sakaiapp.database.SakaiDatabase;
import com.sakai.ug.sakaiapp.databinding.ActivityFolderBinding;
import com.sakai.ug.sakaiapp.helper.Utils;
import com.sakai.ug.sakaiapp.models.resources.ContentCollection;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class FolderDetailsActivity extends AppCompatActivity {
    public static final String EXTRA_FOLDER_NAME = "EXTRA_FOLDER_NAME";
    public static final String EXTRA_FOLDER_CONTAINER = "EXTRA_FOLDER_CONTAINER";

    private SakaiDatabase sakaiDatabase;
    private static final String TAG = "FolderDetailsActivity";
    private ActivityFolderBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sakaiDatabase = new SakaiDatabase(getApplicationContext());

        binding = DataBindingUtil.setContentView(this, R.layout.activity_folder);

        setSupportActionBar(binding.toolbar);
        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        // Setup recyclerview
        setupGrid();


    }

    private void setupGrid() {
        FilesAdapter adapter = new FilesAdapter(this);
        binding.filesList.setAdapter(adapter);
        binding.filesList.setHasFixedSize(true);
        binding.filesList.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_FOLDER_NAME)) {

            String folderTitle = intent.getStringExtra(EXTRA_FOLDER_NAME);
            String folderContainer = intent.getStringExtra(EXTRA_FOLDER_CONTAINER);
            Log.d(TAG, "Folder Name: " + folderTitle);
            binding.toolbar.setTitle(folderTitle);

            sakaiDatabase.fetchFolder(folderTitle, new QueryCallback<ContentCollection>() {
                @Override
                public void onError(@Nullable String errorMessage) {
                    Log.d(TAG, "onError: " + errorMessage);
                }

                @Override
                public void onSuccess(@Nullable ContentCollection response) {
                    Log.d(TAG, "onSuccess: " + response);
                    if (response == null) {
                        Log.d(TAG, "onSuccess: response is null");
                        return;
                    }

                    sakaiDatabase.fetchFilesFromFolder(new QueryCallback<List<ContentCollection>>() {
                        @Override
                        public void onError(@Nullable String errorMessage) {

                        }

                        @Override
                        public void onSuccess(@Nullable List<ContentCollection> files) {
                            String compareString = response.getContainer() /*+ "/" */+ response.getSiteID();
                            Log.d(TAG, "Response: " + compareString);

                            if (files != null) {
                                for (ContentCollection collection : files) {
                                    Log.d(TAG, "Collection: " + collection.getUrl());
                                    if (collection.getUrl().contains(compareString))
                                        adapter.addResourceItem(collection);
                                }
                            } else {
                                Log.d(TAG, "onSuccess: Response is null");
                            }
                        }
                    });

                }

                @Override
                public void onInit() {
                    Log.d(TAG, "onInit: called");
                }
            });


            // TODO: 5/27/2019 query database for all folders containing this name
            // TODO: 5/27/2019 query all files containing the folders container field


        } else Log.d(TAG, "setupGrid: No extras found ");
//        adapter.addResources(response);
    }

    class FilesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private Context context;
        private final LayoutInflater inflater;
        private List<ContentCollection> dataset = new ArrayList<>(0);

        static final int TYPE_EMPTY = R.layout.item_empty;
        static final int TYPE_FILE = R.layout.item_resource;
        String urldownload;

        public FilesAdapter(Context context) {
            this.context = context;
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public int getItemViewType(int position) {
            return dataset.isEmpty() ? TYPE_EMPTY : TYPE_FILE;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            switch (i) {
                case TYPE_EMPTY:
                    return new EmptyViewHolder(inflater.inflate(TYPE_EMPTY, viewGroup, false));
                case TYPE_FILE:
                    return new FileViewHolder(inflater.inflate(TYPE_FILE, viewGroup, false));
                default:
                    return null;
            }

        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            if (getItemViewType(i) == TYPE_FILE) {
                FileViewHolder holder = (FileViewHolder) viewHolder;
                ContentCollection collection = dataset.get(i);

                holder.textViewResource.setText(collection.getTitle());
                holder.image.setImageDrawable(context.getResources().getDrawable(R.drawable.twotone_insert_drive_file_24px));
                urldownload = collection.getUrl();
                //resourcesViewHolder.textViewURL.setText(urldownload);



/*
        resourcesViewHolder.dloadmynote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                *//*Intent mynoteurlload = new Intent(context, DownloadService.class);
                mynoteurlload.putExtra("LEC_NOTE_URL", resourcesList.getContentCollection().get(i).getUrl());
                Log.d("DOWNLOADLINK", resourcesList.getContentCollection().get(i).getUrl());
                context.startActivity(mynoteurlload);*//*

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        ActivityCompat.requestPermissions((Activity) context, permissions, PERMISSION_STORAGE_CODE);
                    } else {
                        startDownloading();
                    }
                } else {
                    startDownloading();
                }
            }
        });*/

                holder.dloadmynote.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (Utils.isNetworkAvailable(context)) {
                            String linktodownload = collection.getUrl();
                            String title = collection.getTitle();
                            String siteTitle = collection.getSiteTitle();

                            //folder to download to
                            File direct = new File(Environment.getExternalStorageDirectory()
                                    + "/UG Sakai");

                            if (!direct.exists()) {
                                direct.mkdirs();
                            }

                            //download manager stuff
                            String servicestring = Context.DOWNLOAD_SERVICE;
                            DownloadManager downloadmanager;
                            downloadmanager = (DownloadManager) context.getSystemService(servicestring);
                            Uri uri = Uri.parse(linktodownload);
                            DownloadManager.Request request = new DownloadManager.Request(uri);
                            HashSet<String> preferences = (HashSet) PreferenceManager
                                    .getDefaultSharedPreferences(context)
                                    .getStringSet("appCookies", new HashSet<>());

                            for (String cookie : preferences) {
                                request.addRequestHeader("Cookie", cookie);
                                Log.d("CookieLogged", cookie);
                            }
                            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
                            request.setTitle(title);
                            request.setDescription("Downloading file");
                            request.allowScanningByMediaScanner();
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                            request.setDestinationInExternalPublicDir("/UG Sakai", "/Resources/" + siteTitle + "/" + title);

                            downloadmanager.enqueue(request);
                            Toast.makeText(context.getApplicationContext(),
                                    "Your file is now downloading...", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(context.getApplicationContext(),
                                    "Cannot download... check internet connection.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return dataset.isEmpty() ? 1 : dataset.size();
        }

        public void addResources(List<ContentCollection> dataset) {
            this.dataset.clear();
            this.dataset.addAll(dataset);
            notifyDataSetChanged();
        }

        public void addResourceItem(ContentCollection collection) {
            this.dataset.add(collection);
            notifyDataSetChanged();
        }

        public class EmptyViewHolder extends RecyclerView.ViewHolder {

            public EmptyViewHolder(@NonNull View itemView) {
                super(itemView);
            }
        }

        public class FileViewHolder extends RecyclerView.ViewHolder {
            TextView textViewResource, textViewURL;
            ImageView image;
            ImageButton dloadmynote;

            public FileViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewResource = itemView.findViewById(R.id.textViewResource);
                //textViewURL = itemView.findViewById(R.id.textViewUrl);
                image = itemView.findViewById(R.id.image);
                dloadmynote = itemView.findViewById(R.id.dloadnote);
            }
        }
    }
}
