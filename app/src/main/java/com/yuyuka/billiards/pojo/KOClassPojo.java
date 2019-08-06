package com.yuyuka.billiards.pojo;

import java.io.Serializable;

public class KOClassPojo implements Serializable {
    private String cType;
    private String address;
    private String id;

    public String getcType() {
        return cType;
    }

    public String getAddress() {
        return address;
    }

    public String getId() {
        return id;
    }
}
