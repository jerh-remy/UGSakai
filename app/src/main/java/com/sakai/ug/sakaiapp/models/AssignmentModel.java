package com.sakai.ug.sakaiapp.models;

public class AssignmentModel {

    private String Title;
    private String Time;
    private int image;
    private int image2;

    public AssignmentModel(String title, String time, int image, int image2) {
        Title = title;
        Time = time;
        this.image = image;
        this.image2 = image2;
    }

    public String getTitle() {
        return Title;
    }

    public String getTime() {
        return Time;
    }

    public int getImage() {
        return image;
    }

    public int getImage2() {
        return image2;
    }
}
