package com.example.user.educationhunt.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/16/2016.
 */
public class OurUniversity implements Serializable {

    List<Collegefees> feeClassObject= new ArrayList<Collegefees>();

    public void setFeesList(List<Collegefees> feeClassObject){
        this.feeClassObject=feeClassObject;
    }
    public List<Collegefees> getFeesList(){
        return  feeClassObject;
    }

    public int universityId;
    public String universityName;
    public String universityAddress;
    public String universityDistrict;
    public String universityCountry;
    public String universityPhone;
    public String universityEmail;
    public String universityWebsite;
    public String universityAffiliation;
    public String universityType;
    public String universityestDate;
    public String admissionOpendate;
    public String admissionEnddate;
    public String universityLogo;
    public Double universityLatitude;
    public Double universityLongitude;
    public String universityLevel;
    public String universityProgramName;
    public String universityProgramDuration;
    public String universityProgramFee;

    public int getUniversityId() {
        return universityId;
    }

    public String getUniversityName() {
        return universityName;
    }

    public String getUniversityAddress() {
        return universityAddress;
    }

    public String getUniversityDistrict() {
        return universityDistrict;
    }

    public String getUniverstiyCountry() {
        return universityCountry;
    }

    public String getUniversityPhone() {
        return universityPhone;
    }

    public String getUniversityEmail() {
        return universityEmail;
    }

    public String getUniversityWebsite() {
        return universityWebsite;
    }

    public String getUniversityAffiliation() {
        return universityAffiliation;
    }

    public String getUniversityType() {
        return universityType;
    }

    public String getUniversityestDate() {
        return universityestDate;
    }

    public String getAdmissionOpendate() {
        return admissionOpendate;
    }

    public String getAdmissionEnddate() {
        return admissionEnddate;
    }

    public String getUniversityLogo() {
        return universityLogo;
    }

    public Double getUniversityLatitude() {
        return universityLatitude;
    }

    public Double getUniversityLongitude() {
        return universityLongitude;
    }

    public String getUniversityLevel() {
        return universityLevel;
    }

    public String getUniversityProgramName() {
        return universityProgramName;
    }

    public String getUniversityProgramDuration() {
        return universityProgramDuration;
    }

    public String getUniversityProgramFee() {
        return universityProgramFee;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public void setUniversityAddress(String universityAddress) {
        this.universityAddress = universityAddress;
    }

    public void setUniversityDistrict(String universityDistrict) {
        this.universityDistrict = universityDistrict;
    }

    public void setUniversityPhone(String universityPhone) {
        this.universityPhone = universityPhone;
    }

    public void setUniverstiyCountry(String universtiyCountry) {
        this.universityCountry = universtiyCountry;
    }

    public void setUniversityEmail(String universityEmail) {
        this.universityEmail = universityEmail;
    }

    public void setUniversityWebsite(String universityWebsite) {
        this.universityWebsite = universityWebsite;
    }

    public void setUniversityAffiliation(String universityAffiliation) {
        this.universityAffiliation = universityAffiliation;
    }

    public void setUniversityType(String universityType) {
        this.universityType = universityType;
    }

    public void setUniversityestDate(String universityestDate) {
        this.universityestDate = universityestDate;
    }

    public void setAdmissionEnddate(String admissionEnddate) {
        this.admissionEnddate = admissionEnddate;
    }

    public void setAdmissionOpendate(String admissionOpendate) {
        this.admissionOpendate = admissionOpendate;
    }

    public void setUniversityLogo(String universityLogo) {
        this.universityLogo = universityLogo;
    }

    public void setUniversityLatitude(Double universityLatitude) {
        this.universityLatitude = universityLatitude;
    }

    public void setUniversityLongitude(Double universityLongitude) {
        this.universityLongitude = universityLongitude;
    }

    public void setUniversityLevel(String universityLevel) {
        this.universityLevel = universityLevel;
    }

    public void setUniversityProgramName(String universityProgramName) {
        this.universityProgramName = universityProgramName;
    }

    public void setUniversityProgramDuration(String universityProgramDuration) {
        this.universityProgramDuration = universityProgramDuration;
    }

    public void setUniversityProgramFee(String universityProgramFee) {
        this.universityProgramFee = universityProgramFee;
    }
}
