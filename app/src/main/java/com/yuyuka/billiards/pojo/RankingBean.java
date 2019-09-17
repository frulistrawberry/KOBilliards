package com.yuyuka.billiards.pojo;

import java.io.Serializable;

public class RankingBean implements Serializable {
  private String imgurl;
  private String text;

    public RankingBean() {
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
