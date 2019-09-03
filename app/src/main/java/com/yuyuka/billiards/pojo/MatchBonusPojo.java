package com.yuyuka.billiards.pojo;

import java.io.Serializable;

public class MatchBonusPojo implements Serializable {
    private int id;
    private int matchInfoId;
    private int matchNo;
    private int matchBonus;

    public int getId() {
        return id;
    }

    public int getMatchInfoId() {
        return matchInfoId;
    }

    public int getMatchNo() {
        return matchNo;
    }

    public int getMatchBonus() {
        return matchBonus;
    }
}
