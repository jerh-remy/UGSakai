package com.sakai.ug.sakaiapp.models.site;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Props {

    @SerializedName("sections_externally_maintained")
    @Expose
    private String sectionsExternallyMaintained;
    @SerializedName("contact-name")
    @Expose
    private String contactName;
    @SerializedName("contact-email")
    @Expose
    private String contactEmail;
    @SerializedName("sections_student_switching_allowed")
    @Expose
    private String sectionsStudentSwitchingAllowed;
    @SerializedName("term")
    @Expose
    private String term;
    @SerializedName("sections_student_registration_allowed")
    @Expose
    private String sectionsStudentRegistrationAllowed;
    @SerializedName("term_eid")
    @Expose
    private String termEid;

    public Props(String contactName) {
        this.contactName = contactName;
    }

    public String getSectionsExternallyMaintained() {
        return sectionsExternallyMaintained;
    }

    public void setSectionsExternallyMaintained(String sectionsExternallyMaintained) {
        this.sectionsExternallyMaintained = sectionsExternallyMaintained;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getSectionsStudentSwitchingAllowed() {
        return sectionsStudentSwitchingAllowed;
    }

    public void setSectionsStudentSwitchingAllowed(String sectionsStudentSwitchingAllowed) {
        this.sectionsStudentSwitchingAllowed = sectionsStudentSwitchingAllowed;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getSectionsStudentRegistrationAllowed() {
        return sectionsStudentRegistrationAllowed;
    }

    public void setSectionsStudentRegistrationAllowed(String sectionsStudentRegistrationAllowed) {
        this.sectionsStudentRegistrationAllowed = sectionsStudentRegistrationAllowed;
    }

    public String getTermEid() {
        return termEid;
    }

    public void setTermEid(String termEid) {
        this.termEid = termEid;
    }

}