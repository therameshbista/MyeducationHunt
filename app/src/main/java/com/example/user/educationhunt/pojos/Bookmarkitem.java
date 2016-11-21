package com.example.user.educationhunt.pojos;

/**
 * Created by user on 11/21/2016.
 */
public class Bookmarkitem {

    String name;
    String address;
    String logo;

    public Bookmarkitem(String name, String address, String logo) {
        this.name = name;
        this.address = address;
        this.logo = logo;
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
