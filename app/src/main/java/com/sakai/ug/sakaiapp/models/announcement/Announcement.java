package com.sakai.ug.sakaiapp.models.announcement;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Announcement {

    @SerializedName("entityPrefix")
    @Expose
    private String entityPrefix;
    @SerializedName("announcement_collection")
    @Expose
    private List<AnnouncementCollection> announcementCollection = null;

    public String getEntityPrefix() {
        return entityPrefix;
    }

    public void setEntityPrefix(String entityPrefix) {
        this.entityPrefix = entityPrefix;
    }

    public List<AnnouncementCollection> getAnnouncementCollection() {
        return announcementCollection;
    }

    public void setAnnouncementCollection(List<AnnouncementCollection> announcementCollection) {
        this.announcementCollection = announcementCollection;
    }


}
