package com.sakai.ug.sakaiapp.models.assignment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AssignmentCollection {

    @SerializedName("access")
    @Expose
    private Access access;
    @SerializedName("allPurposeItemText")
    @Expose
    private Object allPurposeItemText;
    @SerializedName("attachments")
    @Expose
    private List<Object> attachments = null;
    @SerializedName("authorLastModified")
    @Expose
    private String authorLastModified;
    @SerializedName("authors")
    @Expose
    private List<Object> authors = null;
    @SerializedName("closeTime")
    @Expose
    private CloseTime closeTime;
    @SerializedName("closeTimeString")
    @Expose
    private String closeTimeString;
    @SerializedName("content")
    @Expose
    private Object content;
    @SerializedName("contentReference")
    @Expose
    private String contentReference;
    @SerializedName("context")
    @Expose
    private String context;
    @SerializedName("creator")
    @Expose
    private String creator;
    @SerializedName("dropDeadTime")
    @Expose
    private DropDeadTime dropDeadTime;
    @SerializedName("dropDeadTimeString")
    @Expose
    private String dropDeadTimeString;
    @SerializedName("dueTime")
    @Expose
    private DueTime dueTime;
    @SerializedName("dueTimeString")
    @Expose
    private String dueTimeString;
    @SerializedName("gradeScale")
    @Expose
    private String gradeScale;
    @SerializedName("gradeScaleMaxPoints")
    @Expose
    private Object gradeScaleMaxPoints;
    @SerializedName("gradebookItemId")
    @Expose
    private Object gradebookItemId;
    @SerializedName("gradebookItemName")
    @Expose
    private Object gradebookItemName;
    @SerializedName("groups")
    @Expose
    private List<Object> groups = null;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("instructions")
    @Expose
    private String instructions;
    @SerializedName("modelAnswerText")
    @Expose
    private Object modelAnswerText;
    @SerializedName("openTime")
    @Expose
    private OpenTime openTime;
    @SerializedName("openTimeString")
    @Expose
    private String openTimeString;
    @SerializedName("position_order")
    @Expose
    private int positionOrder;
    @SerializedName("privateNoteText")
    @Expose
    private Object privateNoteText;
    @SerializedName("section")
    @Expose
    private String section;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("submissionType")
    @Expose
    private String submissionType;
    @SerializedName("timeCreated")
    @Expose
    private TimeCreated timeCreated;
    @SerializedName("timeLastModified")
    @Expose
    private TimeLastModified timeLastModified;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("allowResubmission")
    @Expose
    private boolean allowResubmission;
    @SerializedName("draft")
    @Expose
    private boolean draft;
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

    public Access getAccess() {
        return access;
    }

    public void setAccess(Access access) {
        this.access = access;
    }

    public Object getAllPurposeItemText() {
        return allPurposeItemText;
    }

    public void setAllPurposeItemText(Object allPurposeItemText) {
        this.allPurposeItemText = allPurposeItemText;
    }

    public List<Object> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Object> attachments) {
        this.attachments = attachments;
    }

    public String getAuthorLastModified() {
        return authorLastModified;
    }

    public void setAuthorLastModified(String authorLastModified) {
        this.authorLastModified = authorLastModified;
    }

    public List<Object> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Object> authors) {
        this.authors = authors;
    }

    public CloseTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(CloseTime closeTime) {
        this.closeTime = closeTime;
    }

    public String getCloseTimeString() {
        return closeTimeString;
    }

    public void setCloseTimeString(String closeTimeString) {
        this.closeTimeString = closeTimeString;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public String getContentReference() {
        return contentReference;
    }

    public void setContentReference(String contentReference) {
        this.contentReference = contentReference;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public DropDeadTime getDropDeadTime() {
        return dropDeadTime;
    }

    public void setDropDeadTime(DropDeadTime dropDeadTime) {
        this.dropDeadTime = dropDeadTime;
    }

    public String getDropDeadTimeString() {
        return dropDeadTimeString;
    }

    public void setDropDeadTimeString(String dropDeadTimeString) {
        this.dropDeadTimeString = dropDeadTimeString;
    }

    public DueTime getDueTime() {
        return dueTime;
    }

    public void setDueTime(DueTime dueTime) {
        this.dueTime = dueTime;
    }

    public String getDueTimeString() {
        return dueTimeString;
    }

    public void setDueTimeString(String dueTimeString) {
        this.dueTimeString = dueTimeString;
    }

    public String getGradeScale() {
        return gradeScale;
    }

    public void setGradeScale(String gradeScale) {
        this.gradeScale = gradeScale;
    }

    public Object getGradeScaleMaxPoints() {
        return gradeScaleMaxPoints;
    }

    public void setGradeScaleMaxPoints(Object gradeScaleMaxPoints) {
        this.gradeScaleMaxPoints = gradeScaleMaxPoints;
    }

    public Object getGradebookItemId() {
        return gradebookItemId;
    }

    public void setGradebookItemId(Object gradebookItemId) {
        this.gradebookItemId = gradebookItemId;
    }

    public Object getGradebookItemName() {
        return gradebookItemName;
    }

    public void setGradebookItemName(Object gradebookItemName) {
        this.gradebookItemName = gradebookItemName;
    }

    public List<Object> getGroups() {
        return groups;
    }

    public void setGroups(List<Object> groups) {
        this.groups = groups;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Object getModelAnswerText() {
        return modelAnswerText;
    }

    public void setModelAnswerText(Object modelAnswerText) {
        this.modelAnswerText = modelAnswerText;
    }

    public OpenTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(OpenTime openTime) {
        this.openTime = openTime;
    }

    public String getOpenTimeString() {
        return openTimeString;
    }

    public void setOpenTimeString(String openTimeString) {
        this.openTimeString = openTimeString;
    }

    public int getPositionOrder() {
        return positionOrder;
    }

    public void setPositionOrder(int positionOrder) {
        this.positionOrder = positionOrder;
    }

    public Object getPrivateNoteText() {
        return privateNoteText;
    }

    public void setPrivateNoteText(Object privateNoteText) {
        this.privateNoteText = privateNoteText;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubmissionType() {
        return submissionType;
    }

    public void setSubmissionType(String submissionType) {
        this.submissionType = submissionType;
    }

    public TimeCreated getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(TimeCreated timeCreated) {
        this.timeCreated = timeCreated;
    }

    public TimeLastModified getTimeLastModified() {
        return timeLastModified;
    }

    public void setTimeLastModified(TimeLastModified timeLastModified) {
        this.timeLastModified = timeLastModified;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isAllowResubmission() {
        return allowResubmission;
    }

    public void setAllowResubmission(boolean allowResubmission) {
        this.allowResubmission = allowResubmission;
    }

    public boolean isDraft() {
        return draft;
    }

    public void setDraft(boolean draft) {
        this.draft = draft;
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
