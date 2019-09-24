package com.yuyuka.billiards.pojo;

import java.io.Serializable;

public class GroupFragmentBean implements Serializable {
     private String imgUrl;
     private String name;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
