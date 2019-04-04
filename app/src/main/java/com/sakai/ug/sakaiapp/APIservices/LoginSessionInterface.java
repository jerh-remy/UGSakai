package com.sakai.ug.sakaiapp.APIservices;

import com.sakai.ug.sakaiapp.models.announcement.Announcement;
import com.sakai.ug.sakaiapp.models.assignment.Assignment;
import com.sakai.ug.sakaiapp.models.profile.Profile;
import com.sakai.ug.sakaiapp.models.session.Session;

import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LoginSessionInterface {

    @POST("session")
    Call<String> login(@Query("_username") String username, @Query("_password") String password);

    @GET("session/current.json")
    Call<Session> getSessionDetails();

   /* @GET("players")
    Call<AnnouncementModel> getAllPlayers();*/
}
