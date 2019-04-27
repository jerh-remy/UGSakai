package com.sakai.ug.sakaiapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class BottomSheetFragment extends BottomSheetDialogFragment {

    public static BottomSheetFragment getInstance() {
        return new BottomSheetFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.bottomsheet, container, false);

        view.findViewById(R.id.gotoprofile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ProfileActivity.class));
                dismiss();
            }
        });

        view.findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferencesManager.getInstance(getContext()).Logout();
                PreferenceManager.getDefaultSharedPreferences(getContext()).edit().clear().apply();
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });
        return view;
    }


}