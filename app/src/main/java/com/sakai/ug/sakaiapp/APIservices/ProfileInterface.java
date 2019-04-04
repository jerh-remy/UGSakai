package com.sakai.ug.sakaiapp.APIservices;

import com.sakai.ug.sakaiapp.models.profile.Profile;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProfileInterface {

    @GET("profile/{userID}.json")
    Call<Profile> getUserProfileDetails(@Path("userID") String userID);
}
