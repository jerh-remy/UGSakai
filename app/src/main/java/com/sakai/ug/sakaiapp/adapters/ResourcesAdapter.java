package com.sakai.ug.sakaiapp.adapters;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.sakai.ug.sakaiapp.R;
import com.sakai.ug.sakaiapp.helper.Utils;
import com.sakai.ug.sakaiapp.models.resources.ContentCollection;
import com.sakai.ug.sakaiapp.models.resources.Resources;
import com.sakai.ug.sakaiapp.models.site.SiteCollection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.prefs.Preferences;

public class ResourcesAdapter extends RecyclerView.Adapter<ResourcesAdapter.ResourcesViewHolder> {

    //private static final int PERMISSION_STORAGE_CODE = 1000;
    private List<ContentCollection> resourcesList;
    private Context context;
    private String urldownload;

    public ResourcesAdapter(Context context) {
        this.context = context;
        resourcesList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ResourcesAdapter.ResourcesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_resource, null);
        return new ResourcesViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ResourcesAdapter.ResourcesViewHolder resourcesViewHolder, int i) {

        resourcesViewHolder.textViewResource.setText(resourcesList.get(i).getTitle());
        resourcesViewHolder.image.setImageDrawable(context.getResources().getDrawable(R.drawable.pdf));
        urldownload = resourcesList.get(i).getUrl();
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

        resourcesViewHolder.dloadmynote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Utils.isNetworkAvailable(context))
                {
                String linktodownload = resourcesList.get(i).getUrl();
                String title = resourcesList.get(i).getTitle();

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
                    Log.d("CookieLogged",  cookie);
                }
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
                request.setTitle(title);
                request.setDescription("Downloading file");
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "" + System.currentTimeMillis());

                downloadmanager.enqueue(request);
                Toast.makeText(context.getApplicationContext(),
                        "Your file is now downloading...", Toast.LENGTH_LONG).show();
            }
            else{
                    Toast.makeText(context.getApplicationContext(),
                            "Cannot download... check internet connection.", Toast.LENGTH_LONG).show();
            }
            }
        });

    }


    /*public void startDownloading(String linktodownload) {
        DownloadManager manager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(linktodownload));
        Log.d("DOWNLOADLINK", linktodownload);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle("Download");
        request.setDescription("Downloading file");
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "" + System.currentTimeMillis());

        manager.enqueue(request);

    }
*/



    @Override
    public int getItemCount() {
        return resourcesList.size();
    }

class ResourcesViewHolder extends RecyclerView.ViewHolder {

    TextView textViewResource, textViewURL;
    ImageView image;
    ImageButton dloadmynote;

    public ResourcesViewHolder(@NonNull View itemView) {
        super(itemView);

        textViewResource = itemView.findViewById(R.id.textViewResource);
        //textViewURL = itemView.findViewById(R.id.textViewUrl);
        image = itemView.findViewById(R.id.image);
        dloadmynote = itemView.findViewById(R.id.dloadnote);
    }
}

    public void addResource(ContentCollection contentCollection) {
        resourcesList.add(contentCollection);
        notifyDataSetChanged();

    }

    public void reset() {
        resourcesList.clear();
        notifyDataSetChanged();
    }


}