package com.sakai.ug.sakaiapp.APIservices;

import com.sakai.ug.sakaiapp.models.assignment.Assignment;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AssignmentInterface {
    @GET("assignment/site/{id}.json")
    Call<Assignment> getSiteAssignment(@Path("id") String site_id);
}
