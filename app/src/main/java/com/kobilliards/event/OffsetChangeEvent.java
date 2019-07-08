package com.kobilliards.event;


import com.kobilliards.widget.AppBarStateChangeListener;

public class OffsetChangeEvent {
    public String from;
    public AppBarStateChangeListener.State state;

    public OffsetChangeEvent(String from, AppBarStateChangeListener.State state) {
        this.from = from;
        this.state = state;
    }
}
