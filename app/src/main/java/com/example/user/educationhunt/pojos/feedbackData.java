package com.example.user.educationhunt.pojos;

/**
 * Created by user on 12/16/2016.
 */
public class feedbackData {
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getComment() {
        return comment;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    String name;
    String email;
    String comment;
}
