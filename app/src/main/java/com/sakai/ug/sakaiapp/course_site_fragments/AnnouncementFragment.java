package com.sakai.ug.sakaiapp.course_site_fragments;

import android.content.Intent;
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
import com.sakai.ug.sakaiapp.adapters.AnnouncementAdapter;
import com.sakai.ug.sakaiapp.course_site_details.AnnouncementDetailActivity;
import com.sakai.ug.sakaiapp.models.AnnouncementModel;

import java.util.ArrayList;
import java.util.List;

public class AnnouncementFragment extends Fragment implements AnnouncementAdapter.onAnnouncementItemClickListener{

    List<AnnouncementModel> announceList;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_announcements, container, false);

        recyclerView = view.findViewById(R.id.announcement_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        announceList = new ArrayList<>();

        announceList.add(
                new AnnouncementModel(
                        "CSCD 418",
                        "this is mainly gibberish scbsk cshskshbjcs.msdcmsds cxnsiksa at he hehdssd school and stdff",
                        //"Data Communication and Networking II",
                        //"Dr Enerst Gyebi",
                        //60000,
                        R.drawable.ic_announcement));
        announceList.add(
                new AnnouncementModel(
                        "CSCD 418",
                        "this is mainly gibberish scbsk cshskshbjcs.msdcmsds cxnsiksa at he hehdssd school and stdff",
                        //"Data Communication and Networking II",
                        //"Dr Enerst Gyebi",
                        //60000,
                        R.drawable.ic_announcement));

        announceList.add(
                new AnnouncementModel(
                        "CSCD 418",
                        "this is mainly gibberish scbsk cshskshbjcs.msdcmsds cxnsiksa at he hehdssd school and stdff",
                        //"Data Communication and Networking II",
                        //"Dr Enerst Gyebi",
                        //60000,
                        R.drawable.ic_announcement));
        announceList.add(
                new AnnouncementModel(
                        "CSCD 418",
                        "this is mainly gibberish scbsk cshskshbjcs.msdcmsds cxnsiksa at he hehdssd school and stdff",
                        //"Data Communication and Networking II",
                        //"Dr Enerst Gyebi",
                        //60000,
                        R.drawable.ic_announcement));
        announceList.add(
                new AnnouncementModel(
                        "CSCD 418",
                        "this is mainly gibberish scbsk cshskshbjcs.msdcmsds cxnsiksa at he hehdssd school and stdff",
                        //"Data Communication and Networking II",
                        //"Dr Enerst Gyebi",
                        //60000,
                        R.drawable.ic_announcement));
        announceList.add(
                new AnnouncementModel(
                        "CSCD 418",
                        "this is mainly gibberish scbsk cshskshbjcs.msdcmsds cxnsiksa at he hehdssd school and stdff",
                        //"Data Communication and Networking II",
                        //"Dr Enerst Gyebi",
                        //60000,
                        R.drawable.ic_announcement));
        announceList.add(
                new AnnouncementModel(
                        "CSCD 418",
                        "this is mainly gibberish scbsk cshskshbjcs.msdcmsds cxnsiksa at he hehdssd school and stdff",
                        //"Data Communication and Networking II",
                        //"Dr Enerst Gyebi",
                        //60000,
                        R.drawable.ic_announcement));
        announceList.add(
                new AnnouncementModel(
                        "CSCD 418",
                        "this is mainly gibberish scbsk cshskshbjcs.msdcmsds cxnsiksa at he hehdssd school and stdff",
                        //"Data Communication and Networking II",
                        //"Dr Enerst Gyebi",
                        //60000,
                        R.drawable.ic_announcement));
        announceList.add(
                new AnnouncementModel(
                        "CSCD 418",
                        "this is mainly gibberish scbsk cshskshbjcs.msdcmsds cxnsiksa at he hehdssd school and stdff",
                        //"Data Communication and Networking II",
                        //"Dr Enerst Gyebi",
                        //60000,
                        R.drawable.ic_announcement));
        announceList.add(
                new AnnouncementModel(
                        "CSCD 418",
                        "this is mainly gibberish scbsk cshskshbjcs.msdcmsds cxnsiksa at he hehdssd school and stdff",
                        //"Data Communication and Networking II",
                        //"Dr Enerst Gyebi",
                        //60000,
                        R.drawable.ic_announcement));
        announceList.add(
                new AnnouncementModel(
                        "CSCD 418",
                        "this is mainly gibberish scbsk cshskshbjcs.msdcmsds cxnsiksa at he hehdssd school and stdff",
                        //"Data Communication and Networking II",
                        //"Dr Enerst Gyebi",
                        //60000,
                        R.drawable.ic_announcement));
        announceList.add(
                new AnnouncementModel(
                        "CSCD 418",
                        "this is mainly gibberish scbsk cshskshbjcs.msdcmsds cxnsiksa at he hehdssd school and stdff",
                        //"Data Communication and Networking II",
                        //"Dr Enerst Gyebi",
                        //60000,
                        R.drawable.ic_announcement));
        announceList.add(
                new AnnouncementModel(
                        "CSCD 418",
                        "this is mainly gibberish scbsk cshskshbjcs.msdcmsds cxnsiksa at he hehdssd school and stdff",
                        //"Data Communication and Networking II",
                        //"Dr Enerst Gyebi",
                        //60000,
                        R.drawable.ic_announcement));


        AnnouncementAdapter adapter = new AnnouncementAdapter(announceList, this.getActivity(), this);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onItemClick(int position) {
        announceList.get(position);
        Intent intent = new Intent(getActivity(), AnnouncementDetailActivity.class);
        startActivity(intent);
    }
}
