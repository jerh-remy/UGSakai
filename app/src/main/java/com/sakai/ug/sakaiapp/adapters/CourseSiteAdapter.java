package com.sakai.ug.sakaiapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sakai.ug.sakaiapp.CourseSiteActivity;
import com.sakai.ug.sakaiapp.R;
import com.sakai.ug.sakaiapp.course_site_details.AssignmentDetailActivity;
import com.sakai.ug.sakaiapp.models.site.Site;

import java.util.List;

public class CourseSiteAdapter extends RecyclerView.Adapter<CourseSiteAdapter.CourseSiteViewHolder>{

    private Site coursesiteList;
    private final CourseSiteAdapter.onCourseSiteItemClickListener listener;
    private Context context;

    public CourseSiteAdapter(Site coursesiteList, Context context, CourseSiteAdapter.onCourseSiteItemClickListener listener) {
        this.coursesiteList = coursesiteList;
        this.context = context;
        this.listener = listener;
    }

    public CourseSiteAdapter.CourseSiteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_course_site, null);
        return new CourseSiteAdapter.CourseSiteViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseSiteAdapter.CourseSiteViewHolder courseSiteViewHolder, int i) {

        courseSiteViewHolder.textViewLecturerName.setText(coursesiteList.getSiteCollection().get(i).getProps().getContactName());
        courseSiteViewHolder.textViewCourseSiteTitle.setText(coursesiteList.getSiteCollection().get(i).getEntityTitle());
        courseSiteViewHolder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_sites));

    }

    @Override
    public int getItemCount() {
        return coursesiteList.getSiteCollection().size();
    }


    public class CourseSiteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewCourseSiteTitle, textViewLecturerName;
        ImageView imageView;
        CourseSiteAdapter.onCourseSiteItemClickListener onCourseSiteItemClickListener;

        public CourseSiteViewHolder(@NonNull View itemView, CourseSiteAdapter.onCourseSiteItemClickListener listener) {
            super(itemView);

            textViewLecturerName = itemView.findViewById(R.id.textViewName);
            textViewCourseSiteTitle = itemView.findViewById(R.id.textViewTitle);
            imageView = itemView.findViewById(R.id.imageView);
            this.onCourseSiteItemClickListener = listener;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onCourseSiteItemClickListener.onItemClick(getAdapterPosition());
            Intent goToOneSite = new Intent(context, CourseSiteActivity.class);
            goToOneSite.putExtra("SITE_ID", coursesiteList.getSiteCollection().get(getAdapterPosition()).getEntityId());
            context.startActivity(goToOneSite);
        }
    }


    public interface onCourseSiteItemClickListener {
        void onItemClick(int position);
    }

}


