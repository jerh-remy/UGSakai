package com.sakai.ug.sakaiapp.models.gradebook;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Gradebook {

    @SerializedName("assignments")
    @Expose
    private List<Assignment> assignments = null;
    @SerializedName("siteId")
    @Expose
    private String siteId;
    @SerializedName("siteName")
    @Expose
    private String siteName;
    @SerializedName("entityReference")
    @Expose
    private String entityReference;
    @SerializedName("entityURL")
    @Expose
    private String entityURL;
    @SerializedName("entityTitle")
    @Expose
    private String entityTitle;

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getEntityReference() {
        return entityReference;
    }

    public void setEntityReference(String entityReference) {
        this.entityReference = entityReference;
    }

    public String getEntityURL() {
        return entityURL;
    }

    public void setEntityURL(String entityURL) {
        this.entityURL = entityURL;
    }

    public String getEntityTitle() {
        return entityTitle;
    }

    public void setEntityTitle(String entityTitle) {
        this.entityTitle = entityTitle;
    }

}