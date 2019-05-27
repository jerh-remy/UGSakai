package com.sakai.ug.sakaiapp;

import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;

import java.util.HashSet;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class ProfileActivity extends AppCompatActivity {
    //@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

    TextView fullname, username, personal_summary, email, phone_num, linkedin, facebook, twitter;
    private String cookieHeader;


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        HashSet<String> preferences = (HashSet) PreferenceManager
                .getDefaultSharedPreferences(this)
                .getStringSet("appCookies", new HashSet<>());

        for (String cookie : preferences) {
            cookieHeader = cookie;
            Log.d("CookieLogged", cookie);
        }


        fullname = findViewById(R.id.fullname);
        username = findViewById(R.id.userID);
        email = findViewById(R.id.email);
        personal_summary = findViewById(R.id.personal_summary);
        phone_num = findViewById(R.id.phone_no);
        linkedin = findViewById(R.id.linkedin);
        facebook = findViewById(R.id.facebook);
        twitter = findViewById(R.id.twitter);

        try {
            fullname.setText(SharedPreferencesManager.getInstance(getApplicationContext()).getFullname());
            username.setText(SharedPreferencesManager.getInstance(getApplicationContext()).getUsername());
            email.setText(SharedPreferencesManager.getInstance(getApplicationContext()).getEmail());
            personal_summary.setText(Html.fromHtml(SharedPreferencesManager.getInstance(getApplicationContext()).getPersonalSummary()));
            phone_num.setText(SharedPreferencesManager.getInstance(getApplicationContext()).getPhoneNo());
            linkedin.setText(SharedPreferencesManager.getInstance(getApplicationContext()).getLinkedin());
            facebook.setText(SharedPreferencesManager.getInstance(getApplicationContext()).getFacebook());
            twitter.setText(SharedPreferencesManager.getInstance(getApplicationContext()).getTwitter());
        }catch (Exception e)
        {
            Log.d("Profile details", "one or more profile fields haven't been filled.");
        }

        CircleImageView circleImageView = findViewById(R.id.profile_pic_large);
        String imageURL = SharedPreferencesManager.getInstance(this).getImageurl();
        GlideUrl glideUrl = new GlideUrl(imageURL, new LazyHeaders.Builder()
                .addHeader("Cookie", cookieHeader)
                .build());

        Glide.with(this).load(glideUrl).into(circleImageView);

        //transition(withCrossFade())
        //.thumbnail(0.5f)

        final Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setBackground(getResources().getDrawable(R.color.colorPrimary));
        toolbar.setTitle("My Profile");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
