package com.sakai.ug.sakaiapp.models;

public class GradebookModel {

    private String Item;
    private String Grade;
    private int image;

    public GradebookModel(String item, String grade, int image) {
        Item = item;
        Grade = grade;
        this.image = image;
    }

    public String getItem() {
        return Item;
    }

    public String getGrade() {
        return Grade;
    }

    public int getImage() {
        return image;
    }
}

