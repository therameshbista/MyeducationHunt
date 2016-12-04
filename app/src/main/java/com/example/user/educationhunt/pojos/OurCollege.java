package com.example.user.educationhunt.pojos;

import java.io.Serializable;

/**
 * Created by user on 11/14/2016.
 */
public class OurCollege implements Serializable {

    public int idCollege;
    public String nameCollege;
    public String locationCollege;
    public String emailCollege;
    ;
    public String createdAtCollege;
    public String updatedAtCollege;
    public String collegeLogoCollege;
    public String websiteCollege;

    public int getIdCollege() {
        return idCollege;
    }

    public String getNameCollege() {
        return nameCollege;
    }

    public String getLocationCollege() {
        return locationCollege;
    }

    public String getEmailCollege() {
        return emailCollege;
    }

    public String getCreatedAtCollege() {
        return createdAtCollege;
    }

    public String getCollegeLogoCollege() {
        return collegeLogoCollege;
    }

    public String getUpdatedAtCollege() {
        return updatedAtCollege;
    }

    public String getWebsiteCollege() {
        return websiteCollege;
    }

    public void setIdCollege(int idCollege) {
        this.idCollege = idCollege;
    }

    public void setNameCollege(String nameCollege) {
        this.nameCollege = nameCollege;
    }

    public void setLocationCollege(String locationCollege) {
        this.locationCollege = locationCollege;
    }

    public void setEmailCollege(String emailCollege) {
        this.emailCollege = emailCollege;
    }

    public void setCreatedAtCollege(String createdAtCollege) {
        this.createdAtCollege = createdAtCollege;
    }

    public void setUpdatedAtCollege(String updatedAtCollege) {
        this.updatedAtCollege = updatedAtCollege;
    }

    public void setCollegeLogoCollege(String collegeLogoCollege) {
        this.collegeLogoCollege = collegeLogoCollege;
    }

    public void setWebsiteCollege(String websiteCollege) {
        this.websiteCollege = websiteCollege;
    }
}
