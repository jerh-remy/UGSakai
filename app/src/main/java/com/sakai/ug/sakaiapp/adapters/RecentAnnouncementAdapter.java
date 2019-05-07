package com.sakai.ug.sakaiapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sakai.ug.sakaiapp.R;
import com.sakai.ug.sakaiapp.course_site_details.AnnouncementDetailActivity;
import com.sakai.ug.sakaiapp.models.announcement.AnnouncementCollection;

import java.util.ArrayList;
import java.util.List;

public class RecentAnnouncementAdapter extends RecyclerView.Adapter<RecentAnnouncementAdapter.RecentAnnouncementViewHolder> {

    private List<AnnouncementCollection> announcementList;
    private Context context;
    private final onRecentAnnouncementItemClickListener listener;


    public RecentAnnouncementAdapter(Context context, onRecentAnnouncementItemClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.announcementList = new ArrayList<>();

    }

    @NonNull
    @Override
    public RecentAnnouncementViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.view_announcement_item, null);
        return new RecentAnnouncementViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentAnnouncementViewHolder recentAnnouncementViewHolder, int i) {
        recentAnnouncementViewHolder.title.setText(announcementList.get(i).getTitle());
        recentAnnouncementViewHolder.body.setText(Html.fromHtml(announcementList.get(i).getBody()));
        recentAnnouncementViewHolder.image.setImageDrawable(recentAnnouncementViewHolder.image.getResources().getDrawable(R.drawable.ic_announcement));

    }

    @Override
    public int getItemCount() {
        return announcementList.size();
    }

    public void addRecentAnnouncement(AnnouncementCollection announcementCollection) {
        announcementList.add(announcementCollection);
        notifyDataSetChanged();
    }

    public void reset() {
        announcementList.clear();
        notifyDataSetChanged();
    }

    public class RecentAnnouncementViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title, body;
        ImageView image;
        onRecentAnnouncementItemClickListener onRecentAnnouncementItemClickListener;


        public RecentAnnouncementViewHolder(@NonNull View itemView, onRecentAnnouncementItemClickListener onAnnouncementItemClickListener) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            body =  itemView.findViewById(R.id.body);
            image = itemView.findViewById(R.id.image);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            announcementList.get(getAdapterPosition());
            Intent goToOneAnnouncement = new Intent(context, AnnouncementDetailActivity.class);
            goToOneAnnouncement.putExtra("AN_TITLE", announcementList.get(getAdapterPosition()).getTitle());
            goToOneAnnouncement.putExtra("AN_SAVED_BY", announcementList.get(getAdapterPosition()).getCreatedByDisplayName());
            //goToOneAnnouncement.putExtra("AN_MODIFIED_DATE", announcement.getAnnouncementCollection().get(getAdapterPosition()).getTitle());
            goToOneAnnouncement.putExtra("AN_BODY", announcementList.get(getAdapterPosition()).getBody());
            context.startActivity(goToOneAnnouncement);
        }

    }

    public interface onRecentAnnouncementItemClickListener {
        void onItemClick(int position);
    }
}
