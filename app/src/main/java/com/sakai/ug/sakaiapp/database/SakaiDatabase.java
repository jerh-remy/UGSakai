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
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.sakai.ug.sakaiapp.Question;
import com.sakai.ug.sakaiapp.callback.AnnouncementFetchListener;
import com.sakai.ug.sakaiapp.callback.AssignmentFetchListener;
import com.sakai.ug.sakaiapp.callback.CourseSiteFetchListener;
import com.sakai.ug.sakaiapp.callback.GradebookFetchListener;
import com.sakai.ug.sakaiapp.callback.QueryCallback;
import com.sakai.ug.sakaiapp.callback.RecentAnnouncementFetchListener;
import com.sakai.ug.sakaiapp.callback.ResourceFetchListener;
import com.sakai.ug.sakaiapp.callback.SyllabusFetchListener;
import com.sakai.ug.sakaiapp.helper.Constants;
import com.sakai.ug.sakaiapp.models.announcement.AnnouncementCollection;
import com.sakai.ug.sakaiapp.models.assignment.AssignmentCollection;
import com.sakai.ug.sakaiapp.models.assignment.TimeCreated;
import com.sakai.ug.sakaiapp.models.gradebook.Assignment;
import com.sakai.ug.sakaiapp.models.resources.ContentCollection;
import com.sakai.ug.sakaiapp.models.site.Props;
import com.sakai.ug.sakaiapp.models.site.SiteCollection;
import com.sakai.ug.sakaiapp.models.syllabus.Attachment;
import com.sakai.ug.sakaiapp.models.syllabus.Item;

import java.util.ArrayList;
import java.util.List;

import static com.sakai.ug.sakaiapp.helper.Constants.DATABASE.CLE_SITE_ID;
import static com.sakai.ug.sakaiapp.helper.Constants.DATABASE.CLE_TABLE_NAME;
import static com.sakai.ug.sakaiapp.helper.Constants.DATABASE.KEY_OPTA;
import static com.sakai.ug.sakaiapp.helper.Constants.DATABASE.KEY_OPTB;
import static com.sakai.ug.sakaiapp.helper.Constants.DATABASE.KEY_OPTC;
import static com.sakai.ug.sakaiapp.helper.Constants.DATABASE.KEY_OPTD;
import static com.sakai.ug.sakaiapp.helper.Constants.DATABASE.KEY_OPTE;
import static com.sakai.ug.sakaiapp.helper.Constants.DATABASE.KEY_QUES;
import static com.sakai.ug.sakaiapp.helper.Constants.DATABASE.KEY_RESPONSE;

/**
 * @author Filippo Engidashet
 * @version 1.0
 * @date today
 */
public class SakaiDatabase extends SQLiteOpenHelper {

    private static final String TAG = SakaiDatabase.class.getSimpleName();
    private SQLiteDatabase dbase;

    public SakaiDatabase(Context context) {
        super(context, Constants.DATABASE.DB_NAME, null, Constants.DATABASE.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            dbase = db;
            db.execSQL(Constants.DATABASE.CREATE_ASSIGNMENT_TABLE_QUERY);
            db.execSQL(Constants.DATABASE.CREATE_ANNOUNCEMENT_TABLE_QUERY);
            db.execSQL(Constants.DATABASE.CREATE_SITES_TABLE_QUERY);
            db.execSQL(Constants.DATABASE.CREATE_RESOURCES_TABLE_QUERY);
            db.execSQL(Constants.DATABASE.CREATE_SYLLABUS_TABLE_QUERY);
            db.execSQL(Constants.DATABASE.CREATE_GRADEBOOK_TABLE_QUERY);
            db.execSQL(Constants.DATABASE.CREATE_QUESTIONS_TABLE_QUERY);
            addQuestions();

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
        db.execSQL(Constants.DATABASE.GRADEBOOK_DROP_QUERY);
        db.execSQL(Constants.DATABASE.QUESTIONS_DROP_QUERY);
        this.onCreate(db);
    }


    public boolean isAnnouncementTableEmpty() {
        SQLiteDatabase db = this.getWritableDatabase();
        String count = "SELECT count(*) FROM " + Constants.DATABASE.ANNOUNCEMENT_TABLE_NAME;
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        if (icount > 0) {
            return false;
        } else {
            return true;
        }
    }


    public void removeAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constants.DATABASE.ANNOUNCEMENT_TABLE_NAME, null, null);
        db.delete(Constants.DATABASE.ASSIGNMENT_TABLE_NAME, null, null);
        db.delete(Constants.DATABASE.RESOURCES_TABLE_NAME, null, null);
        db.delete(Constants.DATABASE.GRADEBOOK_TABLE_NAME, null, null);
        db.delete(Constants.DATABASE.COURSE_SITE_TABLE_NAME, null, null);
        db.delete(Constants.DATABASE.SYLLABUS_TABLE_NAME, null, null);
        db.delete(Constants.DATABASE.CLE_TABLE_NAME, null, null);
        db.close();
    }


    //course lecturer evaluation
    private void addQuestions() {
        Question q1 = new Question("A detailed course syllabus was provided at the beginning of this course", "Strongly Agree", "Agree ", "Moderately agree", "Disagree", "Strongly Disagree");
        addQuestion(q1);
        Question q2 = new Question("The objectives/goals and learning outcomes of the course were clear to me", "Strongly Agree", "Agree ", "Moderately agree", "Disagree", "Strongly Disagree");
        addQuestion(q2);
        Question q3 = new Question("In my opinion, the workload required for the course was adequate for the allocated credit hours ", "Strongly Agree", "Agree ", "Moderately agree", "Disagree", "Strongly Disagree");
        addQuestion(q3);
        Question q4 = new Question("Taking the course was a positive learning experience for me", "Strongly Agree", "Agree ", "Moderately agree", "Disagree", "Strongly Disagree");
        addQuestion(q4);
        Question q5 = new Question("Recommended textbooks and other reference lists were provided", "Strongly Agree", "Agree ", "Moderately agree", "Disagree", "Strongly Disagree");
        addQuestion(q5);
        Question q6 = new Question("The procedure by which student will be assessed were explained", "Strongly Agree", "Agree ", "Moderately agree", "Disagree", "Strongly Disagree");
        addQuestion(q6);
        Question q7 = new Question("The lecturer was firm but was fair to and treated students with respect", "Strongly Agree", "Agree ", "Moderately agree", "Disagree", "Strongly Disagree");
        addQuestion(q7);
        Question q8 = new Question("Useful feedback and comments on the assignment and Interim Assessments (IAs) were provided either in class or during tutorials", "Strongly Agree", "Agree ", "Moderately agree", "Disagree", "Strongly Disagree");
        addQuestion(q8);
        Question q9 = new Question("The lecturer was available during his/her stated hours to be consulted by the students ", "Strongly Agree", "Agree ", "Moderately agree", "Disagree", "Strongly Disagree");
        addQuestion(q9);
        Question q10 = new Question("The lecturer was approachable", "Strongly Agree", "Agree ", "Moderately agree", "Disagree", "Strongly Disagree");
        addQuestion(q10);

    }


    // Adding new question
    public void addQuestion(Question quest) {
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, quest.getQUESTION());
        values.put(KEY_OPTA, quest.getOPTA());
        values.put(KEY_OPTB, quest.getOPTB());
        values.put(KEY_OPTC, quest.getOPTC());
        values.put(KEY_OPTD, quest.getOPTD());
        values.put(KEY_OPTE, quest.getOPTE());
        Log.d("question values", "addQuestion: " + values);
        // Inserting Row
        dbase.insert(CLE_TABLE_NAME, null, values);
    }


    //Adding responses
    public void addResponse(String response, String questionID, String siteID) {
        SQLiteDatabase dbase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_RESPONSE, response);
        values.put(CLE_SITE_ID, siteID);

        Log.d("question responses", "addResponse: " + values);
        // Inserting Row
        dbase.update(CLE_TABLE_NAME, values, "id = ?", new String[]{questionID});
    }

    public List<Question> getAllQuestions() {
        SQLiteDatabase dbase = this.getReadableDatabase();
        List<Question> quesList = new ArrayList<Question>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + CLE_TABLE_NAME;
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        Log.d(TAG, "getAllQuestions: " + cursor.getCount());
        // looping through all rows and adding to list
        if (cursor.getCount() > 0) {
            try {
                if (cursor.moveToFirst()) {
                    do {
                        Question quest = new Question();
                        quest.setID(cursor.getInt(0));
                        quest.setQUESTION(cursor.getString(1));
                        quest.setOPTA(cursor.getString(3));
                        quest.setOPTB(cursor.getString(4));
                        quest.setOPTC(cursor.getString(5));
                        quest.setOPTD(cursor.getString(6));
                        quest.setOPTE(cursor.getString(7));
                        quesList.add(quest);
                    } while (cursor.moveToNext());
                }
            } catch (Exception e) {
                e.getMessage();
                Log.d(TAG, "getAllQuestions: " + cursor.getCount());

            }
        }

        // return quest list
        return quesList;
    }

    public int rowcount() {
        int row = 0;
        String selectQuery = "SELECT  * FROM " + CLE_TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        row = cursor.getCount();
        return row;
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
            db.replace(Constants.DATABASE.COURSE_SITE_TABLE_NAME, null, values);
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


    // FOLDERS
    public void fetchFolder(String title, String siteID, QueryCallback<ContentCollection> listener) {
        listener.onInit();
        FolderFetcher fetcher = new FolderFetcher(title, siteID, listener, this.getWritableDatabase());
        fetcher.start();
    }

    public class FolderFetcher extends Thread {

        private final QueryCallback<ContentCollection> callback;
        private final SQLiteDatabase mDb;
        private final String folderName;
        private final String siteid;


        public FolderFetcher(String folderName, String siteID, QueryCallback<ContentCollection> callback, SQLiteDatabase db) {
            this.callback = callback;
            this.mDb = db;
            this.folderName = folderName;
            this.siteid = siteID;
        }

        @Override
        public void run() {
            Cursor cursor = mDb.rawQuery(Constants.DATABASE.GET_FOLDER_QUERY, new String[]{folderName, siteid});
            //Log.d(TAG, "number of columns in table: " + cursor.getColumnCount());

            List<ContentCollection> folders = new ArrayList<>(0);

            Log.d(TAG, "folder count: " + cursor.getCount());

            if (cursor.getCount() > 0) {

                if (cursor.moveToFirst()) {
                    do {
                        ContentCollection collection = new ContentCollection();
                        collection.setContainer(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.CONTAINER)));
                        collection.setTitle(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.RES_TITLE)));
                        collection.setSiteID(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.RES_SITE_ID)));
                        Log.d(TAG, "folder fetched: " + collection);

                        folders.add(collection);
                        Log.d(TAG, "folders " + folders);

                    } while (cursor.moveToNext());
                }
            } else {
                callback.onError("Could not load folders");
            }
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (folders.isEmpty()) {
                        Log.d(TAG, "folder fetched: " + folders);
                        callback.onSuccess(null);
                    } else {
                        callback.onSuccess(folders.get(0));
                    }
                }
            });
        }
    }


    // FILES
    public class FilesFetcher extends Thread {

        private final QueryCallback<List<ContentCollection>> callback;
        private final SQLiteDatabase mDb;
        private final String siteID;


        public FilesFetcher(QueryCallback<List<ContentCollection>> callback, SQLiteDatabase db, String courseID) { //, String site_id
            this.callback = callback;
            this.mDb = db;
            this.siteID = courseID;
        }

        @Override
        public void run() {
            Cursor cursor = mDb.rawQuery(Constants.DATABASE.GET_RESOURCES_QUERY, new String[]{siteID}); //new String[]{siteID}
            //Log.d(TAG, "number of columns in table: " + cursor.getColumnCount());

            List<ContentCollection> files = new ArrayList<>(0);

            if (cursor.getCount() > 0) {

                if (cursor.moveToFirst()) {
                    do {
                        ContentCollection collection = new ContentCollection();
                        collection.setContainer(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.CONTAINER)));
                        collection.setUrl(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.URL)));
                        collection.setTitle(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.RES_TITLE)));
                        collection.setType(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.TYPE)));
                        Log.d(TAG, "file fetched: " + collection.getTitle());

                        files.add(collection);

                    } while (cursor.moveToNext());
                }
            } else {
                callback.onError("Could not load files");
            }
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    callback.onSuccess(files);
                    Log.d(TAG, "files list fetched: " + files);
                }
            });
        }
    }

    public void fetchFilesFromFolder(QueryCallback<List<ContentCollection>> callback, String courseID) {
        callback.onInit();
        new FilesFetcher(callback, this.getWritableDatabase(), courseID).start();
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
            db.replace(Constants.DATABASE.ASSIGNMENT_TABLE_NAME, null, values);
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
            Cursor cursor = mDb.rawQuery(Constants.DATABASE.GET_ASSIGNMENTS_QUERY, new String[]{siteID});

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
        values.put(Constants.DATABASE.CREATED_ON, announcementCollection.getCreatedOnString());
        values.put(Constants.DATABASE.ANN_SITE_ID, announcementCollection.getSiteId());
        Log.d(TAG, "SakaiDB Announcement: " + values);
        Log.d(TAG, "Date value: " + announcementCollection.getCreatedOnString());

        try {
            db.insert(Constants.DATABASE.ANNOUNCEMENT_TABLE_NAME, null, values);
        } catch (Exception e) {
            //db.replace(Constants.DATABASE.ANNOUNCEMENT_TABLE_NAME, null, values);
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
            Cursor cursor = mDb.rawQuery(Constants.DATABASE.GET_ANNOUNCEMENTS_QUERY, new String[]{siteID});

            List<AnnouncementCollection> announcementCollectionList = new ArrayList<>();

            if (cursor.getCount() > 0) {

                if (cursor.moveToFirst()) {
                    do {
                        AnnouncementCollection announcementCollection = new AnnouncementCollection();
                        announcementCollection.setAnnouncementId(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.ANNOUNCEMENT_ID)));
                        announcementCollection.setTitle(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.ANN_TITLE)));
                        announcementCollection.setBody(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.BODY)));
                        announcementCollection.setCreatedByDisplayName(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.CREATED_BY)));
                        announcementCollection.setCreatedOnString(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.CREATED_ON)));
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
        values.put(Constants.DATABASE.CONTAINER, contentCollection.getContainer());
        values.put(Constants.DATABASE.URL, contentCollection.getUrl());
        values.put(Constants.DATABASE.NO_OF_CHILDREN, contentCollection.getNumChildren());
        values.put(Constants.DATABASE.RES_SITE_ID, contentCollection.getSiteID());
        Log.d(TAG, "SakaiDB Resource: " + values);

        try {
            db.insert(Constants.DATABASE.RESOURCES_TABLE_NAME, null, values);
        } catch (Exception e) {
            db.replace(Constants.DATABASE.RESOURCES_TABLE_NAME, null, values);
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
            Cursor cursor = mDb.rawQuery(Constants.DATABASE.GET_RESOURCES_QUERY, new String[]{siteID});

            List<ContentCollection> contentCollectionList = new ArrayList<>();

            if (cursor.getCount() > 0) {

                if (cursor.moveToFirst()) {
                    do {
                        ContentCollection contentCollection = new ContentCollection();
                        contentCollection.setTitle(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.RES_TITLE)));
                        contentCollection.setType(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.TYPE)));
                        contentCollection.setContainer(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.CONTAINER)));
                        contentCollection.setUrl(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.URL)));
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

    //SYLLABUS
    public void addSyllabus(Item syllabusItem) {


        Log.d(TAG, "Values Got " + syllabusItem.getTitle());


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.DATABASE.TITLE, syllabusItem.getTitle());
        values.put(Constants.DATABASE.DATA, syllabusItem.getData());
        if (syllabusItem.getAttachments().size() != 0) {
            values.put(Constants.DATABASE.ATTACHMENT_TITLE, syllabusItem.getAttachments().get(0).getTitle());
        } else {
            values.put(Constants.DATABASE.ATTACHMENT_TITLE, "");
        }
        values.put(Constants.DATABASE.SYL_SITE_ID, syllabusItem.getSiteID());
        Log.d(TAG, "SakaiDB syllabus: " + values);

        try {
            db.insert(Constants.DATABASE.SYLLABUS_TABLE_NAME, null, values);
        } catch (Exception e) {
            //
            db.replace(Constants.DATABASE.SYLLABUS_TABLE_NAME, null, values);
        }
        db.close();
    }

    public void fetchSyllabus(SyllabusFetchListener listener, String courseID) {
        SyllabusFetcher fetcher = new SyllabusFetcher(listener, this.getWritableDatabase(), courseID);
        fetcher.start();
    }

    public class SyllabusFetcher extends Thread {

        private final SyllabusFetchListener mListener;
        private final SQLiteDatabase mDb;
        private final String siteID;

        public SyllabusFetcher(SyllabusFetchListener listener, SQLiteDatabase db, String courseID) {
            mListener = listener;
            mDb = db;
            siteID = courseID;
        }

        @Override
        public void run() {
            Cursor cursor = mDb.rawQuery(Constants.DATABASE.GET_SYLLABUS_QUERY, new String[]{siteID});

            List<Item> syllabusItemList = new ArrayList<>();
            List<Attachment> attachmentList = new ArrayList<>();

            if (cursor.getCount() > 0) {

                if (cursor.moveToFirst()) {
                    do {
                        Item syllabusItem = new Item();
                        syllabusItem.setTitle(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.TITLE)));
                        syllabusItem.setData(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.DATA)));
                        Attachment attachment = new Attachment();
                        attachment.setTitle(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.ATTACHMENT_TITLE)));
                        attachmentList.add(attachment);
                        syllabusItem.setAttachments(attachmentList);

                        Log.d(TAG, "syllabus fetched: " + syllabusItem);

                        syllabusItemList.add(syllabusItem);
                        publishSyllabus(syllabusItem);

                    } while (cursor.moveToNext());
                }
            }
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    mListener.onDeliverAllSyllabus(syllabusItemList);
                    mListener.onHideDialog();
                }
            });
        }

        private void publishSyllabus(final Item syllabusItem) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    mListener.onDeliverSyllabus(syllabusItem);
                }
            });
        }

    }


    //GRADEBOOK
    public void addGrade(Assignment assignment) {


        Log.d(TAG, "Values Got " + assignment.getItemName());


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.DATABASE.GRADEBOOK_ITEM_NAME, assignment.getItemName());
        values.put(Constants.DATABASE.GRADEBOOK_POINTS, assignment.getPoints());
        values.put(Constants.DATABASE.GRADEBOOK_GRADE, assignment.getGrade());
        values.put(Constants.DATABASE.GRD_SITE_ID, assignment.getSiteID());
        Log.d(TAG, "SakaiDB gradebook: " + values);

        try {
            db.insert(Constants.DATABASE.GRADEBOOK_TABLE_NAME, null, values);
        } catch (Exception e) {
            db.replace(Constants.DATABASE.GRADEBOOK_TABLE_NAME, null, values);
        }
        db.close();
    }

    public void fetchGrades(GradebookFetchListener listener, String courseID) {
        GradebookFetcher fetcher = new GradebookFetcher(listener, this.getWritableDatabase(), courseID);
        fetcher.start();
    }

    public class GradebookFetcher extends Thread {

        private final GradebookFetchListener mListener;
        private final SQLiteDatabase mDb;
        private final String siteID;

        public GradebookFetcher(GradebookFetchListener listener, SQLiteDatabase db, String courseID) {
            mListener = listener;
            mDb = db;
            siteID = courseID;
        }

        @Override
        public void run() {
            Cursor cursor = mDb.rawQuery(Constants.DATABASE.GET_GRADES_QUERY, new String[]{siteID});

            List<Assignment> assignmentList = new ArrayList<>();

            if (cursor.getCount() > 0) {

                if (cursor.moveToFirst()) {
                    do {
                        Assignment assignment = new Assignment();
                        assignment.setItemName(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.GRADEBOOK_ITEM_NAME)));
                        assignment.setGrade(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.GRADEBOOK_GRADE)));
                        assignment.setPoints(cursor.getInt(cursor.getColumnIndex(Constants.DATABASE.GRADEBOOK_POINTS)));
                        assignment.setSiteID(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.GRD_SITE_ID)));

                        Log.d(TAG, "grade fetched: " + assignment);

                        assignmentList.add(assignment);
                        publishGrade(assignment);

                    } while (cursor.moveToNext());
                }
            }
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    mListener.onDeliverAllGrades(assignmentList);
                    mListener.onHideDialog();
                }
            });
        }

        private void publishGrade(final Assignment assignment) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    mListener.onDeliverGrade(assignment);
                }
            });
        }

    }


    //RECENT ANNOUNCEMENTS
    public void fetchRecentAnnouncements(RecentAnnouncementFetchListener listener) {
        RecentAnnouncementFetcher fetcher = new RecentAnnouncementFetcher(listener, this.getWritableDatabase());
        fetcher.start();
    }

    public class RecentAnnouncementFetcher extends Thread {

        private final RecentAnnouncementFetchListener mListener;
        private final SQLiteDatabase mDb;

        public RecentAnnouncementFetcher(RecentAnnouncementFetchListener listener, SQLiteDatabase db) {
            mListener = listener;
            mDb = db;
        }

        @Override
        public void run() {
            Cursor cursor = mDb.rawQuery(Constants.DATABASE.GET_RECENT_ANNOUNCEMENTS_QUERY, null);

            List<AnnouncementCollection> announcementCollectionList = new ArrayList<>();

            if (cursor.getCount() > 0) {

                if (cursor.moveToFirst()) {
                    do {
                        AnnouncementCollection announcementCollection = new AnnouncementCollection();
                        announcementCollection.setAnnouncementId(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.ANNOUNCEMENT_ID)));
                        announcementCollection.setCourseSiteName(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.ENTITY_TITLE)));
                        announcementCollection.setTitle(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.ANN_TITLE)));
                        announcementCollection.setBody(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.BODY)));
                        announcementCollection.setCreatedByDisplayName(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.CREATED_BY)));
                        announcementCollection.setCreatedOnString(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.CREATED_ON)));
                        announcementCollection.setSiteId(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.ANN_SITE_ID)));
                        Log.d(TAG, "recent announcement fetched: " + announcementCollection);


                        announcementCollectionList.add(announcementCollection);
                        publishAnnouncement(announcementCollection);

                    } while (cursor.moveToNext());
                }
            }
        }

        public void publishAnnouncement(final AnnouncementCollection announcementCollection) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    mListener.onDeliverRecentAnnouncement(announcementCollection);
                }
            });
        }
    }


}
