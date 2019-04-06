package com.sakai.ug.sakaiapp.models.resources;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Resources {

    @SerializedName("entityPrefix")
    @Expose
    private String entityPrefix;
    @SerializedName("content_collection")
    @Expose
    private List<ContentCollection> contentCollection = null;

    public String getEntityPrefix() {
        return entityPrefix;
    }

    public void setEntityPrefix(String entityPrefix) {
        this.entityPrefix = entityPrefix;
    }

    public List<ContentCollection> getContentCollection() {
        return contentCollection;
    }

    public void setContentCollection(List<ContentCollection> contentCollection) {
        this.contentCollection = contentCollection;
    }

}