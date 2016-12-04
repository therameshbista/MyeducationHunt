package com.example.user.educationhunt.pojos;

import java.io.Serializable;

/**
 * Created by user on 11/21/2016.
 */
public class Bookmarkitem{

    public int bookmarkID; //school or college or university id
    public String name;
    public String address;
    public String logo;

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
}
