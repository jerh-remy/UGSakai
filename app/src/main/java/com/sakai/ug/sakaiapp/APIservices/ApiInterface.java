package com.sakai.ug.sakaiapp.APIservices;

import com.sakai.ug.sakaiapp.models.AnnouncementModel;

import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("session")
    Call<String> login(@Query("_username") String username, @Query("_password") String password);

    @GET("players")
    Call<AnnouncementModel> getAllPlayers();
}
