package com.sakai.ug.sakaiapp;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.sakai.ug.sakaiapp.course_site_fragments.AnnouncementFragment;
import com.sakai.ug.sakaiapp.course_site_fragments.AssignmentsFragment;
import com.sakai.ug.sakaiapp.course_site_fragments.GradebookFragment;
import com.sakai.ug.sakaiapp.course_site_fragments.ResourcesFragment;
import com.sakai.ug.sakaiapp.course_site_fragments.SyllabusFragment;
import com.sakai.ug.sakaiapp.main_fragments.ChatFragment;
import com.sakai.ug.sakaiapp.main_fragments.HomeFragment;
import com.sakai.ug.sakaiapp.main_fragments.NotificationFragment;
import com.sakai.ug.sakaiapp.main_fragments.SiteFragment;

import java.util.HashSet;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity {

    //declaring main fragments
    final HomeFragment homeFragment = new HomeFragment();
    final SiteFragment siteFragment = new SiteFragment();
    final AnnouncementFragment announcementFragment = new AnnouncementFragment();
    final AssignmentsFragment assignmentsFragment = new AssignmentsFragment();
    final SyllabusFragment syllabusFragment = new SyllabusFragment();
    final ResourcesFragment resourcesFragment  = new ResourcesFragment();
    final GradebookFragment gradebookFragment = new GradebookFragment();

    //final NotificationFragment notificationFragment = new NotificationFragment();
    //final ChatFragment chatFragment = new ChatFragment();
    final FragmentManager fm = getSupportFragmentManager();
    private LoginActivity loginActivity = new LoginActivity();
    private String cookieHeader;

    //Fragment active = homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HashSet<String> preferences = (HashSet) PreferenceManager
                .getDefaultSharedPreferences(this)
                .getStringSet("appCookies", new HashSet<>());

        for (String cookie : preferences) {
            cookieHeader = cookie;
            Log.d("CookieLogged",  cookie);
        }

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        /*toolbar.inflateMenu(R.menu.options_menu);
        toolbar.setContentInsetsAbsolute(0, 0);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.logout) {
                    SharedPreferencesManager.getInstance(getApplicationContext()).Logout();
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().clear().apply();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                    return true;
                } else {
                    return false;
                }
            }
        });*/


        BottomNavigationView navigation = findViewById(R.id.bnNavigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        CircleImageView circleImageView = findViewById(R.id.profile_click);
        String imageURL = SharedPreferencesManager.getInstance(this).getImageurl();
        GlideUrl glideUrl = new GlideUrl(imageURL, new LazyHeaders.Builder()
                .addHeader("Cookie", cookieHeader)
                .build());

        try {
            Glide.with(this).load(glideUrl).into(circleImageView);
            Log.d("Main", "glide request " + glideUrl);
        }catch (Exception e){
            e.getMessage();
        }

        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BottomSheetFragment fragment = BottomSheetFragment.getInstance();
                fragment.show(getSupportFragmentManager(), "Custom Bottom Sheet");

               /* PopupMenu popupMenu = new PopupMenu(getBaseContext(), circleImageView);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.logout:
                                SharedPreferencesManager.getInstance(getApplicationContext()).Logout();
                                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().clear().apply();
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                finish();
                                return true;

                            case R.id.settings:
                                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                                return true;

                        }

                        return false;
                    }
                });

                popupMenu.inflate(R.menu.options_menu);
                popupMenu.show();*/
            }
        });

       /* circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
            }
        });
*/
       /*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Window window = getWindow();
            View rootView = window.getDecorView().getRootView();
            rootView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        }*/

        fm.beginTransaction().add(R.id.fragment_container, homeFragment, "1").commit();
        /*fm.beginTransaction().add(R.id.fragment_container, announcementFragment).hide(announcementFragment).commit();
        fm.beginTransaction().add(R.id.fragment_container, syllabusFragment).hide(syllabusFragment).commit();
        fm.beginTransaction().add(R.id.fragment_container, resourcesFragment).hide(resourcesFragment).commit();
        fm.beginTransaction().add(R.id.fragment_container, assignmentsFragment).hide(assignmentsFragment).commit();
        fm.beginTransaction().add(R.id.fragment_container, gradebookFragment).hide(gradebookFragment).commit();*/
        //fm.beginTransaction().add(R.id.fragment_container, notificationFragment, "3").hide(notificationFragment).commit();
        //fm.beginTransaction().add(R.id.fragment_container, chatFragment, "4").hide(chatFragment).commit();

    }
/*

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }
*/


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_home:
                    //fm.beginTransaction().hide(active).show(homeFragment).commit();
                    fm.beginTransaction().replace(R.id.fragment_container, homeFragment).commit();
                    //active = homeFragment;
                    return true;
                case R.id.action_sites:
                    //fm.beginTransaction().hide(active).show(siteFragment).commit();
                    fm.beginTransaction().replace(R.id.fragment_container, siteFragment).commit();
                    //active = siteFragment;
                    return true;
                /*case R.id.action_notifications:
                    //fm.beginTransaction().hide(active).show(notificationFragment).commit();
                    //fm.beginTransaction().replace(R.id.fragment_container, notificationFragment).commit();
                    //active = notificationFragment;
                    return true;*/
               /* case R.id.action_chat:
                    //fm.beginTransaction().hide(active).show(chatFragment).commit();
                    fm.beginTransaction().replace(R.id.fragment_container, chatFragment).commit();
                    //active = chatFragment;
                    return true;*/

            }

            return false;
        }
    };


}
