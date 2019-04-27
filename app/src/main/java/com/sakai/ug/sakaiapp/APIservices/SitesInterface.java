package com.sakai.ug.sakaiapp.APIservices;
import com.sakai.ug.sakaiapp.models.roster.Roster;
import com.sakai.ug.sakaiapp.models.site.Site;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SitesInterface {
    @GET("direct/site.json")
    Call<Site> getSites();

    @GET("direct/roster/site/{site_id}.json")
    Call<Roster> getRosterSize(@Path("site_id")String site_id);

}
