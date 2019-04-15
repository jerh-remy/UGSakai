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

    /*public static final class HTTP {
        public static final String BASE_URL = "http://services.hanselandpetal.com";
    }*/

    public static final class DATABASE {

        public static final String DB_NAME = "sakaidatabase";
        public static final int DB_VERSION = 7;

        //tables
        public static final String COURSE_SITE_TABLE_NAME = "courseSite";
        public static final String ASSIGNMENT_TABLE_NAME = "assignment";
        public static final String ANNOUNCEMENT_TABLE_NAME = "announcement";

        //drop queries
        public static final String ASSIGNMENT_DROP_QUERY = "DROP TABLE IF EXISTS " + ASSIGNMENT_TABLE_NAME;
        public static final String COURSE_SITE_DROP_QUERY = "DROP TABLE IF EXISTS " + COURSE_SITE_TABLE_NAME;
        public static final String ANNOUNCEMENT_DROP_QUERY = "DROP TABLE IF EXISTS " + ANNOUNCEMENT_TABLE_NAME;

        //get queries
        public static final String GET_ASSIGNMENTS_QUERY = "SELECT * FROM " + ASSIGNMENT_TABLE_NAME;
        public static final String GET_ANNOUNCEMENTS_QUERY = "SELECT * FROM " + ANNOUNCEMENT_TABLE_NAME;
        public static final String GET_COURSE_SITE_QUERY = "SELECT * FROM " + COURSE_SITE_TABLE_NAME;

        //course site table columns
        public static final String SITE_ID = "siteId";
        public static final String DESCRIPTION = "description";
        public static final String ENTITY_TITLE = "entityTitle";
        public static final String PROPS_CONTACT_NAME = "propsContactName";

        //assignment table columns
        public static final String ASSIGNMENT_ID = "assignmentId";
        public static final String ASS_TITLE = "title";
        public static final String DUE_DATE = "due_date";
        public static final String STATUS = "status";
        public static final String INSTRUCTIONS = "instructions";
        public static final String TIME_CREATED = "time_created";
        public static final String ASS_SITE_ID = "site_id";

        //announcement table columns
        public static final String ANNOUNCEMENT_ID = "announcement";
        public static final String ANN_TITLE = "title";
        public static final String BODY = "body";
        public static final String CREATED_BY = "created_by";
        public static final String CREATED_ON = "created_on";
        public static final String ANN_SITE_ID = "site_id";


        //create table queries
        public static final String CREATE_ASSIGNMENT_TABLE_QUERY = "CREATE TABLE " + ASSIGNMENT_TABLE_NAME + "" +
                "(" + ASSIGNMENT_ID + " TEXT PRIMARY KEY not null," +
                ASS_TITLE + " TEXT not null," +
                DUE_DATE + " TEXT not null," +
                STATUS + " TEXT not null," +
                ASS_SITE_ID + " TEXT not null," +
                INSTRUCTIONS + " TEXT not null," +
                TIME_CREATED + " TEXT not null)";


        public static final String CREATE_ANNOUNCEMENT_TABLE_QUERY = "CREATE TABLE " + ANNOUNCEMENT_TABLE_NAME + "" +
                "(" + ANNOUNCEMENT_ID + " TEXT PRIMARY KEY not null," +
                ANN_TITLE + " TEXT not null," +
                BODY + " TEXT not null," +
                CREATED_BY + " TEXT not null," +
                CREATED_ON + " TEXT not null," +
                ANN_SITE_ID + " TEXT not null)";


        public static final String CREATE_SITES_TABLE_QUERY = "CREATE TABLE " + COURSE_SITE_TABLE_NAME + "" +
                "(" + SITE_ID + " TEXT PRIMARY KEY not null," +
                DESCRIPTION + " TEXT not null," +
                ENTITY_TITLE + " TEXT not null," +
                PROPS_CONTACT_NAME + " TEXT not null)";
    }





   /* public static final class REFERENCE {
        public static final String FLOWER = Config.PACKAGE_NAME + "flower";
    }

    public static final class Config {
        public static final String PACKAGE_NAME = "org.dalol.retrofit2_restapidemo.";
    }*/


}
