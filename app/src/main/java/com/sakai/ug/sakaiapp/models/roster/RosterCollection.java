package com.sakai.ug.sakaiapp.models.roster;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RosterCollection {

    @SerializedName("displayName")
    @Expose
    private String displayName;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("entityReference")
    @Expose
    private String entityReference;
    @SerializedName("entityURL")
    @Expose
    private String entityURL;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

}