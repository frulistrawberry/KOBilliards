package com.yuyuka.billiards.event;


import com.yuyuka.billiards.widget.AppBarStateChangeListener;

public class OffsetChangeEvent {
    public String from;
    public AppBarStateChangeListener.State state;

    public OffsetChangeEvent(String from, AppBarStateChangeListener.State state) {
        this.from = from;
        this.state = state;
    }
}
