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
import com.sakai.ug.sakaiapp.models.GradebookModel;
import com.sakai.ug.sakaiapp.models.announcement.AnnouncementCollection;
import com.sakai.ug.sakaiapp.models.gradebook.Assignment;
import com.sakai.ug.sakaiapp.models.gradebook.Gradebook;

import java.util.ArrayList;
import java.util.List;

public class GradebookAdapter extends RecyclerView.Adapter<GradebookAdapter.gradebookViewHolder> {

    private List<Assignment> gradebookList;
    private Context context;

    public GradebookAdapter(Context context) {
        this.gradebookList = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public GradebookAdapter.gradebookViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.gradebook_list, null);
        return new gradebookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GradebookAdapter.gradebookViewHolder gradebookViewHolder, int i) {

        gradebookViewHolder.textViewGradebookItem.setText(gradebookList.get(i).getItemName());
        gradebookViewHolder.textViewPoint.setText(String.valueOf(gradebookList.get(i).getPoints()));
        gradebookViewHolder.textViewGrade.setText(gradebookList.get(i).getGrade());
        gradebookViewHolder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_grade));


    }

    @Override
    public int getItemCount() {
        return gradebookList.size();
    }

    class gradebookViewHolder extends RecyclerView.ViewHolder {

        TextView textViewGrade, textViewGradebookItem, textViewPoint;
        ImageView imageView;
        public gradebookViewHolder(@NonNull View itemView) {
            super(itemView);


            textViewGrade = itemView.findViewById(R.id.textViewGrade);
            textViewPoint = itemView.findViewById(R.id.textViewPoint);
            textViewGradebookItem = itemView.findViewById(R.id.textViewGradebookItem);
            imageView = itemView.findViewById(R.id.imageView);

        }
    }

    public void addGrade(Assignment assignment) {
        gradebookList.add(assignment);
        notifyDataSetChanged();

    }

    public void reset() {
        gradebookList.clear();
        notifyDataSetChanged();
    }
}
