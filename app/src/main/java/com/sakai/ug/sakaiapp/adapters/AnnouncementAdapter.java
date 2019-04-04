package com.sakai.ug.sakaiapp.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.sakai.ug.sakaiapp.R;
import com.sakai.ug.sakaiapp.course_site_details.AnnouncementDetailActivity;
import com.sakai.ug.sakaiapp.models.announcement.Announcement;


public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.AnnouncementViewHolder> {


    private Announcement announcement = new Announcement();
    private Context context;
    private final onAnnouncementItemClickListener listener;

    public AnnouncementAdapter(Announcement announcement, Context context, onAnnouncementItemClickListener listener) {
        this.announcement = announcement;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AnnouncementViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.announcement_item, null);
        return new AnnouncementViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull AnnouncementViewHolder announcementViewHolder, int i) {

        announcementViewHolder.textViewTitle.setText(announcement.getAnnouncementCollection().get(i).getTitle());
        announcementViewHolder.textViewShortDesc.setText(Html.fromHtml(announcement.getAnnouncementCollection().get(i).getBody()));
        announcementViewHolder.imageView.setImageDrawable(announcementViewHolder.imageView.getResources().getDrawable(R.drawable.ic_announcement));

    }

    @Override
    public int getItemCount() {
        return announcement.getAnnouncementCollection().size();
    }

    class AnnouncementViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewTitle, textViewShortDesc;
        ImageView imageView;
        onAnnouncementItemClickListener onAnnouncementItemClickListener;

        public AnnouncementViewHolder(View itemView, onAnnouncementItemClickListener onAnnouncementItemClickListener) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            imageView = itemView.findViewById(R.id.imageView);
            this.onAnnouncementItemClickListener = onAnnouncementItemClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onAnnouncementItemClickListener.onItemClick(getAdapterPosition());
            Intent goToOneAnnouncement = new Intent(context, AnnouncementDetailActivity.class);
            goToOneAnnouncement.putExtra("AN_TITLE", announcement.getAnnouncementCollection().get(getAdapterPosition()).getTitle());
            goToOneAnnouncement.putExtra("AN_SAVED_BY", announcement.getAnnouncementCollection().get(getAdapterPosition()).getCreatedByDisplayName());
            //goToOneAnnouncement.putExtra("AN_MODIFIED_DATE", announcement.getAnnouncementCollection().get(getAdapterPosition()).getTitle());
            goToOneAnnouncement.putExtra("AN_BODY", announcement.getAnnouncementCollection().get(getAdapterPosition()).getBody());
            context.startActivity(goToOneAnnouncement);
        }
    }


    public interface onAnnouncementItemClickListener {
        void onItemClick(int position);
    }
}

