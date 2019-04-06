package com.sakai.ug.sakaiapp.APIservices;

import com.sakai.ug.sakaiapp.models.resources.Resources;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ResourcesInterface {

    @GET("content/site/{site_id}.json")
    Call<Resources> getSiteResources(@Path("site_id") String siteID);
}