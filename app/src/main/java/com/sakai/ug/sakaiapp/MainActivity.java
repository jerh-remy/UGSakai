package com.sakai.ug.sakaiapp;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.sakai.ug.sakaiapp.fragments.HomeFragment;
import com.sakai.ug.sakaiapp.fragments.NotificationFragment;
import com.sakai.ug.sakaiapp.fragments.SiteFragment;

public class MainActivity extends AppCompatActivity {

    private HomeFragment homeFragment = new HomeFragment();
    private SiteFragment siteFragment = new SiteFragment();
    private NotificationFragment notificationFragment = new NotificationFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ViewPager pager = findViewById(R.id.pager);

        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                switch (i) {
                    case 0:
                        return homeFragment;
                    case 1:
                        return siteFragment;
                    case 2:
                        return notificationFragment;
                    default:
                        return new NotificationFragment(); // this is just a placeholder
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        });

        final BottomNavigationView bnNavigation = findViewById(R.id.bnNavigation);
        bnNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        pager.setCurrentItem(0);
                        break;
                    case R.id.action_sites:
                        pager.setCurrentItem(1);
                        break;
                    case R.id.action_notifications:
                        pager.setCurrentItem(2);
                        break;
                    default:
                        pager.setCurrentItem(3);
                }

                return true;
            }
        });

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        bnNavigation.setSelectedItemId(R.id.action_home);
                        break;
                    case 1:
                        bnNavigation.setSelectedItemId(R.id.action_sites);
                        break;
                    case 2:
                        bnNavigation.setSelectedItemId(R.id.action_notifications);
                        break;

                    default:
                        bnNavigation.setSelectedItemId(R.id.action_chat);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}
