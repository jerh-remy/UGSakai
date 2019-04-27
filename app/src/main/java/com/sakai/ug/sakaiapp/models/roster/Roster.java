package com.sakai.ug.sakaiapp.models.roster;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Roster {

    @SerializedName("entityPrefix")
    @Expose
    private String entityPrefix;
    @SerializedName("roster_collection")
    @Expose
    private List<RosterCollection> rosterCollection = null;

    public String getEntityPrefix() {
        return entityPrefix;
    }

    public void setEntityPrefix(String entityPrefix) {
        this.entityPrefix = entityPrefix;
    }

    public List<RosterCollection> getRosterCollection() {
        return rosterCollection;
    }

    public void setRosterCollection(List<RosterCollection> rosterCollection) {
        this.rosterCollection = rosterCollection;
    }

}