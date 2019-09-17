package com.yuyuka.billiards.pojo;

import java.io.Serializable;

public class SiRuoZhiyeBangBean implements Serializable {
    private String imgUrl;
    private String name;
    private String jifen;

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

    public String getJifen() {
        return jifen;
    }

    public void setJifen(String jifen) {
        this.jifen = jifen;
    }
}
