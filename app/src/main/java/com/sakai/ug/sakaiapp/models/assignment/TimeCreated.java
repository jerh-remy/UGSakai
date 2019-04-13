package com.sakai.ug.sakaiapp.models.assignment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TimeCreated {

    @SerializedName("display")
    @Expose
    private String display;
    @SerializedName("time")
    @Expose
    private double time;

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public TimeCreated(String display) {
        this.display = display;
    }
}
