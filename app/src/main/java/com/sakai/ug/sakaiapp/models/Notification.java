package com.sakai.ug.sakaiapp.models;

public class Notification {
    private String course_code;
    private String shortdesc;
    private String time;
    private String title;
    private int image;

    public Notification(String course_code, String shortdesc, String time, String title, int image) {
        this.course_code = course_code;
        this.shortdesc = shortdesc;
        this.time = time;
        this.title = title;
        this.image = image;
    }

    public String getCourse_code() {
        return course_code;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public void setShortdesc(String shortdesc) {
        this.shortdesc = shortdesc;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
