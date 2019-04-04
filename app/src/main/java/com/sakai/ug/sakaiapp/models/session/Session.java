package com.sakai.ug.sakaiapp.models.session;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Session {



    @SerializedName("attributeNames")
    @Expose
    private AttributeNames attributeNames;
    @SerializedName("attributes")
    @Expose
    private Object attributes;
    @SerializedName("creationTime")
    @Expose
    private double creationTime;
    @SerializedName("currentTime")
    @Expose
    private double currentTime;
    @SerializedName("id")
    @Expose
    private Object id;
    @SerializedName("lastAccessedTime")
    @Expose
    private double lastAccessedTime;
    @SerializedName("maxInactiveInterval")
    @Expose
    private int maxInactiveInterval;
    @SerializedName("userEid")
    @Expose
    private String userEid;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("active")
    @Expose
    private boolean active;
    @SerializedName("entityReference")
    @Expose
    private String entityReference;
    @SerializedName("entityURL")
    @Expose
    private String entityURL;

    public AttributeNames getAttributeNames() {
        return attributeNames;
    }

    public void setAttributeNames(AttributeNames attributeNames) {
        this.attributeNames = attributeNames;
    }

    public Object getAttributes() {
        return attributes;
    }

    public void setAttributes(Object attributes) {
        this.attributes = attributes;
    }

    public double getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(double creationTime) {
        this.creationTime = creationTime;
    }

    public double getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(double currentTime) {
        this.currentTime = currentTime;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public double getLastAccessedTime() {
        return lastAccessedTime;
    }

    public void setLastAccessedTime(double lastAccessedTime) {
        this.lastAccessedTime = lastAccessedTime;
    }

    public int getMaxInactiveInterval() {
        return maxInactiveInterval;
    }

    public void setMaxInactiveInterval(int maxInactiveInterval) {
        this.maxInactiveInterval = maxInactiveInterval;
    }

    public String getUserEid() {
        return userEid;
    }

    public void setUserEid(String userEid) {
        this.userEid = userEid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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