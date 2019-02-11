package com.sakai.ug.sakaiapp;

import android.app.Activity;

import android.content.Intent;
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

public class NavigationDrawerUtil {

    public static void getDrawer(final Activity activity, Toolbar toolbar) {

        AccountHeader accHeader = new AccountHeaderBuilder()
                .withActivity(activity)
                .withHeaderBackground(R.drawable.cobalt)
                .addProfiles(new ProfileDrawerItem()
                        .withName("Jeremy Offori")
                        .withEmail("jeremy.offori@gmail.com")
                        .withIcon(R.drawable.profile)
                )
                .withSelectionListEnabledForSingleProfile(false)
                .withTranslucentStatusBar(true)
                .build();

        PrimaryDrawerItem empty= new PrimaryDrawerItem().withIdentifier(8).withName("").withEnabled(false);

        SectionDrawerItem tools = new SectionDrawerItem().withIdentifier(1).withName("Tools").withSelectable(false);
        PrimaryDrawerItem all_sites = new PrimaryDrawerItem().withIdentifier(0).withName("All Sites").withIcon(R.drawable.ic_sites);

        SecondaryDrawerItem s1 = new SecondaryDrawerItem().withIdentifier(2).withName("Announcements").withIcon(R.drawable.ic_announcement);
        SecondaryDrawerItem s2 = new SecondaryDrawerItem().withIdentifier(3).withName("Syllabus").withIcon(R.drawable.syllabus);
        SecondaryDrawerItem s3 = new SecondaryDrawerItem().withIdentifier(4).withName("Resources").withIcon(R.drawable.folder);
        SecondaryDrawerItem s4 = new SecondaryDrawerItem().withIdentifier(5).withName("Assignments").withIcon(R.drawable.assignment);
        SecondaryDrawerItem s5 = new SecondaryDrawerItem().withIdentifier(6).withName("Gradebook").withIcon(R.drawable.gradebook);

        //create the drawer and remember the `Drawer` result object
        final Drawer result = new DrawerBuilder()
                .withActivity(activity)
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
                    // load all sites screen
                    Intent intent = new Intent(activity, CourseSiteActivity.class);
                    view.getContext().startActivity(intent);
                }else if (drawerItem.getIdentifier() == 2 && !(activity instanceof AnnouncementActivity)) {
                    // load announcement screen
                    Intent intent = new Intent(activity, AnnouncementActivity.class);
                    view.getContext().startActivity(intent);
                }else if (drawerItem.getIdentifier() == 3 && !(activity instanceof SyllabusActivity)) {
                    // load syllabus screen
                    Intent intent = new Intent(activity, SyllabusActivity.class);
                    view.getContext().startActivity(intent);
                }else if (drawerItem.getIdentifier() == 4 && !(activity instanceof ResourcesActivity)) {
                    // load resources screen
                    Intent intent = new Intent(activity, ResourcesActivity.class);
                    view.getContext().startActivity(intent);
                }else if (drawerItem.getIdentifier() == 5 && !(activity instanceof AssignmentsActivity)) {
                    // load assignments screen
                    Intent intent = new Intent(activity, AssignmentsActivity.class);
                    view.getContext().startActivity(intent);
                }else if(drawerItem.getIdentifier() == 6 && !(activity instanceof GradebookActivity)) {
                    // load gradebook screen
                    Intent intent = new Intent(activity, GradebookActivity.class);
                    view.getContext().startActivity(intent);
                }
                return false;
            }
        }).build();

        result.addStickyFooterItem(new PrimaryDrawerItem().withName("\u00A9 2019 UG Sakai"));

    }




}
