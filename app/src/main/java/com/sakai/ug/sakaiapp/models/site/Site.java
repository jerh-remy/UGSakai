package com.sakai.ug.sakaiapp.models.site;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Site {

    @SerializedName("entityPrefix")
    @Expose
    private String entityPrefix;
    @SerializedName("site_collection")
    @Expose
    private List<SiteCollection> siteCollection = null;

    public String getEntityPrefix() {
        return entityPrefix;
    }

    public void setEntityPrefix(String entityPrefix) {
        this.entityPrefix = entityPrefix;
    }

    public List<SiteCollection> getSiteCollection() {
        return siteCollection;
    }

    public void setSiteCollection(List<SiteCollection> siteCollection) {
        this.siteCollection = siteCollection;
    }

}