package com.kobilliards.pojo;

import java.io.Serializable;

public class AlbumBean implements Serializable {
     private String  imageUrl;
     private String  text;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
