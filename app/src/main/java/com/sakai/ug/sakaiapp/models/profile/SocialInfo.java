package com.sakai.ug.sakaiapp.models.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SocialInfo {

    @SerializedName("facebookUrl")
    @Expose
    private String facebookUrl;
    @SerializedName("linkedinUrl")
    @Expose
    private String linkedinUrl;
    @SerializedName("myspaceUrl")
    @Expose
    private String myspaceUrl;
    @SerializedName("skypeUsername")
    @Expose
    private String skypeUsername;
    @SerializedName("twitterUrl")
    @Expose
    private String twitterUrl;
    @SerializedName("userUuid")
    @Expose
    private String userUuid;

    public String getFacebookUrl() {
        return facebookUrl;
    }

    public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
    }

    public String getLinkedinUrl() {
        return linkedinUrl;
    }

    public void setLinkedinUrl(String linkedinUrl) {
        this.linkedinUrl = linkedinUrl;
    }

    public String getMyspaceUrl() {
        return myspaceUrl;
    }

    public void setMyspaceUrl(String myspaceUrl) {
        this.myspaceUrl = myspaceUrl;
    }

    public String getSkypeUsername() {
        return skypeUsername;
    }

    public void setSkypeUsername(String skypeUsername) {
        this.skypeUsername = skypeUsername;
    }

    public String getTwitterUrl() {
        return twitterUrl;
    }

    public void setTwitterUrl(String twitterUrl) {
        this.twitterUrl = twitterUrl;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

}