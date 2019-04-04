package com.sakai.ug.sakaiapp.APIservices;

import com.sakai.ug.sakaiapp.models.syllabus.Syllabus;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SyllabusInterface {

    @GET("syllabus/site/{id}.json")
    public Call<Syllabus> getSiteSyllabus(@Path("id") String site_id);
}
