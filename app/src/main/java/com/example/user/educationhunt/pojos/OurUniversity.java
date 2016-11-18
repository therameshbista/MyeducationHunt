package com.example.user.educationhunt.pojos;

import java.io.Serializable;

/**
 * Created by user on 11/16/2016.
 */
public class OurUniversity implements Serializable {

    public String universityName;
    public String universityLogo;
    public int universityId;
    public String universityLocation;
    public String universitycreatedAt;
    public String universityupdatedAt;
    public String universityEmail;
    public String universityWebsite;

    public String getUniversityName() {
        return universityName;
    }

    public String getUniversityLogo() {
        return universityLogo;
    }

    public int getUniversityId() {
        return universityId;
    }

    public String getUniversityLocation() {
        return universityLocation;
    }

    public String getUniversitycreatedAt() {
        return universitycreatedAt;
    }

    public String getUniversityupdatedAt() {
        return universityupdatedAt;
    }

    public String getUniversityEmail() {
        return universityEmail;
    }

    public String getUniversityWebsite() {
        return universityWebsite;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public void setUniversityLogo(String universityLogo) {
        this.universityLogo = universityLogo;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    public void setUniversitycreatedAt(String universitycreatedAt) {
        this.universitycreatedAt = universitycreatedAt;
    }

    public void setUniversityLocation(String universityLocation) {
        this.universityLocation = universityLocation;
    }

    public void setUniversityupdatedAt(String universityupdatedAt) {
        this.universityupdatedAt = universityupdatedAt;
    }

    public void setUniversityEmail(String universityEmail) {
        this.universityEmail = universityEmail;
    }

    public void setUniversityWebsite(String universityWebsite) {
        this.universityWebsite = universityWebsite;
    }


}
