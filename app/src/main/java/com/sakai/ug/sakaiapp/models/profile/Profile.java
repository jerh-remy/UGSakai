package com.sakai.ug.sakaiapp.models.profile;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile {

    @SerializedName("academicProfileUrl")
    @Expose
    private Object academicProfileUrl;
    @SerializedName("birthday")
    @Expose
    private Object birthday;
    @SerializedName("birthdayDisplay")
    @Expose
    private Object birthdayDisplay;
    @SerializedName("businessBiography")
    @Expose
    private Object businessBiography;
    @SerializedName("companyProfiles")
    @Expose
    private List<Object> companyProfiles = null;
    @SerializedName("course")
    @Expose
    private Object course;
    @SerializedName("dateOfBirth")
    @Expose
    private Object dateOfBirth;
    @SerializedName("department")
    @Expose
    private Object department;
    @SerializedName("displayName")
    @Expose
    private String displayName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("facsimile")
    @Expose
    private Object facsimile;
    @SerializedName("favouriteBooks")
    @Expose
    private Object favouriteBooks;
    @SerializedName("favouriteMovies")
    @Expose
    private Object favouriteMovies;
    @SerializedName("favouriteQuotes")
    @Expose
    private Object favouriteQuotes;
    @SerializedName("favouriteTvShows")
    @Expose
    private Object favouriteTvShows;
    @SerializedName("homepage")
    @Expose
    private Object homepage;
    @SerializedName("homephone")
    @Expose
    private Object homephone;
    @SerializedName("imageThumbUrl")
    @Expose
    private String imageThumbUrl;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("mobilephone")
    @Expose
    private String mobilephone;
    @SerializedName("nickname")
    @Expose
    private Object nickname;
    @SerializedName("personalSummary")
    @Expose
    private String personalSummary;
    @SerializedName("position")
    @Expose
    private Object position;
    @SerializedName("props")
    @Expose
    private Object props;
    @SerializedName("publications")
    @Expose
    private Object publications;
    @SerializedName("room")
    @Expose
    private Object room;
    @SerializedName("school")
    @Expose
    private Object school;
    @SerializedName("socialInfo")
    @Expose
    private SocialInfo socialInfo;
    @SerializedName("staffProfile")
    @Expose
    private Object staffProfile;
    @SerializedName("status")
    @Expose
    private Object status;
    @SerializedName("subjects")
    @Expose
    private Object subjects;
    @SerializedName("universityProfileUrl")
    @Expose
    private Object universityProfileUrl;
    @SerializedName("userUuid")
    @Expose
    private String userUuid;
    @SerializedName("workphone")
    @Expose
    private Object workphone;
    @SerializedName("locked")
    @Expose
    private boolean locked;
    @SerializedName("entityReference")
    @Expose
    private String entityReference;
    @SerializedName("entityURL")
    @Expose
    private String entityURL;
    @SerializedName("entityId")
    @Expose
    private String entityId;

    public Object getAcademicProfileUrl() {
        return academicProfileUrl;
    }

    public void setAcademicProfileUrl(Object academicProfileUrl) {
        this.academicProfileUrl = academicProfileUrl;
    }

    public Object getBirthday() {
        return birthday;
    }

    public void setBirthday(Object birthday) {
        this.birthday = birthday;
    }

    public Object getBirthdayDisplay() {
        return birthdayDisplay;
    }

    public void setBirthdayDisplay(Object birthdayDisplay) {
        this.birthdayDisplay = birthdayDisplay;
    }

    public Object getBusinessBiography() {
        return businessBiography;
    }

    public void setBusinessBiography(Object businessBiography) {
        this.businessBiography = businessBiography;
    }

    public List<Object> getCompanyProfiles() {
        return companyProfiles;
    }

    public void setCompanyProfiles(List<Object> companyProfiles) {
        this.companyProfiles = companyProfiles;
    }

    public Object getCourse() {
        return course;
    }

    public void setCourse(Object course) {
        this.course = course;
    }

    public Object getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Object dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Object getDepartment() {
        return department;
    }

    public void setDepartment(Object department) {
        this.department = department;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getFacsimile() {
        return facsimile;
    }

    public void setFacsimile(Object facsimile) {
        this.facsimile = facsimile;
    }

    public Object getFavouriteBooks() {
        return favouriteBooks;
    }

    public void setFavouriteBooks(Object favouriteBooks) {
        this.favouriteBooks = favouriteBooks;
    }

    public Object getFavouriteMovies() {
        return favouriteMovies;
    }

    public void setFavouriteMovies(Object favouriteMovies) {
        this.favouriteMovies = favouriteMovies;
    }

    public Object getFavouriteQuotes() {
        return favouriteQuotes;
    }

    public void setFavouriteQuotes(Object favouriteQuotes) {
        this.favouriteQuotes = favouriteQuotes;
    }

    public Object getFavouriteTvShows() {
        return favouriteTvShows;
    }

    public void setFavouriteTvShows(Object favouriteTvShows) {
        this.favouriteTvShows = favouriteTvShows;
    }

    public Object getHomepage() {
        return homepage;
    }

    public void setHomepage(Object homepage) {
        this.homepage = homepage;
    }

    public Object getHomephone() {
        return homephone;
    }

    public void setHomephone(Object homephone) {
        this.homephone = homephone;
    }

    public String getImageThumbUrl() {
        return imageThumbUrl;
    }

    public void setImageThumbUrl(String imageThumbUrl) {
        this.imageThumbUrl = imageThumbUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public Object getNickname() {
        return nickname;
    }

    public void setNickname(Object nickname) {
        this.nickname = nickname;
    }

    public String getPersonalSummary() {
        return personalSummary;
    }

    public void setPersonalSummary(String personalSummary) {
        this.personalSummary = personalSummary;
    }

    public Object getPosition() {
        return position;
    }

    public void setPosition(Object position) {
        this.position = position;
    }

    public Object getProps() {
        return props;
    }

    public void setProps(Object props) {
        this.props = props;
    }

    public Object getPublications() {
        return publications;
    }

    public void setPublications(Object publications) {
        this.publications = publications;
    }

    public Object getRoom() {
        return room;
    }

    public void setRoom(Object room) {
        this.room = room;
    }

    public Object getSchool() {
        return school;
    }

    public void setSchool(Object school) {
        this.school = school;
    }

    public SocialInfo getSocialInfo() {
        return socialInfo;
    }

    public void setSocialInfo(SocialInfo socialInfo) {
        this.socialInfo = socialInfo;
    }

    public Object getStaffProfile() {
        return staffProfile;
    }

    public void setStaffProfile(Object staffProfile) {
        this.staffProfile = staffProfile;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public Object getSubjects() {
        return subjects;
    }

    public void setSubjects(Object subjects) {
        this.subjects = subjects;
    }

    public Object getUniversityProfileUrl() {
        return universityProfileUrl;
    }

    public void setUniversityProfileUrl(Object universityProfileUrl) {
        this.universityProfileUrl = universityProfileUrl;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public Object getWorkphone() {
        return workphone;
    }

    public void setWorkphone(Object workphone) {
        this.workphone = workphone;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
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

}