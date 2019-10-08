package com.yuyuka.billiards.pojo;

import java.io.Serializable;

public class Tag implements Serializable {
    int id;
    String tagName;
    String created;
    String mark;
    boolean isSelected;

    public int getId() {
        return id;
    }

    public String getTagName() {
        return tagName;
    }

    public String getCreated() {
        return created;
    }

    public String getMark() {
        return mark;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isSelected() {
        return isSelected;
    }
}
