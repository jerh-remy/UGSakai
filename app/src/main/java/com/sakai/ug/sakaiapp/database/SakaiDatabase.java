/*
 * Copyright (c) 2015-2016 Filippo Engidashet. All Rights Reserved.
 * <p>
 *  Save to the extent permitted by law, you may not use, copy, modify,
 *  distribute or create derivative works of this material or any part
 *  of it without the prior written consent of Filippo Engidashet.
 *  <p>
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 */

package com.sakai.ug.sakaiapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.sakai.ug.sakaiapp.callback.AnnouncementFetchListener;
import com.sakai.ug.sakaiapp.callback.AssignmentFetchListener;
import com.sakai.ug.sakaiapp.callback.CourseSiteFetchListener;
import com.sakai.ug.sakaiapp.callback.ResourceFetchListener;
import com.sakai.ug.sakaiapp.helper.Constants;
import com.sakai.ug.sakaiapp.models.announcement.Announcement;
import com.sakai.ug.sakaiapp.models.announcement.AnnouncementCollection;
import com.sakai.ug.sakaiapp.models.assignment.Assignment;
import com.sakai.ug.sakaiapp.models.assignment.AssignmentCollection;
import com.sakai.ug.sakaiapp.models.assignment.TimeCreated;
import com.sakai.ug.sakaiapp.models.resources.ContentCollection;
import com.sakai.ug.sakaiapp.models.site.Props;
import com.sakai.ug.sakaiapp.models.site.Site;
import com.sakai.ug.sakaiapp.models.site.SiteCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Filippo Engidashet
 * @version 1.0
 * @date today
 */
public class SakaiDatabase extends SQLiteOpenHelper {

    private static final String TAG = SakaiDatabase.class.getSimpleName();

    public SakaiDatabase(Context context) {
        super(context, Constants.DATABASE.DB_NAME, null, Constants.DATABASE.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(Constants.DATABASE.CREATE_ASSIGNMENT_TABLE_QUERY);
            db.execSQL(Constants.DATABASE.CREATE_ANNOUNCEMENT_TABLE_QUERY);
            db.execSQL(Constants.DATABASE.CREATE_SITES_TABLE_QUERY);
            db.execSQL(Constants.DATABASE.CREATE_RESOURCES_TABLE_QUERY);
            db.execSQL(Constants.DATABASE.CREATE_SYLLABUS_TABLE_QUERY);
        } catch (SQLException ex) {
            Log.d(TAG, ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Constants.DATABASE.ASSIGNMENT_DROP_QUERY);
        db.execSQL(Constants.DATABASE.ANNOUNCEMENT_DROP_QUERY);
        db.execSQL(Constants.DATABASE.COURSE_SITE_DROP_QUERY);
        db.execSQL(Constants.DATABASE.SYLLABUS_DROP_QUERY);
        db.execSQL(Constants.DATABASE.RESOURCES_DROP_QUERY);
        this.onCreate(db);
    }


    //COURSE SITES
    public void addCourseSite(SiteCollection siteCollection) {


        Log.d(TAG, "Values Got " + siteCollection.getEntityId());


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.DATABASE.SITE_ID, siteCollection.getEntityId());
        values.put(Constants.DATABASE.DESCRIPTION, siteCollection.getDescription());
        values.put(Constants.DATABASE.ENTITY_TITLE, siteCollection.getEntityTitle());
        values.put(Constants.DATABASE.PROPS_CONTACT_NAME, siteCollection.getProps().getContactName());
        Log.d(TAG, "SakaiDB Course Site: " + values);

        try {
            db.insert(Constants.DATABASE.COURSE_SITE_TABLE_NAME, null, values);
        } catch (Exception e) {

        }
        db.close();
    }

    public void fetchCourseSites(CourseSiteFetchListener listener) {
        CourseSiteFetcher fetcher = new CourseSiteFetcher(listener, this.getWritableDatabase());
        fetcher.start();
    }

    public class CourseSiteFetcher extends Thread {

        private final CourseSiteFetchListener courseSiteFetchListener;
        private final SQLiteDatabase mDb;


        public CourseSiteFetcher(CourseSiteFetchListener listener, SQLiteDatabase db) {
            courseSiteFetchListener = listener;
            mDb = db;
        }

        @Override
        public void run() {
            Cursor cursor = mDb.rawQuery(Constants.DATABASE.GET_COURSE_SITE_QUERY, null);
            //Log.d(TAG, "number of columns in table: " + cursor.getColumnCount());

            List<SiteCollection> siteCollectionList = new ArrayList<>();

            if (cursor.getCount() > 0) {

                if (cursor.moveToFirst()) {
                    do {
                        SiteCollection siteCollection = new SiteCollection();
                        siteCollection.setEntityId(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.SITE_ID)));
                        siteCollection.setDescription(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.DESCRIPTION)));
                        siteCollection.setEntityTitle(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.ENTITY_TITLE)));
                        siteCollection.setProps(new Props(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.PROPS_CONTACT_NAME))));
                        Log.d(TAG, "course site fetched: " + siteCollection);

                        siteCollectionList.add(siteCollection);
                        publishCourseSite(siteCollection);

                    } while (cursor.moveToNext());
                }
            }
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    courseSiteFetchListener.onDeliverAllCourseSites(siteCollectionList);
                    Log.d(TAG, "course site list fetched: " + siteCollectionList);
                    courseSiteFetchListener.onHideDialog();
                }
            });
        }

        public void publishCourseSite(final SiteCollection siteCollection) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    courseSiteFetchListener.onDeliverCourseSite(siteCollection);
                }
            });
        }
    }


    //ASSIGNMENTS
    public void addAssignment(AssignmentCollection assignmentCollection) {


        Log.d(TAG, "Values Got " + assignmentCollection.getEntityId());


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.DATABASE.ASSIGNMENT_ID, assignmentCollection.getEntityId());
        values.put(Constants.DATABASE.INSTRUCTIONS, assignmentCollection.getInstructions());
        values.put(Constants.DATABASE.DUE_DATE, assignmentCollection.getDueTimeString());
        values.put(Constants.DATABASE.STATUS, assignmentCollection.getStatus());
        values.put(Constants.DATABASE.ASS_TITLE, assignmentCollection.getTitle());
        values.put(Constants.DATABASE.ASS_SITE_ID, assignmentCollection.getContext());
        values.put(Constants.DATABASE.TIME_CREATED, assignmentCollection.getTimeCreated().getDisplay());
        Log.d(TAG, "SakaiDB Assignment: " + values);

        try {
            db.insert(Constants.DATABASE.ASSIGNMENT_TABLE_NAME, null, values);
        } catch (Exception e) {

        }
        db.close();
    }

    public void fetchAssignments(AssignmentFetchListener listener, String courseID) {
        AssignmentFetcher fetcher = new AssignmentFetcher(listener, this.getWritableDatabase(), courseID);
        fetcher.start();
    }

    public class AssignmentFetcher extends Thread {

        private final AssignmentFetchListener mListener;
        private final SQLiteDatabase mDb;
        private final String siteID;

        public AssignmentFetcher(AssignmentFetchListener listener, SQLiteDatabase db, String courseID) {
            mListener = listener;
            mDb = db;
            siteID = courseID;
        }

        @Override
        public void run() {
            Cursor cursor = mDb.rawQuery(Constants.DATABASE.GET_ASSIGNMENTS_QUERY, new String[] {siteID});

            List<AssignmentCollection> assignmentCollectionList = new ArrayList<>();

            if (cursor.getCount() > 0) {

                if (cursor.moveToFirst()) {
                    do {
                        AssignmentCollection assignmentCollection = new AssignmentCollection();
                        assignmentCollection.setEntityId(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.ASSIGNMENT_ID)));
                        assignmentCollection.setContext(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.ASS_SITE_ID)));
                        assignmentCollection.setInstructions(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.INSTRUCTIONS)));
                        assignmentCollection.setDueTimeString(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.DUE_DATE)));
                        assignmentCollection.setStatus(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.STATUS)));
                        assignmentCollection.setTitle(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.ASS_TITLE)));
                        assignmentCollection.setTimeCreated(new TimeCreated(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.TIME_CREATED))));
                        Log.d(TAG, "assignment fetched: " + assignmentCollection);

                        assignmentCollectionList.add(assignmentCollection);
                        publishAssignment(assignmentCollection);

                    } while (cursor.moveToNext());
                }
            }
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    mListener.onDeliverAllAssignments(assignmentCollectionList);
                    mListener.onHideDialog();
                }
            });
        }

        public void publishAssignment(final AssignmentCollection assignmentCollection) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    mListener.onDeliverAssignment(assignmentCollection);
                }
            });
        }
    }


    //ANNOUNCEMENTS
    public void addAnnouncement(AnnouncementCollection announcementCollection) {


        Log.d(TAG, "Values Got " + announcementCollection.getEntityId());


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.DATABASE.ANNOUNCEMENT_ID, announcementCollection.getAnnouncementId());
        values.put(Constants.DATABASE.ANN_TITLE, announcementCollection.getTitle());
        values.put(Constants.DATABASE.BODY, announcementCollection.getBody());
        values.put(Constants.DATABASE.CREATED_BY, announcementCollection.getCreatedByDisplayName());
        values.put(Constants.DATABASE.CREATED_ON, announcementCollection.getCreatedOn());
        values.put(Constants.DATABASE.ANN_SITE_ID, announcementCollection.getSiteId());
        Log.d(TAG, "SakaiDB Announcement: " + values);

        try {
            db.insert(Constants.DATABASE.ANNOUNCEMENT_TABLE_NAME, null, values);
        } catch (Exception e) {

        }
        db.close();
    }

    public void fetchAnnouncements(AnnouncementFetchListener listener, String courseID) {
        AnnouncementFetcher fetcher = new AnnouncementFetcher(listener, this.getWritableDatabase(), courseID);
        fetcher.start();
    }

    public class AnnouncementFetcher extends Thread {

        private final AnnouncementFetchListener mListener;
        private final SQLiteDatabase mDb;
        private final String siteID;

        public AnnouncementFetcher(AnnouncementFetchListener listener, SQLiteDatabase db, String courseID) {
            mListener = listener;
            mDb = db;
            siteID = courseID;
        }

        @Override
        public void run() {
            Cursor cursor = mDb.rawQuery(Constants.DATABASE.GET_ANNOUNCEMENTS_QUERY, new String[] {siteID});

            List<AnnouncementCollection> announcementCollectionList = new ArrayList<>();

            if (cursor.getCount() > 0) {

                if (cursor.moveToFirst()) {
                    do {
                        AnnouncementCollection announcementCollection = new AnnouncementCollection();
                        announcementCollection.setAnnouncementId(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.ANNOUNCEMENT_ID)));
                        announcementCollection.setTitle(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.ANN_TITLE)));
                        announcementCollection.setBody(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.BODY)));
                        announcementCollection.setCreatedByDisplayName(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.CREATED_BY)));
                        announcementCollection.setCreatedOn(Double.parseDouble(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.CREATED_ON))));
                        announcementCollection.setSiteId(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.ANN_SITE_ID)));
                        Log.d(TAG, "announcement fetched: " + announcementCollection);


                        announcementCollectionList.add(announcementCollection);
                        publishAnnouncement(announcementCollection);

                    } while (cursor.moveToNext());
                }
            }
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    mListener.onDeliverAllAnnouncements(announcementCollectionList);
                    mListener.onHideDialog();
                }
            });
        }

        public void publishAnnouncement(final AnnouncementCollection announcementCollection) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    mListener.onDeliverAnnouncement(announcementCollection);
                }
            });
        }
    }


    //RESOURCES
    public void addResources(ContentCollection contentCollection) {


        Log.d(TAG, "Values Got " + contentCollection.getTitle());


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.DATABASE.RES_TITLE, contentCollection.getTitle());
        values.put(Constants.DATABASE.TYPE, contentCollection.getType());
        values.put(Constants.DATABASE.NO_OF_CHILDREN, contentCollection.getNumChildren());
        values.put(Constants.DATABASE.RES_SITE_ID, contentCollection.getSiteID());
        Log.d(TAG, "SakaiDB Resource: " + values);

        try {
            db.insert(Constants.DATABASE.RESOURCES_TABLE_NAME, null, values);
        } catch (Exception e) {

        }
        db.close();
    }

    public void fetchResources(ResourceFetchListener listener, String courseID) {
        ResourcesFetcher fetcher = new ResourcesFetcher(listener, this.getWritableDatabase(), courseID);
        fetcher.start();
    }

    public class ResourcesFetcher extends Thread {

        private final ResourceFetchListener mListener;
        private final SQLiteDatabase mDb;
        private final String siteID;

        public ResourcesFetcher(ResourceFetchListener listener, SQLiteDatabase db, String courseID) {
            mListener = listener;
            mDb = db;
            siteID = courseID;
        }

        @Override
        public void run() {
            Cursor cursor = mDb.rawQuery(Constants.DATABASE.GET_RESOURCES_QUERY, new String[] {siteID});

            List<ContentCollection> contentCollectionList = new ArrayList<>();

            if (cursor.getCount() > 0) {

                if (cursor.moveToFirst()) {
                    do {
                        ContentCollection contentCollection = new ContentCollection();
                        contentCollection.setTitle(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.RES_TITLE)));
                        contentCollection.setType(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.TYPE)));
                        contentCollection.setNumChildren(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.NO_OF_CHILDREN))));
                        Log.d(TAG, "resource fetched: " + contentCollection);

                        contentCollectionList.add(contentCollection);
                        publishResource(contentCollection);

                    } while (cursor.moveToNext());
                }
            }
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    mListener.onDeliverAllResources(contentCollectionList);
                    mListener.onHideDialog();
                }
            });
        }

        public void publishResource(final ContentCollection contentCollection) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    mListener.onDeliverResource(contentCollection);
                }
            });
        }
    }


}
