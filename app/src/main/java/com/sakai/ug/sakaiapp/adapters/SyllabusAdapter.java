
package com.sakai.ug.sakaiapp.adapters;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sakai.ug.sakaiapp.R;
import com.sakai.ug.sakaiapp.helper.Utils;
import com.sakai.ug.sakaiapp.models.syllabus.Item;
import com.sakai.ug.sakaiapp.models.syllabus.Syllabus;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SyllabusAdapter extends RecyclerView.Adapter<SyllabusAdapter.SyllabusViewHolder> {

    private List<Item> syllabusList;
    private Context context;


    public SyllabusAdapter(Context context) {
        this.context = context;
        syllabusList = new ArrayList<>();
    }

    @NonNull
    @Override
    public SyllabusViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.syllabus_item, null);
        return new SyllabusViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SyllabusViewHolder syllabusViewHolder, int i) {

        syllabusViewHolder.textViewTitle.setText(syllabusList.get(i).getTitle());
        syllabusViewHolder.textViewData.setText(Html.fromHtml(syllabusList.get(i).getData()));

        if (syllabusList.get(i).getAttachments().size() != 0) {
            syllabusViewHolder.textViewAttachmentTitle.setVisibility(View.VISIBLE);
            syllabusViewHolder.textViewAttachmentTitle.setText(syllabusList.get(i).getAttachments().get(i).getTitle());
        } else {
            syllabusViewHolder.textViewAttachmentTitle.setVisibility(View.VISIBLE);
            syllabusViewHolder.textViewAttachmentTitle.setText("");
        }

        syllabusViewHolder.textViewAttachmentTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Utils.isNetworkAvailable(context)) {
                    String linktodownload = "http://sakaiapp.ngrok.io" + syllabusList.get(i).getAttachments().get(i).getUrl();
                    String title = syllabusList.get(i).getAttachments().get(i).getTitle();
                    String site_title = syllabusList.get(i).getSiteTitle();

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
                    request.setDestinationInExternalPublicDir("/UG Sakai", "/Syllabus/" + site_title + "/" + title);

                    downloadmanager.enqueue(request);
                    Toast.makeText(context.getApplicationContext(),
                            "Your file is now downloading...", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(context.getApplicationContext(),
                            "Please check your internet connection", Toast.LENGTH_LONG).show();
                }

            }
        });
    }


    @Override
    public int getItemCount() {
        return syllabusList.size();
    }

public class SyllabusViewHolder extends RecyclerView.ViewHolder {

    TextView textViewTitle, textViewData, textViewAttachmentTitle;

    public SyllabusViewHolder(@NonNull View itemView) {
        super(itemView);

        textViewTitle = itemView.findViewById(R.id.title);
        textViewData = itemView.findViewById(R.id.body);
        textViewAttachmentTitle = itemView.findViewById(R.id.attachment);
    }

}

    public void addSyllabus(Item syllabusItem) {
        syllabusList.add(syllabusItem);
        notifyDataSetChanged();

    }

    public void reset() {
        syllabusList.clear();
        notifyDataSetChanged();
    }
}

