package com.sakai.ug.sakaiapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sakai.ug.sakaiapp.R;
import com.sakai.ug.sakaiapp.course_site_details.AssignmentDetailActivity;
import com.sakai.ug.sakaiapp.course_site_fragments.AnnouncementFragment;
import com.sakai.ug.sakaiapp.models.assignment.Assignment;
import com.sakai.ug.sakaiapp.models.assignment.AssignmentCollection;

import java.util.ArrayList;
import java.util.List;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.AssignmentViewHolder> {

    //private AssignmentCollection assignmentCollection = new AssignmentCollection();
    private List<AssignmentCollection> assignmentCollectionList;
    private Context context;
    private final onAssignmentItemClickListener listener;

    public AssignmentAdapter(Context context, onAssignmentItemClickListener listener) {
        this.context = context;
        this.listener = listener;
        assignmentCollectionList = new ArrayList<>();
    }

    public AssignmentAdapter.AssignmentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.assignment_list, null);
        return new AssignmentViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignmentAdapter.AssignmentViewHolder assignmentViewHolder, int i) {


        assignmentViewHolder.textViewTime.setText(assignmentCollectionList.get(i).getTimeCreated().getDisplay());
        assignmentViewHolder.textViewTitle.setText(assignmentCollectionList.get(i).getTitle());
        assignmentViewHolder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.assignment));
        //assignmentViewHolder.imageView2.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_attach_file_black_24dp));

        assignmentViewHolder.asssignmentclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotooneasignment = new Intent(context, AssignmentDetailActivity.class);
                gotooneasignment.putExtra("AS_TITLE", assignmentCollectionList.get(i).getTitle());
                gotooneasignment.putExtra("AS_DUEDATE", assignmentCollectionList.get(i).getDueTimeString());
                gotooneasignment.putExtra("AS_STATUS", assignmentCollectionList.get(i).getStatus());
                gotooneasignment.putExtra("AS_INSTRUCTION", assignmentCollectionList.get(i).getInstructions());
                context.startActivity(gotooneasignment);
            }
        });

    }

    public void addAssignment(AssignmentCollection assignmentCollection) {
        assignmentCollectionList.add(assignmentCollection);
        notifyDataSetChanged();

    }

    public void reset() {
        assignmentCollectionList.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return assignmentCollectionList.size();
    }


    public class AssignmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewTitle, textViewTime;
        ImageView imageView, imageView2;
        onAssignmentItemClickListener onAssignmentItemClickListener;
        CardView asssignmentclick;

        public AssignmentViewHolder(@NonNull View itemView, onAssignmentItemClickListener listener) {
            super(itemView);

            textViewTime = itemView.findViewById(R.id.textViewTime);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            imageView = itemView.findViewById(R.id.imageView);
            //imageView2 = itemView.findViewById(R.id.imageView2);
            this.onAssignmentItemClickListener = listener;
            asssignmentclick = itemView.findViewById(R.id.asignmentcardview);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            assignmentCollectionList.get(getAdapterPosition());
            //onAssignmentItemClickListener.onItemClick(getAdapterPosition());
        }
    }


    public interface onAssignmentItemClickListener {
        void onItemClick(int position);
    }

}
