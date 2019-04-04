package com.sakai.ug.sakaiapp.models.site;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModifiedTime {

    @SerializedName("display")
    @Expose
    private String display;
    @SerializedName("time")
    @Expose
    private String time;

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}