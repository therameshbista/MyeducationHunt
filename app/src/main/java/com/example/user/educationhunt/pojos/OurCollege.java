package com.example.user.educationhunt.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/14/2016.
 */
public class OurCollege implements Serializable {

    List<Collegefees> feeClassObject= new ArrayList<Collegefees>();

    public void setFeesList(List<Collegefees> feeClassObject){
        this.feeClassObject=feeClassObject;
    }
    public List<Collegefees> getFeesList(){
        return  feeClassObject;
    }

    public int collegeId;
    public String collegeName;
    public String collegeAddress;
    public String collegeDistrict;
    public String collegeCountry;
    public String collegePhone;
    public String collegeEmail;
    public String collegeWebsite;
    public String collegeAffiliation;
    public String collegeType;
    public String estDate;
    public String admissinOpenDate;
    public String admissionEndDate;
    public String collegeLogo;
    public String collegeLevel;
    public String collegeProgramDuration;
    public String collegeFee;
    public String collegeProgramName;
    public Double latitude;
    public Double longitude;

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongtitude() {
        return longitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongtitude(Double longtitude) {
        this.longitude = longtitude;
    }

    public int getCollegeId() {
        return collegeId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public String getCollegeAddress() {
        return collegeAddress;
    }

    public String getCollegeDistrict() {
        return collegeDistrict;
    }

    public String getCollegeCountry() {
        return collegeCountry;
    }

    public String getCollegeEmail() {
        return collegeEmail;
    }

    public String getCollegePhone() {
        return collegePhone;
    }

    public String getCollegeWebsite() {
        return collegeWebsite;
    }

    public String getCollegeAffiliation() {
        return collegeAffiliation;
    }

    public String getCollegeType() {
        return collegeType;
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

    public String getCollegeLogo() {
        return collegeLogo;
    }

    public String getCollegeLevel() {
        return collegeLevel;
    }

    public String getCollegeProgramDuration() {
        return collegeProgramDuration;
    }

    public String getCollegeProgramName() {
        return collegeProgramName;
    }

    public String getCollegeFee() {
        return collegeFee;
    }

    public void setCollegeId(int collegeId) {
        this.collegeId = collegeId;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public void setCollegeAddress(String collegeAddress) {
        this.collegeAddress = collegeAddress;
    }

    public void setCollegeDistrict(String collegeDistrict) {
        this.collegeDistrict = collegeDistrict;
    }

    public void setCollegeCountry(String collegeCountry) {
        this.collegeCountry = collegeCountry;
    }

    public void setCollegePhone(String collegePhone) {
        this.collegePhone = collegePhone;
    }

    public void setCollegeWebsite(String collegeWebsite) {
        this.collegeWebsite = collegeWebsite;
    }

    public void setCollegeEmail(String collegeEmail) {
        this.collegeEmail = collegeEmail;
    }

    public void setCollegeAffiliation(String collegeAffiliation) {
        this.collegeAffiliation = collegeAffiliation;
    }

    public void setCollegeType(String collegeType) {
        this.collegeType = collegeType;
    }

    public void setEstDate(String estDate) {
        this.estDate = estDate;
    }

    public void setAdmissinOpenDate(String admissinOpenDate) {
        this.admissinOpenDate = admissinOpenDate;
    }

    public void setCollegeLogo(String collegeLogo) {
        this.collegeLogo = collegeLogo;
    }

    public void setAdmissionEndDate(String admissionEndDate) {
        this.admissionEndDate = admissionEndDate;
    }

    public void setCollegeLevel(String collegeLevel) {
        this.collegeLevel = collegeLevel;
    }

    public void setCollegeProgramDuration(String collegeProgramDuration) {
        this.collegeProgramDuration = collegeProgramDuration;
    }

    public void setCollegeFee(String collegeFee) {
        this.collegeFee = collegeFee;
    }

    public void setCollegeProgramName(String collegeProgramName) {
        this.collegeProgramName = collegeProgramName;
    }
}
