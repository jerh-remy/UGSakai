package com.sakai.ug.sakaiapp.APIservices;

import com.sakai.ug.sakaiapp.models.roster.Roster;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RosterInterface {

    @GET("roster/site/{site_id}.json")
    Call<Roster> getRoster(@Path("site_id") String siteID);
}
