package com.sakai.ug.sakaiapp.APIservices;

import com.sakai.ug.sakaiapp.models.announcement.Announcement;
import com.sakai.ug.sakaiapp.models.assignment.Assignment;

import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("session")
    Call<String> login(@Query("_username") String username, @Query("_password") String password);

    @GET("assignment/site/d6dbdb40-bafb-46be-83b4-5054b9be0438.json")
    Call<Assignment> getSiteAssignment();

    @GET("announcement/site/d6dbdb40-bafb-46be-83b4-5054b9be0438.json?n=100&d=100")
    Call<Announcement> getSiteAnnouncement();

   /* @GET("players")
    Call<AnnouncementModel> getAllPlayers();*/
}
