package com.yuyuka.billiards.pojo;

import java.io.Serializable;

public class SelectTimePojo implements Serializable {
    boolean isSelected;
    String content;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
