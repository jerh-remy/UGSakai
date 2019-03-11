package com.sakai.ug.sakaiapp.models;

public class ResourcesModel {

    private String name;
    private int image;

    public ResourcesModel(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }


}

