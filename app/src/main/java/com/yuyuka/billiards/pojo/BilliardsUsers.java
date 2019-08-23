package com.yuyuka.billiards.pojo;

import java.io.Serializable;

public class BilliardsUsers implements Serializable {
    private int id;
    private String userName;
    private String loginName;
    private String loginPass;
    private String wxId;
    private String phoneNum;
    private String headImage;
    private String userRealName;
    private long idCard;
    private long authentication;
    private String created;
    private String reason;
    private String idCardPositive;
    private String idCardBack;
    private String idCardHand;
    private String industry;

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

    public String getWxId() {
        return wxId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getHeadImage() {
        return headImage;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public long getIdCard() {
        return idCard;
    }

    public long getAuthentication() {
        return authentication;
    }

    public String getCreated() {
        return created;
    }

    public String getReason() {
        return reason;
    }

    public String getIdCardPositive() {
        return idCardPositive;
    }

    public String getIdCardBack() {
        return idCardBack;
    }

    public String getIdCardHand() {
        return idCardHand;
    }

    public String getIndustry() {
        return industry;
    }
}
