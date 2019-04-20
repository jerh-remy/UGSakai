package com.sakai.ug.sakaiapp.main_fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sakai.ug.sakaiapp.LoginActivity;
import com.sakai.ug.sakaiapp.R;
import com.sakai.ug.sakaiapp.SharedPreferencesManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeFragment extends Fragment {

    TextView welcome, date;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        welcome = view.findViewById(R.id.welcome_username);
        date = view.findViewById(R.id.current_date);


        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("EEE, d MMMM yyyy");
        String formattedDate = df.format(c);
        date.setText(formattedDate);

        welcome.setText(getText(R.string.welcome) + SharedPreferencesManager.getInstance(getContext()).getFullname());

        return view;

    }
}
