package com.yuyuka.billiards.pojo;

import java.io.Serializable;

public class MessageBean implements Serializable {
     private String imgurl;
     private String tvName="群组名";
     private String tvmessage;
     private String time;
     private String img;

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getTvName() {
        return tvName;
    }

    public void setTvName(String tvName) {
        this.tvName = tvName;
    }

    public String getTvmessage() {
        return tvmessage;
    }

    public void setTvmessage(String tvmessage) {
        this.tvmessage = tvmessage;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
