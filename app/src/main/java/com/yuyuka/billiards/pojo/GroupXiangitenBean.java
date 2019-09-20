package com.yuyuka.billiards.pojo;

import java.io.Serializable;

public class GroupXiangitenBean implements Serializable {
    private String imgUrl;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
