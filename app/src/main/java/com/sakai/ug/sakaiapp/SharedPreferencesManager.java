package com.sakai.ug.sakaiapp;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {

    private static SharedPreferencesManager mInstance;
    private static Context mCtx;


    private static final String SHARED_PREF_NAME = "sakaisharedpref";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String DISPLAYNAME = "fullname";
    private static final String EMAIL = "email";
    private static final String IMAGEURL = "image";
    private static final String NOT_FIRST = "no";
    private static final String USERID = "user_id";
    private static final String MOTD = "motd";
    private static final String PERSONAL_SUMMARY = "psummary";
    private static final String PHONE_NO = "phone_no";
    private static final String LINKEDIN = "linkedin";
    private static final String FACEBOOK = "facebook";
    private static final String TWITTER = "twitter";


    private SharedPreferencesManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPreferencesManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPreferencesManager(context);
        }
        return mInstance;
    }

    public boolean UserLogin(String username, String password, String notfirst) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERNAME, username);
        editor.putString(PASSWORD, password);
        editor.putString(NOT_FIRST, notfirst);
        editor.apply();

        return true;
    }


    public boolean UserSession(String userID) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERID, userID);
        editor.apply();

        return true;
    }

    public boolean MOTD(String motd) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(MOTD, motd);
        editor.apply();

        return true;
    }

    public boolean UserProfileDetails(String displayName, String email, String imageUrl, String personalSummary, String phone_number, String linkedin, String facebook, String twitter) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(DISPLAYNAME, displayName);
        editor.putString(EMAIL, email);
        editor.putString(IMAGEURL, imageUrl);
        editor.putString(PERSONAL_SUMMARY, personalSummary);
        editor.putString(PHONE_NO, phone_number);
        editor.putString(LINKEDIN, linkedin);
        editor.putString(FACEBOOK, facebook);
        editor.putString(TWITTER, twitter);
        editor.apply();

        return true;
    }

    public boolean isLoggedIn() {

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if (sharedPreferences.getString(USERNAME, null) != null) {
            return true;
        }
        return false;
    }

    public boolean Logout() {

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.clear();
        editor.putString(NOT_FIRST, "yes");
        editor.apply();
        return true;
    }

    public String getUsername() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getString(USERNAME, null);
    }

    public String getPassword() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getString(PASSWORD, null);
    }

    public String getNotFirst() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getString(NOT_FIRST, null);
    }

    public String getFullname() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getString(DISPLAYNAME, null);
    }

    public String getUSERID() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getString(USERID, null);
    }

    public String getEmail() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getString(EMAIL, null);
    }

    public String getImageurl() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getString(IMAGEURL, null);
    }

    public String getMOTD() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getString(MOTD, null);
    }

    public String getPersonalSummary() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getString(PERSONAL_SUMMARY, null);
    }


    public String getPhoneNo() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getString(PHONE_NO, null);
    }


    public String getLinkedin() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getString(LINKEDIN, null);
    }


    public String getFacebook() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getString(FACEBOOK, null);
    }

    public String getTwitter() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getString(TWITTER, null);
    }


}
