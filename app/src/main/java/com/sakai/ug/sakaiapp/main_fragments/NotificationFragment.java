package com.sakai.ug.sakaiapp.main_fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sakai.ug.sakaiapp.R;
import com.sakai.ug.sakaiapp.adapters.NotificationAdapter;
import com.sakai.ug.sakaiapp.models.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationFragment extends Fragment {

    private List<Notification> notificationList;
    RecyclerView recyclerView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));


        notificationList = new ArrayList<>();

        notificationList.add(
                new Notification(
                        "CSCD 400",
                        "Hi Class, I have uploaded the lecture material for the sixth week.",
                        "5:12pm",
                        "New resource for CSCD 400",
                        R.drawable.ic_notifications
                )
        );
        notificationList.add(
                new Notification(
                        "CSCD 400",
                        "Hi Class, I have uploaded the lecture material for the sixth week.",
                        "5:12pm",
                        "New resource for CSCD 400",
                        R.drawable.ic_notifications
                )
        );
        notificationList.add(
                new Notification(
                        "CSCD 400",
                        "Hi Class, I have uploaded the lecture material for the sixth week.",
                        "5:12pm",
                        "New resource for CSCD 400",
                        R.drawable.ic_notifications
                )
        );
        notificationList.add(
                new Notification(
                        "CSCD 400",
                        "Hi Class, I have uploaded the lecture material for the sixth week.",
                        "5:12pm",
                        "New resource for CSCD 400",
                        R.drawable.ic_notifications

                )
        );
        notificationList.add(
                new Notification(
                        "CSCD 400",
                        "Hi Class, I have uploaded the lecture material for the sixth week.",
                        "5:12pm",
                        "New resource for CSCD 400",
                        R.drawable.ic_notifications

                )
        );
        notificationList.add(
                new Notification(
                        "CSCD 400",
                        "Hi Class, I have uploaded the lecture material for the sixth week.",
                        "5:12pm",
                        "New resource for CSCD 400",
                        R.drawable.ic_notifications

                )
        );
        notificationList.add(
                new Notification(
                        "CSCD 400",
                        "Hi Class, I have uploaded the lecture material for the sixth week.",
                        "5:12pm",
                        "New resource for CSCD 400",
                        R.drawable.ic_notifications

                )
        );
        notificationList.add(
                new Notification(
                        "CSCD 400",
                        "Hi Class, I have uploaded the lecture material for the sixth week.",
                        "5:12pm",
                        "New resource for CSCD 400",
                        R.drawable.ic_notifications

                )
        );
        notificationList.add(
                new Notification(
                        "CSCD 400",
                        "Hi Class, I have uploaded the lecture material for the sixth week.",
                        "5:12pm",
                        "New resource for CSCD 400",
                        R.drawable.ic_notifications

                )
        );
        notificationList.add(
                new Notification(
                        "CSCD 400",
                        "Hi Class, I have uploaded the lecture material for the sixth week.",
                        "5:12pm",
                        "New resource for CSCD 400",
                        R.drawable.ic_notifications

                )
        );
        notificationList.add(
                new Notification(
                        "CSCD 400",
                        "Hi Class, I have uploaded the lecture material for the sixth week.",
                        "5:12pm",
                        "New resource for CSCD 400",
                        R.drawable.ic_notifications

                )
        );
        notificationList.add(
                new Notification(
                        "CSCD 400",
                        "Hi Class, I have uploaded the lecture material for the sixth week.",
                        "5:12pm",
                        "New resource for CSCD 400",
                        R.drawable.ic_notifications

                )
        );
        notificationList.add(
                new Notification(
                        "CSCD 400",
                        "Hi Class, I have uploaded the lecture material for the sixth week.",
                        "5:12pm",
                        "New resource for CSCD 400",
                        R.drawable.ic_notifications

                )
        );


        NotificationAdapter adapter = new NotificationAdapter(notificationList, this.getActivity());
        recyclerView.setAdapter(adapter);


        return view;
    }

}
