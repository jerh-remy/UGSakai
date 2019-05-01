package com.sakai.ug.sakaiapp.APIservices;

import com.sakai.ug.sakaiapp.models.gradebook.Gradebook;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GradebookInterface {
    @GET("gradebook/site/{id}.json")
    Call<Gradebook> getGrades(@Path("id") String site_id);
}
