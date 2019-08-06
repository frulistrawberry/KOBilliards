package com.yuyuka.billiards.pojo;

import java.io.Serializable;

public class KOListPojo implements Serializable {

    private String address;
    private String info;
    private String imageAdd;
    private String title;

    public String getInfo() {
        return info;
    }

    public String getImageAdd() {
        return imageAdd;
    }

    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }
}
