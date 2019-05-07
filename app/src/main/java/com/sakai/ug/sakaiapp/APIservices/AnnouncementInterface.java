package com.sakai.ug.sakaiapp.APIservices;

import com.sakai.ug.sakaiapp.models.announcement.Announcement;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AnnouncementInterface {

    @GET("announcement/site/{id}.json?n=100&d=100")
    Call<Announcement> getSiteAnnouncement(@Path("id") String site_id);

    @GET("announcement/motd.json")
    Call<Announcement> getMOTD();
}
