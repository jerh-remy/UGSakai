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
import com.sakai.ug.sakaiapp.models.AssignmentModel;

import java.util.List;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.AssignmentViewHolder> {

    private List<AssignmentModel> assignmentList;
    private final onAssignmentItemClickListener listener;
    private Context context;

    public AssignmentAdapter(List<AssignmentModel> assignmentList, Context context, onAssignmentItemClickListener listener) {
        this.assignmentList = assignmentList;
        this.context = context;
        this.listener = listener;
    }

    public AssignmentAdapter.AssignmentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.assignment_list, null);
        return new AssignmentViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignmentAdapter.AssignmentViewHolder assignmentViewHolder, int i) {

        AssignmentModel Assignment = assignmentList.get(i);
        assignmentViewHolder.textViewTime.setText(Assignment.getTime());
        assignmentViewHolder.textViewTitle.setText(Assignment.getTitle());
        assignmentViewHolder.imageView.setImageDrawable(context.getResources().getDrawable(Assignment.getImage()));
        assignmentViewHolder.imageView2.setImageDrawable(context.getResources().getDrawable(Assignment.getImage2()));

    }

    @Override
    public int getItemCount() {
        return assignmentList.size();
    }


    public class AssignmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewTitle, textViewTime;
        ImageView imageView, imageView2;
        onAssignmentItemClickListener onAssignmentItemClickListener;

        public AssignmentViewHolder(@NonNull View itemView, onAssignmentItemClickListener listener) {
            super(itemView);

            textViewTime = itemView.findViewById(R.id.textViewTime);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            imageView = itemView.findViewById(R.id.imageView);
            imageView2 = itemView.findViewById(R.id.imageView2);
            this.onAssignmentItemClickListener = listener;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onAssignmentItemClickListener.onItemClick(getAdapterPosition());
        }
    }


    public interface onAssignmentItemClickListener {
        void onItemClick(int position);
    }

}
