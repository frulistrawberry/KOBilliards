package com.yuyuka.billiards.pojo;

import java.io.Serializable;

public class GoodsComment implements Serializable {
    int id;
    int secondMallId;
    int userId;
    String commentContent;
    String commentDate;
    BilliardsUsers billiardsUsers;

    public int getId() {
        return id;
    }

    public int getSecondMallId() {
        return secondMallId;
    }

    public int getUserId() {
        return userId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public BilliardsUsers getBilliardsUsers() {
        return billiardsUsers;
    }
}
