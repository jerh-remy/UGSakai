package com.sakai.ug.sakaiapp.adapters;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sakai.ug.sakaiapp.FolderDetailsActivity;
import com.sakai.ug.sakaiapp.R;
import com.sakai.ug.sakaiapp.helper.Utils;
import com.sakai.ug.sakaiapp.models.resources.ContentCollection;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ResourcesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //private static final int PERMISSION_STORAGE_CODE = 1000;
    private List<ContentCollection> resourcesList;
    private Context context;
    private String urldownload;

    private static final int TYPE_EMPTY = -1;
    private static final int TYPE_FILE = 0;
    private static final int TYPE_FOLDER = 1;
    private LayoutInflater inflater;

    @Override
    public int getItemViewType(int position) {
        if (resourcesList.isEmpty()) return TYPE_EMPTY;
        else if (resourcesList.get(position).getUrl().endsWith("/")) return TYPE_FOLDER;
        return TYPE_FILE;
    }

    public ResourcesAdapter(Context context) {
        this.context = context;
        resourcesList = new ArrayList<>(0);
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        switch (i) {
            case TYPE_EMPTY:
                return new EmptyViewHolder(inflater.inflate(R.layout.item_empty, viewGroup, false));
            case TYPE_FILE:
                return new FileViewHolder(inflater.inflate(R.layout.item_resource, viewGroup, false));
            default:
                return new FolderViewHolder(inflater.inflate(R.layout.item_folder, viewGroup, false));
        }
//        View view = inflater.inflate(R.layout.item_resource, null);
//        return new ResourcesViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        switch (getItemViewType(i)) {
            case TYPE_EMPTY:
                bindEmptyViewHolder((EmptyViewHolder)holder);
                break;
            case TYPE_FILE:
                bindFileHolder((FileViewHolder) holder, resourcesList.get(i));
                break;
            default:
                bindFolderHolder((FolderViewHolder) holder, resourcesList.get(i));
                break;
        }
    }

    private void bindFolderHolder(FolderViewHolder holder, ContentCollection collection) {
        // TODO: 5/27/2019 Bind folders here

        holder.foldername.setText(collection.getTitle());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, FolderDetailsActivity.class);
            Bundle bundle = new Bundle(0);
            bundle.putString(FolderDetailsActivity.EXTRA_FOLDER_NAME, collection.getEntityTitle());
            bundle.putString(FolderDetailsActivity.EXTRA_FOLDER_CONTAINER, collection.getContainer());
            intent.putExtras(bundle);
            context.startActivity(intent);
        });
    }

    private void bindFileHolder(FileViewHolder holder, ContentCollection collection) {
        holder.textViewResource.setText(collection.getTitle());
        holder.image.setImageDrawable(context.getResources().getDrawable(R.drawable.pdf));
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

    private void bindEmptyViewHolder(EmptyViewHolder holder) {
        // TODO: 5/27/2019 Bind empty viewholder
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
        return resourcesList.isEmpty() ? 1 : resourcesList.size();
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

    public class FolderViewHolder extends RecyclerView.ViewHolder {
        TextView foldername;
        ImageView folderimage;

        public FolderViewHolder(@NonNull View itemView) {
            super(itemView);

            foldername = itemView.findViewById(R.id.foldername);
            folderimage = itemView.findViewById(R.id.image_folder);

        }
    }

}