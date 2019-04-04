package com.sakai.ug.sakaiapp.models.syllabus;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("attachments")
    @Expose
    private List<Attachment> attachments = null;
    @SerializedName("data")
    @Expose
    private String data;
    @SerializedName("endDate")
    @Expose
    private int endDate;
    @SerializedName("order")
    @Expose
    private int order;
    @SerializedName("startDate")
    @Expose
    private int startDate;
    @SerializedName("title")
    @Expose
    private String title;

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getEndDate() {
        return endDate;
    }

    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getStartDate() {
        return startDate;
    }

    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}