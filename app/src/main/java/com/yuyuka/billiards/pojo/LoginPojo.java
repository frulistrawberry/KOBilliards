package com.yuyuka.billiards.pojo;

import java.io.Serializable;

public class LoginPojo implements Serializable {
    String token;
    String userInfo;

    public String getToken() {
        return token;
    }

    public String getUserInfo() {
        return userInfo;
    }
}
