package com.example.user.educationhunt.pojos;

import java.io.Serializable;

/**
 * Created by user on 12/19/2016.
 */
public class Collegefees implements Serializable {

    public String level;
    public String programName;
    public String duration;
    public String fee;

    public String getLevel() {
        return level;
    }

    public String getProgramName() {
        return programName;
    }

    public String getFee() {
        return fee;
    }

    public String getDuration() {
        return duration;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }
}
