package com.sakai.ug.sakaiapp.APIservices;
import com.sakai.ug.sakaiapp.models.site.Site;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SitesInterface {
    @GET("direct/site.json")
    Call<Site> getSites();


}
