package com.yuyuka.billiards.net;

import java.io.Serializable;

public class CanJuBean implements Serializable {
    private  String imgUrl;
    private String name;
    private String canju;

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

    public String getCanju() {
        return canju;
    }

    public void setCanju(String canju) {
        this.canju = canju;
    }
}
