/*
package com.sakai.ug.sakaiapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sakai.ug.sakaiapp.R;
import com.sakai.ug.sakaiapp.models.syllabus.Syllabus;

public class SyllabusAdapter extends RecyclerView.Adapter<SyllabusAdapter.SyllabusViewHolder> {

    private Syllabus syllabus = new Syllabus();
    private Context context;


    public SyllabusAdapter(Syllabus syllabus, Context context) {
        this.syllabus = syllabus;
        this.context = context;
    }

    @NonNull
    @Override
    public SyllabusViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_syllabus, null);
        return new SyllabusViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SyllabusViewHolder syllabusViewHolder, int i) {

        syllabusViewHolder.textViewTitle.setText(syllabus.getItems().get(i).getTitle());
        syllabusViewHolder.textViewData.setText(syllabus.getItems().get(i).getData());
        syllabusViewHolder.textViewAttachment.setText(syllabus.getItems().get(i).getAttachments().get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return syllabus.getItems().size();
    }

    public class SyllabusViewHolder extends RecyclerView.ViewHolder{

        TextView textViewTitle, textViewData, textViewAttachment;
        public SyllabusViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.title);
            textViewData = itemView.findViewById(R.id.body);
            textViewAttachment = itemView.findViewById(R.id.attachment);
        }
    }
}
*/
