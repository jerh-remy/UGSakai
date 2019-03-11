package com.sakai.ug.sakaiapp.models;

public class AnnouncementModel {

    private String course_code;
    private String shortdesc;
    private int image;

    public String getCourse_code() {
        return course_code;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public int getImage() {
        return image;
    }

    public AnnouncementModel(String course_code, String shortdesc, int image) {
        this.course_code = course_code;
        this.shortdesc = shortdesc;
        this.image = image;
    }

}
