package com.sakai.ug.sakaiapp.models.site;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SitePage {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("layout")
    @Expose
    private int layout;
    @SerializedName("layoutTitle")
    @Expose
    private String layoutTitle;
    @SerializedName("position")
    @Expose
    private int position;
    @SerializedName("props")
    @Expose
    private Object props;
    @SerializedName("reference")
    @Expose
    private String reference;
    @SerializedName("siteId")
    @Expose
    private String siteId;
    @SerializedName("skin")
    @Expose
    private String skin;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("titleCustom")
    @Expose
    private boolean titleCustom;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("activeEdit")
    @Expose
    private boolean activeEdit;
    @SerializedName("homePage")
    @Expose
    private boolean homePage;
    @SerializedName("popUp")
    @Expose
    private boolean popUp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public String getLayoutTitle() {
        return layoutTitle;
    }

    public void setLayoutTitle(String layoutTitle) {
        this.layoutTitle = layoutTitle;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Object getProps() {
        return props;
    }

    public void setProps(Object props) {
        this.props = props;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isTitleCustom() {
        return titleCustom;
    }

    public void setTitleCustom(boolean titleCustom) {
        this.titleCustom = titleCustom;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isActiveEdit() {
        return activeEdit;
    }

    public void setActiveEdit(boolean activeEdit) {
        this.activeEdit = activeEdit;
    }

    public boolean isHomePage() {
        return homePage;
    }

    public void setHomePage(boolean homePage) {
        this.homePage = homePage;
    }

    public boolean isPopUp() {
        return popUp;
    }

    public void setPopUp(boolean popUp) {
        this.popUp = popUp;
    }

}