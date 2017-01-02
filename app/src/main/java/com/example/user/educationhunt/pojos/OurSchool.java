package com.example.user.educationhunt.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/14/2016.
 */
public class OurSchool implements Serializable {

    List<FeeClass> feeClassObject= new ArrayList<FeeClass>();

    public void setFeesList(List<FeeClass> feeClassObject){
        this.feeClassObject=feeClassObject;
    }
    public List<FeeClass> getFeesList(){
        return  feeClassObject;
    }

    public int schoolId;
    public String schoolName;
    public String schoolAddress;
    public String schoolDistrict;
    public String schoolCountry;
    public String schoolPhone;
    public String schoolEmail;
    public String schoolWebsite;
    public String schoolAffiliation;
    public String schoolType;
    public String estDate;
    public String admissinOpenDate;
    public String admissionEndDate;
    public String schoolLogo;
    public Double latitude;
    public Double longitude;

    public int getSchoolId() {
        return schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public String getSchoolAddress() {
        return schoolAddress;
    }

    public String getSchoolDistrict() {
        return schoolDistrict;
    }

    public String getSchoolCountry() {
        return schoolCountry;
    }

    public String getSchoolPhone() {
        return schoolPhone;
    }

    public String getSchoolEmail() {
        return schoolEmail;
    }

    public String getSchoolWebsite() {
        return schoolWebsite;
    }

    public String getSchoolAffiliation() {
        return schoolAffiliation;
    }

    public String getSchoolType() {
        return schoolType;
    }

    public String getEstDate() {
        return estDate;
    }

    public String getAdmissionEndDate() {
        return admissionEndDate;
    }

    public String getAdmissinOpenDate() {
        return admissinOpenDate;
    }

    public String getSchoolLogo() {
        return schoolLogo;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public void setSchoolDistrict(String schoolDistrict) {
        this.schoolDistrict = schoolDistrict;
    }

    public void setSchoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress;
    }

    public void setSchoolCountry(String schoolCountry) {
        this.schoolCountry = schoolCountry;
    }

    public void setSchoolPhone(String schoolPhone) {
        this.schoolPhone = schoolPhone;
    }

    public void setSchoolEmail(String schoolEmail) {
        this.schoolEmail = schoolEmail;
    }

    public void setSchoolWebsite(String schoolWebsite) {
        this.schoolWebsite = schoolWebsite;
    }

    public void setSchoolAffiliation(String schoolAffiliation) {
        this.schoolAffiliation = schoolAffiliation;
    }

    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType;
    }

    public void setAdmissinOpenDate(String admissinOpenDate) {
        this.admissinOpenDate = admissinOpenDate;
    }

    public void setEstDate(String estDate) {
        this.estDate = estDate;
    }

    public void setAdmissionEndDate(String admissionEndDate) {
        this.admissionEndDate = admissionEndDate;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setSchoolLogo(String schoolLogo) {
        this.schoolLogo = schoolLogo;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
