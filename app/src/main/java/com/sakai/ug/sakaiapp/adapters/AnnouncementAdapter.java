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
import com.sakai.ug.sakaiapp.models.AnnouncementModel;

import java.util.List;


public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.AnnouncementViewHolder> {


    private List<AnnouncementModel> announceList;
    private Context context;
    private final onAnnouncementItemClickListener listener;

    public AnnouncementAdapter(List<AnnouncementModel> announceList, Context context, onAnnouncementItemClickListener listener) {
        this.announceList = announceList;
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

        AnnouncementModel Announce = announceList.get(i);
        announcementViewHolder.textViewTitle.setText(Announce.getCourse_code());
        announcementViewHolder.textViewShortDesc.setText(Announce.getShortdesc());
        announcementViewHolder.imageView.setImageDrawable(context.getResources().getDrawable(Announce.getImage()));

    }

    @Override
    public int getItemCount() {
        return announceList.size();
    }

    class AnnouncementViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

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

