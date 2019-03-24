package com.sakai.ug.sakaiapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

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
import com.sakai.ug.sakaiapp.course_site_fragments.AnnouncementFragment;
import com.sakai.ug.sakaiapp.course_site_fragments.AssignmentsFragment;
import com.sakai.ug.sakaiapp.course_site_fragments.GradebookFragment;
import com.sakai.ug.sakaiapp.course_site_fragments.ProfileFragment;
import com.sakai.ug.sakaiapp.course_site_fragments.ResourcesFragment;
import com.sakai.ug.sakaiapp.course_site_fragments.SiteOverviewFragment;
import com.sakai.ug.sakaiapp.course_site_fragments.SyllabusFragment;
import com.sakai.ug.sakaiapp.main_fragments.SiteFragment;

public class CourseSiteActivity extends AppCompatActivity {


    final SiteOverviewFragment siteOverviewFragment = new SiteOverviewFragment();
    final AnnouncementFragment announcementFragment = new AnnouncementFragment();
    final AssignmentsFragment assignmentsFragment = new AssignmentsFragment();
    final GradebookFragment gradebookFragment = new GradebookFragment();
    final ProfileFragment profileFragment = new ProfileFragment();
    final ResourcesFragment resourcesFragment = new ResourcesFragment();
    final SyllabusFragment syllabusFragment = new SyllabusFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = siteOverviewFragment;


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_site);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setBackground(getResources().getDrawable(R.color.colorPrimary));
        toolbar.setTitle("Course Site");
        setSupportActionBar(toolbar);

        fm.beginTransaction().add(R.id.container, siteOverviewFragment).commit();
        fm.beginTransaction().add(R.id.container, profileFragment).hide(profileFragment).commit();
        fm.beginTransaction().add(R.id.container, announcementFragment).hide(announcementFragment).commit();
        fm.beginTransaction().add(R.id.container, syllabusFragment).hide(syllabusFragment).commit();
        fm.beginTransaction().add(R.id.container, resourcesFragment).hide(resourcesFragment).commit();
        fm.beginTransaction().add(R.id.container, assignmentsFragment).hide(assignmentsFragment).commit();
        fm.beginTransaction().add(R.id.container, gradebookFragment).hide(gradebookFragment).commit();


        AccountHeader accHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.cobalt)
                .addProfiles(new ProfileDrawerItem()
                        .withName("Jeremy Offori")
                        .withEmail("jeremy.offori@gmail.com")
                        .withIcon(R.drawable.profile)
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
        SecondaryDrawerItem s3 = new SecondaryDrawerItem().withIdentifier(4).withName("Resources").withIcon(R.drawable.folder);
        SecondaryDrawerItem s4 = new SecondaryDrawerItem().withIdentifier(5).withName("Assignments").withIcon(R.drawable.assignment);
        SecondaryDrawerItem s5 = new SecondaryDrawerItem().withIdentifier(6).withName("Gradebook").withIcon(R.drawable.gradebook);

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
                        s5
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
                        }
                        return false;
                    }
                }).build();

        result.addStickyFooterItem(new PrimaryDrawerItem().withName("\u00A9 2019 UG Sakai"));




    }

}

