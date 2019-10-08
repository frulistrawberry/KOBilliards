package com.yuyuka.billiards.event;

import java.io.Serializable;

public class SelectPicEvent implements Serializable {
    public String tag;
    public String img;
    public String text;
    public int position;
}
