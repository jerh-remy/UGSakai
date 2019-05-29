package com.sakai.ug.sakaiapp;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.sakai.ug.sakaiapp.APIservices.ApiClient;
import com.sakai.ug.sakaiapp.APIservices.RosterInterface;
import com.sakai.ug.sakaiapp.course_site_fragments.AnnouncementFragment;
import com.sakai.ug.sakaiapp.course_site_fragments.AssignmentsFragment;
import com.sakai.ug.sakaiapp.course_site_fragments.GradebookFragment;
import com.sakai.ug.sakaiapp.course_site_fragments.ProfileFragment;
import com.sakai.ug.sakaiapp.course_site_fragments.ResourcesFragment;
import com.sakai.ug.sakaiapp.course_site_fragments.SiteOverviewFragment;
import com.sakai.ug.sakaiapp.course_site_fragments.SyllabusFragment;
import com.sakai.ug.sakaiapp.database.SakaiDatabase;
import com.sakai.ug.sakaiapp.main_fragments.SiteFragment;
import com.sakai.ug.sakaiapp.models.roster.Roster;

import java.util.HashSet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseSiteActivity extends AppCompatActivity {


    Bundle bundle1;

    //declaring all course site fragments
    final SiteOverviewFragment siteOverviewFragment = new SiteOverviewFragment();
    final AnnouncementFragment announcementFragment = new AnnouncementFragment();
    final AssignmentsFragment assignmentsFragment = new AssignmentsFragment();
    final GradebookFragment gradebookFragment = new GradebookFragment();
    final ProfileFragment profileFragment = new ProfileFragment();
    final ResourcesFragment resourcesFragment = new ResourcesFragment();
    final SyllabusFragment syllabusFragment = new SyllabusFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = siteOverviewFragment;

    private String cookieHeader;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_site);


        final Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setBackground(getResources().getDrawable(R.color.colorPrimary));
        toolbar.setTitle("Course Site");
        setSupportActionBar(toolbar);

        //getting the cookie from shared preferences
        HashSet<String> preferences = (HashSet) PreferenceManager
                .getDefaultSharedPreferences(this)
                .getStringSet("appCookies", new HashSet<>());

        for (String cookie : preferences) {
            cookieHeader = cookie;
            Log.d("CookieLogged", cookie);
        }

        //getting extras from course site adapter
        final String siteid = getIntent().getStringExtra("SITE_ID");
        final String sitedescription = getIntent().getStringExtra("SITE_DESCRIPTION");
        final String sitetitle = getIntent().getStringExtra("SITE_TITLE");
        final String instructor = getIntent().getStringExtra("SITE_INSTRUCTOR");


        //passing extras into bundle to be shared among all course sites
        bundle1 = new Bundle();
        bundle1.putString("COURSE_ID", siteid);
        bundle1.putString("COURSE_DESCRIPTION", sitedescription);
        bundle1.putString("COURSE_TITLE", sitetitle);
        bundle1.putString("COURSE_INSTRUCTOR", instructor);
        Log.d("bundle1", "onCreate: " + bundle1);


        siteOverviewFragment.setArguments(bundle1);
        profileFragment.setArguments(bundle1);
        announcementFragment.setArguments(bundle1);
        syllabusFragment.setArguments(bundle1);
        resourcesFragment.setArguments(bundle1);
        assignmentsFragment.setArguments(bundle1);
        gradebookFragment.setArguments(bundle1);



        //committing all the fragments and making the site overview fragment the only initially visible one
        fm.beginTransaction().add(R.id.container, siteOverviewFragment).commit();
        fm.beginTransaction().add(R.id.container, profileFragment).hide(profileFragment).commit();
        fm.beginTransaction().add(R.id.container, announcementFragment).hide(announcementFragment).commit();
        fm.beginTransaction().add(R.id.container, syllabusFragment).hide(syllabusFragment).commit();
        fm.beginTransaction().add(R.id.container, resourcesFragment).hide(resourcesFragment).commit();
        fm.beginTransaction().add(R.id.container, assignmentsFragment).hide(assignmentsFragment).commit();
        fm.beginTransaction().add(R.id.container, gradebookFragment).hide(gradebookFragment).commit();


        String imageURL = SharedPreferencesManager.getInstance(this).getImageurl();
        GlideUrl glideUrl = new GlideUrl(imageURL, new LazyHeaders.Builder()
                .addHeader("Cookie", cookieHeader)
                .build());

        /*Glide.with(this).load(glideUrl).into(circleImageView);
*/
        //material drawer stuff
        AccountHeader accHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.cobalt)
                .addProfiles(new ProfileDrawerItem()
                        .withName(SharedPreferencesManager.getInstance(getApplicationContext()).getFullname())
                        .withEmail(SharedPreferencesManager.getInstance(getApplicationContext()).getEmail())
/*
                        .withIcon(R.drawable.ic_outline_tag_faces_24px)
*/
                        .withIdentifier(100)
                ).withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        return false;
                    }
                })
                .withSelectionListEnabledForSingleProfile(false)
                .withTranslucentStatusBar(true)
                .build();

        //PrimaryDrawerItem empty = new PrimaryDrawerItem().withIdentifier(8).withName("").withEnabled(false);

        SectionDrawerItem tools = new SectionDrawerItem().withIdentifier(1).withName("Tools").withSelectable(false);
        PrimaryDrawerItem all_sites = new PrimaryDrawerItem().withIdentifier(0).withName("Overview").withIcon(R.drawable.ic_sites);

        SecondaryDrawerItem s1 = new SecondaryDrawerItem().withIdentifier(2).withName("Announcements").withIcon(R.drawable.ic_announcement);
        SecondaryDrawerItem s2 = new SecondaryDrawerItem().withIdentifier(3).withName("Syllabus").withIcon(R.drawable.syllabus);
        SecondaryDrawerItem s3 = new SecondaryDrawerItem().withIdentifier(4).withName("Resources").withIcon(R.drawable.ic_outline_folder_open_24px);
        SecondaryDrawerItem s4 = new SecondaryDrawerItem().withIdentifier(5).withName("Assignments").withIcon(R.drawable.assignment);
        SecondaryDrawerItem s5 = new SecondaryDrawerItem().withIdentifier(6).withName("Gradebook").withIcon(R.drawable.gradebook);
        SecondaryDrawerItem s6 = new SecondaryDrawerItem().withIdentifier(7).withName("Course Lecturer Evaluation").withIcon(R.drawable.ic_outline_show_chart_24px);

        //create the drawer and remember the `Drawer` result object
        final Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withSelectedItem(-1)
                .withActionBarDrawerToggle(true)
                .withAccountHeader(accHeader)
                .withCloseOnClick(true)
                .addDrawerItems(
                        all_sites,
                        tools,
                        s1,
                        s2,
                        s3,
                        s4,
                        s5,
                        s6
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem.getIdentifier() == 0) {
                            // load site overview screen
                            toolbar.setTitle("Course site");
                            fm.beginTransaction().hide(active).show(siteOverviewFragment).commit();
                            active = siteOverviewFragment;
                        } else if (drawerItem.getIdentifier() == 2) {
                            // load announcement screen
                            toolbar.setTitle("Announcements");
                            fm.beginTransaction().hide(active).show(announcementFragment).commit();
                            active = announcementFragment;
                        } else if (drawerItem.getIdentifier() == 3) {
                            // load syllabus screen
                            toolbar.setTitle("Syllabus");
                            fm.beginTransaction().hide(active).show(syllabusFragment).commit();
                            active = syllabusFragment;
                        } else if (drawerItem.getIdentifier() == 4) {
                            // load resources screen
                            toolbar.setTitle("Resources");
                            fm.beginTransaction().hide(active).show(resourcesFragment).commit();
                            active = resourcesFragment;
                        } else if (drawerItem.getIdentifier() == 5) {
                            // load assignments screen
                            toolbar.setTitle("Assignments");
                            fm.beginTransaction().hide(active).show(assignmentsFragment).commit();
                            active = assignmentsFragment;
                        } else if (drawerItem.getIdentifier() == 6) {
                            // load gradebook screen
                            toolbar.setTitle("Gradebook");
                            fm.beginTransaction().hide(active).show(gradebookFragment).commit();
                            active = gradebookFragment;
                        }else if (drawerItem.getIdentifier() == 7) {
                            // load gradebook screen
                            Intent goToCLE = new Intent(CourseSiteActivity.this, CourseLecturerEvaluationActivity.class);
                            goToCLE.putExtras(bundle1);
                            startActivity(goToCLE);
                        }
                        return false;
                    }
                }).build();

        result.addStickyFooterItem(new PrimaryDrawerItem().withName("\u00A9 2019 UG Sakai"));




    }

}

