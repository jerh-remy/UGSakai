package com.sakai.ug.sakaiapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sakai.ug.sakaiapp.R;
import com.sakai.ug.sakaiapp.models.CourseSiteModel;

import java.util.List;

public class CourseSiteAdapter extends RecyclerView.Adapter<CourseSiteAdapter.CourseSiteViewHolder>{

    private List<CourseSiteModel> coursesiteList;
    private final CourseSiteAdapter.onCourseSiteItemClickListener listener;
    private Context context;

    public CourseSiteAdapter(List<CourseSiteModel> coursesiteList, Context context, CourseSiteAdapter.onCourseSiteItemClickListener listener) {
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

        CourseSiteModel courseSite = coursesiteList.get(i);
        courseSiteViewHolder.textViewLecturerName.setText(courseSite.getCourseLecturer());
        courseSiteViewHolder.textViewCourseSiteTitle.setText(courseSite.getCourseSiteTitle());
        courseSiteViewHolder.imageView.setImageDrawable(context.getResources().getDrawable(courseSite.getImage()));

    }

    @Override
    public int getItemCount() {
        return coursesiteList.size();
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
        }
    }


    public interface onCourseSiteItemClickListener {
        void onItemClick(int position);
    }

}


