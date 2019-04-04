package com.sakai.ug.sakaiapp.models.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SocialInfo {

    @SerializedName("facebookUrl")
    @Expose
    private Object facebookUrl;
    @SerializedName("linkedinUrl")
    @Expose
    private Object linkedinUrl;
    @SerializedName("myspaceUrl")
    @Expose
    private Object myspaceUrl;
    @SerializedName("skypeUsername")
    @Expose
    private Object skypeUsername;
    @SerializedName("twitterUrl")
    @Expose
    private Object twitterUrl;
    @SerializedName("userUuid")
    @Expose
    private String userUuid;

    public Object getFacebookUrl() {
        return facebookUrl;
    }

    public void setFacebookUrl(Object facebookUrl) {
        this.facebookUrl = facebookUrl;
    }

    public Object getLinkedinUrl() {
        return linkedinUrl;
    }

    public void setLinkedinUrl(Object linkedinUrl) {
        this.linkedinUrl = linkedinUrl;
    }

    public Object getMyspaceUrl() {
        return myspaceUrl;
    }

    public void setMyspaceUrl(Object myspaceUrl) {
        this.myspaceUrl = myspaceUrl;
    }

    public Object getSkypeUsername() {
        return skypeUsername;
    }

    public void setSkypeUsername(Object skypeUsername) {
        this.skypeUsername = skypeUsername;
    }

    public Object getTwitterUrl() {
        return twitterUrl;
    }

    public void setTwitterUrl(Object twitterUrl) {
        this.twitterUrl = twitterUrl;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

}