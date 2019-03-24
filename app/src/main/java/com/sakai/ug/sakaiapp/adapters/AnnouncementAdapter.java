package com.sakai.ug.sakaiapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.sakai.ug.sakaiapp.R;
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
        announcementViewHolder.textViewShortDesc.setText(announcement.getAnnouncementCollection().get(i).getBody());
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
        }
    }


    public interface onAnnouncementItemClickListener {
        void onItemClick(int position);
    }
}

