package com.yuyuka.billiards.pojo;

import java.io.Serializable;

public class SiRuoZhiyeBean implements Serializable {
     private String name;
     private String leixing;
     private String time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLeixing() {
        return leixing;
    }

    public void setLeixing(String leixing) {
        this.leixing = leixing;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
