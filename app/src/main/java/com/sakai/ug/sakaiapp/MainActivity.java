package com.sakai.ug.sakaiapp;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.sakai.ug.sakaiapp.main_fragments.ChatFragment;
import com.sakai.ug.sakaiapp.main_fragments.HomeFragment;
import com.sakai.ug.sakaiapp.main_fragments.NotificationFragment;
import com.sakai.ug.sakaiapp.main_fragments.SiteFragment;


public class MainActivity extends AppCompatActivity {

    //declaring main fragments
    final HomeFragment homeFragment = new HomeFragment();
    final SiteFragment siteFragment = new SiteFragment();
    final NotificationFragment notificationFragment = new NotificationFragment();
    final ChatFragment chatFragment = new ChatFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.bnNavigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fm.beginTransaction().add(R.id.fragment_container, homeFragment, "1").commit();
        fm.beginTransaction().add(R.id.fragment_container, siteFragment, "2").hide(siteFragment).commit();
        fm.beginTransaction().add(R.id.fragment_container, notificationFragment, "3").hide(notificationFragment).commit();
        fm.beginTransaction().add(R.id.fragment_container, chatFragment, "4").hide(chatFragment).commit();

    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_home:
                    fm.beginTransaction().hide(active).show(homeFragment).commit();
                    active = homeFragment;
                    return true;
                case R.id.action_sites:
                    fm.beginTransaction().hide(active).show(siteFragment).commit();
                    active = siteFragment;
                    return true;
                case R.id.action_notifications:
                    fm.beginTransaction().hide(active).show(notificationFragment).commit();
                    active = notificationFragment;
                    return true;
                case R.id.action_chat:
                    fm.beginTransaction().hide(active).show(chatFragment).commit();
                    active = chatFragment;
                    return true;

            }

            return false;
        }
    };



}
