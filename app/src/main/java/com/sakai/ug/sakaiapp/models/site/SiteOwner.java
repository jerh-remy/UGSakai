package com.sakai.ug.sakaiapp.models.site;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SiteOwner {

    @SerializedName("userDisplayName")
    @Expose
    private String userDisplayName;
    @SerializedName("userEntityURL")
    @Expose
    private String userEntityURL;
    @SerializedName("userId")
    @Expose
    private String userId;

    public String getUserDisplayName() {
        return userDisplayName;
    }

    public void setUserDisplayName(String userDisplayName) {
        this.userDisplayName = userDisplayName;
    }

    public String getUserEntityURL() {
        return userEntityURL;
    }

    public void setUserEntityURL(String userEntityURL) {
        this.userEntityURL = userEntityURL;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}