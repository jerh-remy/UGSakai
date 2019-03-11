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
import com.sakai.ug.sakaiapp.models.Notification;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.notificationsViewHolder> {


    private List<Notification> notificationList;
    private Context context;

    public NotificationAdapter(List<Notification> notificationList, Context context) {
        this.notificationList = notificationList;
        this.context = context;
    }

    @NonNull
    @Override
    public notificationsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_notification, null);
        return new notificationsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull notificationsViewHolder notificationsViewHolder, int i) {

        Notification notification = notificationList.get(i);
        notificationsViewHolder.textViewCourseCode.setText(notification.getCourse_code());
        notificationsViewHolder.textViewTime.setText(notification.getTime());
        notificationsViewHolder.textViewTitle.setText(notification.getTitle());
        notificationsViewHolder.textViewShortDesc.setText(notification.getShortdesc());
        notificationsViewHolder.imageView.setImageDrawable(context.getResources().getDrawable(notification.getImage()));

    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    class notificationsViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc, textViewTime, textViewCourseCode;
        ImageView imageView;

        public notificationsViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTime = itemView.findViewById(R.id.textViewTime);
            textViewCourseCode = itemView.findViewById(R.id.textViewCourseCode);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            imageView = itemView.findViewById(R.id.imageView);

        }
    }
}

