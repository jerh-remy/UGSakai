package com.sakai.ug.sakaiapp.models;

public class CourseSiteModel {
    private String courseSiteTitle;
    private int image;
    private String courseLecturer;

    public CourseSiteModel(String courseSiteTitle, String courseLecturer, int image) {
        this.courseSiteTitle = courseSiteTitle;
        this.courseLecturer = courseLecturer;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getCourseLecturer() {
        return courseLecturer;
    }

    public void setCourseLecturer(String courseLecturer) {
        this.courseLecturer = courseLecturer;
    }

    public String getCourseSiteTitle() {
        return courseSiteTitle;
    }

    public void setCourseSiteTitle(String courseSiteTitle) {
        this.courseSiteTitle = courseSiteTitle;
    }
}
