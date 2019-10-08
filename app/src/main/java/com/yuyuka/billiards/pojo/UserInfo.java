package com.yuyuka.billiards.pojo;

import java.io.Serializable;

public class UserInfo implements Serializable {
    int id;
    String userName;
    String loginName;
    String loginPass;
    String phoneNum;
    String created;

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getLoginPass() {
        return loginPass;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getCreated() {
        return created;
    }
}
