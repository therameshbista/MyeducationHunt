package com.example.user.educationhunt.pojos;

import java.io.Serializable;

/**
 * Created by user on 11/21/2016.
 */
public class Bookmarkitem implements Serializable{

    public int bookmarkID; //school or college or university id
    public String name;
    public String address;
    public String logo;
    public String country;

    public String phone;
    public String email;
    public String website;
    public String institution_type;
    public String establishment_date;
    public String admission_open_from;
    public String admission_open_to;
    public String latitude;
    public String longitude;


    public int getBookmarkID() {
        return bookmarkID;
    }

    public void setBookmarkID(int bookmarkID) {
        this.bookmarkID = bookmarkID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getLogo() {
        return logo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getInstitution_type() {
        return institution_type;
    }

    public void setInstitution_type(String institution_type) {
        this.institution_type = institution_type;
    }

    public String getEstablishment_date() {
        return establishment_date;
    }

    public void setEstablishment_date(String establishment_date) {
        this.establishment_date = establishment_date;
    }

    public String getAdmission_open_from() {
        return admission_open_from;
    }

    public void setAdmission_open_from(String admission_open_from) {
        this.admission_open_from = admission_open_from;
    }

    public String getAdmission_open_to() {
        return admission_open_to;
    }

    public void setAdmission_open_to(String admission_open_to) {
        this.admission_open_to = admission_open_to;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
