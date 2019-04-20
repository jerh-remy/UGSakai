package com.sakai.ug.sakaiapp.models.resources;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContentCollection {

    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("authorId")
    @Expose
    private String authorId;
    @SerializedName("container")
    @Expose
    private String container;
    @SerializedName("copyrightAlert")
    @Expose
    private Object copyrightAlert;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("endDate")
    @Expose
    private Object endDate;
    @SerializedName("fromDate")
    @Expose
    private Object fromDate;
    @SerializedName("modifiedDate")
    @Expose
    private String modifiedDate;
    @SerializedName("numChildren")
    @Expose
    private int numChildren;
    @SerializedName("quota")
    @Expose
    private Object quota;
    @SerializedName("size")
    @Expose
    private int size;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("usage")
    @Expose
    private Object usage;
    @SerializedName("hidden")
    @Expose
    private boolean hidden;
    @SerializedName("visible")
    @Expose
    private boolean visible;
    @SerializedName("entityReference")
    @Expose
    private String entityReference;
    @SerializedName("entityURL")
    @Expose
    private String entityURL;
    @SerializedName("entityTitle")
    @Expose
    private String entityTitle;

    private String siteID;

    public String getSiteID() {
        return siteID;
    }

    public void setSiteID(String siteID) {
        this.siteID = siteID;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public Object getCopyrightAlert() {
        return copyrightAlert;
    }

    public void setCopyrightAlert(Object copyrightAlert) {
        this.copyrightAlert = copyrightAlert;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Object getEndDate() {
        return endDate;
    }

    public void setEndDate(Object endDate) {
        this.endDate = endDate;
    }

    public Object getFromDate() {
        return fromDate;
    }

    public void setFromDate(Object fromDate) {
        this.fromDate = fromDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public int getNumChildren() {
        return numChildren;
    }

    public void setNumChildren(int numChildren) {
        this.numChildren = numChildren;
    }

    public Object getQuota() {
        return quota;
    }

    public void setQuota(Object quota) {
        this.quota = quota;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getUsage() {
        return usage;
    }

    public void setUsage(Object usage) {
        this.usage = usage;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
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
