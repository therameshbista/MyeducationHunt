package com.example.user.educationhunt.pojos;

import java.io.Serializable;

/**
 * Created by user on 11/3/2016.
 */
public class OurSchool implements Serializable {

    public String schoolName;
    private String schoolLogo;
    public int schoolId;
    public String schoolLocation;
    public String createdAt;
    public String updatedAt;
    public String schoolEmail;
    public String schoolWebsite;

    public String getSchoolWebsite() {
        return schoolWebsite;
    }

    public void setSchoolWebsite(String schoolWebsite) {
        this.schoolWebsite = schoolWebsite;
    }

    public String getSchoolEmail() {
        return schoolEmail;
    }

    public void setSchoolEmail(String schoolEmail) {
        this.schoolEmail = schoolEmail;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public int setSchoolId(int schoolId) {
        this.schoolId = schoolId;
        return schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolLogo() {
        return schoolLogo;
    }

    public void setSchoolLogo(String schoolLogo) {
        this.schoolLogo = schoolLogo;
    }

    public String getSchoolLocation() {
        return schoolLocation;
    }

    public void setSchoolLocation(String schoolLocation) {
        this.schoolLocation = schoolLocation;
    }

}