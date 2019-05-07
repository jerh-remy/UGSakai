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

package com.sakai.ug.sakaiapp.helper;

/**
 * @author Filippo Engidashet
 * @version 1.0
 * @date today
 */
public class Constants {

    public static final class DATABASE {

        public static final String DB_NAME = "sakaidatabase";
        public static final int DB_VERSION = 13;

        //tables
        public static final String COURSE_SITE_TABLE_NAME = "courseSite";
        public static final String ASSIGNMENT_TABLE_NAME = "assignment";
        public static final String ANNOUNCEMENT_TABLE_NAME = "announcement";
        public static final String SYLLABUS_TABLE_NAME = "syllabus";
        public static final String RESOURCES_TABLE_NAME = "resources";
        public static final String GRADEBOOK_TABLE_NAME = "gradebook";


        //course site table columns
        public static final String SITE_ID = "siteId";
        public static final String DESCRIPTION = "description";
        public static final String ENTITY_TITLE = "entityTitle";
        public static final String PROPS_CONTACT_NAME = "creatorName";

        //assignment table columns
        public static final String ASSIGNMENT_ID = "assignmentId";
        public static final String ASS_TITLE = "title";
        public static final String DUE_DATE = "due_date";
        public static final String STATUS = "status";
        public static final String INSTRUCTIONS = "instructions";
        public static final String TIME_CREATED = "time_created";
        public static final String ASS_SITE_ID = "site_id";

        //announcement table columns
        public static final String ANNOUNCEMENT_ID = "announcementId";
        public static final String ANN_TITLE = "title";
        public static final String BODY = "body";
        public static final String CREATED_BY = "created_by";
        public static final String CREATED_ON = "created_on";
        public static final String ANN_SITE_ID = "site_id";

        //syllabus table columns
        public static final String TITLE = "title";
        public static final String DATA = "data";
        public static final String ATTACHMENT_TITLE = "attachment_title";
        public static final String SYL_SITE_ID = "siteId";

        //resources table columns
        public static final String RES_TITLE = "title";
        public static final String TYPE = "type";
        public static final String NO_OF_CHILDREN = "no_of_children";
        public static final String RES_SITE_ID = "siteId";

        //gradebook table columns
        public static final String GRADEBOOK_ITEM_NAME = "itemName";
        public static final String GRADEBOOK_POINTS = "points";
        public static final String GRADEBOOK_GRADE = "grade";
        public static final String GRD_SITE_ID = "siteId";


        //drop queries
        public static final String ASSIGNMENT_DROP_QUERY = "DROP TABLE IF EXISTS " + ASSIGNMENT_TABLE_NAME;
        public static final String COURSE_SITE_DROP_QUERY = "DROP TABLE IF EXISTS " + COURSE_SITE_TABLE_NAME;
        public static final String ANNOUNCEMENT_DROP_QUERY = "DROP TABLE IF EXISTS " + ANNOUNCEMENT_TABLE_NAME;
        public static final String SYLLABUS_DROP_QUERY = "DROP TABLE IF EXISTS " + SYLLABUS_TABLE_NAME;
        public static final String RESOURCES_DROP_QUERY = "DROP TABLE IF EXISTS " + RESOURCES_TABLE_NAME;
        public static final String GRADEBOOK_DROP_QUERY = "DROP TABLE IF EXISTS " + GRADEBOOK_TABLE_NAME;

        //get queries
        public static final String GET_ASSIGNMENTS_QUERY = "SELECT * FROM " + ASSIGNMENT_TABLE_NAME + " WHERE " + ASS_SITE_ID + " = ?";
        public static final String GET_ANNOUNCEMENTS_QUERY = "SELECT * FROM " + ANNOUNCEMENT_TABLE_NAME + " WHERE " + ANN_SITE_ID + " = ?";
        public static final String GET_COURSE_SITE_QUERY = "SELECT * FROM " + COURSE_SITE_TABLE_NAME;
        public static final String GET_SYLLABUS_QUERY = "SELECT * FROM " + SYLLABUS_TABLE_NAME + " WHERE " + SYL_SITE_ID + " = ?";
        public static final String GET_RESOURCES_QUERY = "SELECT * FROM " + RESOURCES_TABLE_NAME + " WHERE " + RES_SITE_ID + " = ?";
        public static final String GET_GRADES_QUERY = "SELECT * FROM " + GRADEBOOK_TABLE_NAME + " WHERE " + GRD_SITE_ID + " = ?";


        //get recent announcements query
        public static final String GET_RECENT_ANNOUNCEMENTS_QUERY = "SELECT * FROM announcement LEFT JOIN courseSite ON announcement.site_id = courseSite.siteId ORDER BY created_on DESC LIMIT 5";



        //create table queries
        public static final String CREATE_ASSIGNMENT_TABLE_QUERY = "CREATE TABLE " + ASSIGNMENT_TABLE_NAME + "" +
                "(" + ASSIGNMENT_ID + " TEXT PRIMARY KEY not null," +
                ASS_TITLE + " TEXT not null," +
                DUE_DATE + " TEXT not null," +
                STATUS + " TEXT not null," +
                ASS_SITE_ID + " TEXT not null," +
                INSTRUCTIONS + " TEXT not null," +
                TIME_CREATED + " TEXT not null," +
                "FOREIGN KEY (" + ASS_SITE_ID + ") REFERENCES " + COURSE_SITE_TABLE_NAME + "(" + SITE_ID + "))";


        public static final String CREATE_ANNOUNCEMENT_TABLE_QUERY = "CREATE TABLE " + ANNOUNCEMENT_TABLE_NAME + "" +
                "(" + ANNOUNCEMENT_ID + " TEXT PRIMARY KEY not null," +
                ANN_TITLE + " TEXT not null," +
                BODY + " TEXT not null," +
                CREATED_BY + " TEXT not null," +
                CREATED_ON + " TEXT not null," +
                ANN_SITE_ID + " TEXT not null," +
                "FOREIGN KEY (" + ANN_SITE_ID + ") REFERENCES " + COURSE_SITE_TABLE_NAME + "(" + SITE_ID + "))";


        public static final String CREATE_SITES_TABLE_QUERY = "CREATE TABLE " + COURSE_SITE_TABLE_NAME + "" +
                "(" + SITE_ID + " TEXT PRIMARY KEY not null," +
                DESCRIPTION + " TEXT not null," +
                ENTITY_TITLE + " TEXT not null," +
                PROPS_CONTACT_NAME + " TEXT not null)";

        public static final String CREATE_SYLLABUS_TABLE_QUERY = "CREATE TABLE " + SYLLABUS_TABLE_NAME + "" +
                "(" + TITLE + " TEXT PRIMARY KEY not null," +
                DATA + " TEXT null," +
                ATTACHMENT_TITLE + " TEXT null," +
                SYL_SITE_ID + " TEXT not null," +
                "FOREIGN KEY (" + SYL_SITE_ID + ") REFERENCES " + COURSE_SITE_TABLE_NAME + "(" + SITE_ID + "))";

        public static final String CREATE_RESOURCES_TABLE_QUERY = "CREATE TABLE " + RESOURCES_TABLE_NAME + "" +
                "(" + RES_TITLE + " TEXT PRIMARY KEY not null," +
                TYPE + " TEXT not null," +
                NO_OF_CHILDREN + " TEXT not null," +
                RES_SITE_ID + " TEXT not null," +
                "FOREIGN KEY (" + RES_SITE_ID + ") REFERENCES " + COURSE_SITE_TABLE_NAME + "(" + SITE_ID + "))";

        public static final String CREATE_GRADEBOOK_TABLE_QUERY = "CREATE TABLE " + GRADEBOOK_TABLE_NAME + "" +
                "(" + GRADEBOOK_ITEM_NAME + " TEXT not null," +
                GRADEBOOK_POINTS + " TEXT not null," +
                GRADEBOOK_GRADE + " TEXT not null," +
                GRD_SITE_ID + " TEXT not null," +
                "PRIMARY KEY (" + GRADEBOOK_ITEM_NAME + "," + GRD_SITE_ID + "), " +
                "FOREIGN KEY (" + GRD_SITE_ID + ") REFERENCES " + COURSE_SITE_TABLE_NAME + "(" + SITE_ID + "))";


    }


}
