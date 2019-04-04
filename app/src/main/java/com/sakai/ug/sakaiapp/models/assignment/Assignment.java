package com.sakai.ug.sakaiapp.models.assignment;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Assignment {

    @SerializedName("entityPrefix")
    @Expose
    private String entityPrefix;
    @SerializedName("assignment_collection")
    @Expose
    private List<AssignmentCollection> assignmentCollection = null;

    public String getEntityPrefix() {
        return entityPrefix;
    }

    public void setEntityPrefix(String entityPrefix) {
        this.entityPrefix = entityPrefix;
    }

    public List<AssignmentCollection> getAssignmentCollection() {
        return assignmentCollection;
    }

    public void setAssignmentCollection(List<AssignmentCollection> assignmentCollection) {
        this.assignmentCollection = assignmentCollection;
    }


}

