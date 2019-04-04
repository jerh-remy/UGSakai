package com.sakai.ug.sakaiapp.models.site;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SiteCollection {

    @SerializedName("contactEmail")
    @Expose
    private String contactEmail;
    @SerializedName("contactName")
    @Expose
    private String contactName;
    @SerializedName("createdDate")
    @Expose
    private String createdDate;
    @SerializedName("createdTime")
    @Expose
    private CreatedTime createdTime;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("htmlDescription")
    @Expose
    private String htmlDescription;
    @SerializedName("htmlShortDescription")
    @Expose
    private String htmlShortDescription;
    @SerializedName("iconUrl")
    @Expose
    private String iconUrl;
    @SerializedName("iconUrlFull")
    @Expose
    private String iconUrlFull;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("infoUrl")
    @Expose
    private String infoUrl;
    @SerializedName("infoUrlFull")
    @Expose
    private String infoUrlFull;
    @SerializedName("joinerRole")
    @Expose
    private String joinerRole;
    @SerializedName("lastModified")
    @Expose
    private double lastModified;
    @SerializedName("maintainRole")
    @Expose
    private String maintainRole;
    @SerializedName("modifiedDate")
    @Expose
    private double modifiedDate;
    @SerializedName("modifiedTime")
    @Expose
    private ModifiedTime modifiedTime;
    @SerializedName("owner")
    @Expose
    private String owner;
    @SerializedName("props")
    @Expose
    private Props props;
    @SerializedName("providerGroupId")
    @Expose
    private String providerGroupId;
    @SerializedName("reference")
    @Expose
    private String reference;
    @SerializedName("shortDescription")
    @Expose
    private String shortDescription;
    @SerializedName("siteGroups")
    @Expose
    private String siteGroups;
    @SerializedName("siteOwner")
    @Expose
    private SiteOwner siteOwner;
    @SerializedName("sitePages")
    @Expose
    private List<SitePage> sitePages = null;
    @SerializedName("skin")
    @Expose
    private String skin;
    @SerializedName("softlyDeletedDate")
    @Expose
    private String softlyDeletedDate;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("userRoles")
    @Expose
    private List<String> userRoles = null;
    @SerializedName("activeEdit")
    @Expose
    private boolean activeEdit;
    @SerializedName("customPageOrdered")
    @Expose
    private boolean customPageOrdered;
    @SerializedName("empty")
    @Expose
    private boolean empty;
    @SerializedName("joinable")
    @Expose
    private boolean joinable;
    @SerializedName("pubView")
    @Expose
    private boolean pubView;
    @SerializedName("published")
    @Expose
    private boolean published;
    @SerializedName("softlyDeleted")
    @Expose
    private boolean softlyDeleted;
    @SerializedName("entityReference")
    @Expose
    private String entityReference;
    @SerializedName("entityURL")
    @Expose
    private String entityURL;
    @SerializedName("entityId")
    @Expose
    private String entityId;
    @SerializedName("entityTitle")
    @Expose
    private String entityTitle;

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public CreatedTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(CreatedTime createdTime) {
        this.createdTime = createdTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHtmlDescription() {
        return htmlDescription;
    }

    public void setHtmlDescription(String htmlDescription) {
        this.htmlDescription = htmlDescription;
    }

    public String getHtmlShortDescription() {
        return htmlShortDescription;
    }

    public void setHtmlShortDescription(String htmlShortDescription) {
        this.htmlShortDescription = htmlShortDescription;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getIconUrlFull() {
        return iconUrlFull;
    }

    public void setIconUrlFull(String iconUrlFull) {
        this.iconUrlFull = iconUrlFull;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInfoUrl() {
        return infoUrl;
    }

    public void setInfoUrl(String infoUrl) {
        this.infoUrl = infoUrl;
    }

    public String getInfoUrlFull() {
        return infoUrlFull;
    }

    public void setInfoUrlFull(String infoUrlFull) {
        this.infoUrlFull = infoUrlFull;
    }

    public String getJoinerRole() {
        return joinerRole;
    }

    public void setJoinerRole(String joinerRole) {
        this.joinerRole = joinerRole;
    }

    public double getLastModified() {
        return lastModified;
    }

    public void setLastModified(double lastModified) {
        this.lastModified = lastModified;
    }

    public String getMaintainRole() {
        return maintainRole;
    }

    public void setMaintainRole(String maintainRole) {
        this.maintainRole = maintainRole;
    }

    public double getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(double modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public ModifiedTime getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(ModifiedTime modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Props getProps() {
        return props;
    }

    public void setProps(Props props) {
        this.props = props;
    }

    public String getProviderGroupId() {
        return providerGroupId;
    }

    public void setProviderGroupId(String providerGroupId) {
        this.providerGroupId = providerGroupId;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getSiteGroups() {
        return siteGroups;
    }

    public void setSiteGroups(String siteGroups) {
        this.siteGroups = siteGroups;
    }

    public SiteOwner getSiteOwner() {
        return siteOwner;
    }

    public void setSiteOwner(SiteOwner siteOwner) {
        this.siteOwner = siteOwner;
    }

    public List<SitePage> getSitePages() {
        return sitePages;
    }

    public void setSitePages(List<SitePage> sitePages) {
        this.sitePages = sitePages;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public String getSoftlyDeletedDate() {
        return softlyDeletedDate;
    }

    public void setSoftlyDeletedDate(String softlyDeletedDate) {
        this.softlyDeletedDate = softlyDeletedDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<String> userRoles) {
        this.userRoles = userRoles;
    }

    public boolean isActiveEdit() {
        return activeEdit;
    }

    public void setActiveEdit(boolean activeEdit) {
        this.activeEdit = activeEdit;
    }

    public boolean isCustomPageOrdered() {
        return customPageOrdered;
    }

    public void setCustomPageOrdered(boolean customPageOrdered) {
        this.customPageOrdered = customPageOrdered;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public boolean isJoinable() {
        return joinable;
    }

    public void setJoinable(boolean joinable) {
        this.joinable = joinable;
    }

    public boolean isPubView() {
        return pubView;
    }

    public void setPubView(boolean pubView) {
        this.pubView = pubView;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isSoftlyDeleted() {
        return softlyDeleted;
    }

    public void setSoftlyDeleted(boolean softlyDeleted) {
        this.softlyDeleted = softlyDeleted;
    }

    public String getEntityReference() {
        return entityReference;
    }

    public void setEntityReference(String entityReference) {
        this.entityReference = entityReference;
    }

    public String getEntityURL() {
        return entityURL;
    }

    public void setEntityURL(String entityURL) {
        this.entityURL = entityURL;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getEntityTitle() {
        return entityTitle;
    }

    public void setEntityTitle(String entityTitle) {
        this.entityTitle = entityTitle;
    }

}