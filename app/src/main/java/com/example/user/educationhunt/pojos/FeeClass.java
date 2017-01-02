package com.example.user.educationhunt.pojos;

import java.io.Serializable;

/**
 * Created by user on 12/19/2016.
 */
 public class FeeClass implements Serializable{
    public String grade;
    public String fee;

    public String getFee() {
        return fee;
    }
    public void setFee(String fee) {
        this.fee = fee;
    }
    public void setGrade(String grade){
        this.grade=grade;
    }
    public String getGrade(){
        return this.grade;
    }
}