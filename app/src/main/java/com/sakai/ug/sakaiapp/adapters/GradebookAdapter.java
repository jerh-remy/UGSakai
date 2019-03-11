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

import java.util.List;

public class GradebookAdapter extends RecyclerView.Adapter<GradebookAdapter.gradebookViewHolder> {

    private List<GradebookModel> gradebookList;
    private Context context;

    public GradebookAdapter(List<GradebookModel> gradebookList, Context context) {
        this.gradebookList = gradebookList;
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

        GradebookModel Gradebook = gradebookList.get(i);
        gradebookViewHolder.textViewGradebookItem.setText(Gradebook.getItem());
        gradebookViewHolder.textViewGrade.setText(Gradebook.getGrade());
        gradebookViewHolder.imageView.setImageDrawable(context.getResources().getDrawable(Gradebook.getImage()));


    }

    @Override
    public int getItemCount() {
        return gradebookList.size();
    }

    class gradebookViewHolder extends RecyclerView.ViewHolder {

        TextView textViewGrade, textViewGradebookItem;
        ImageView imageView;
        public gradebookViewHolder(@NonNull View itemView) {
            super(itemView);


            textViewGrade = itemView.findViewById(R.id.textViewGrade);
            textViewGradebookItem = itemView.findViewById(R.id.textViewGradebookItem);
            imageView = itemView.findViewById(R.id.imageView);

        }
    }
}
