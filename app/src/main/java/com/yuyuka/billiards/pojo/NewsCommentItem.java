package com.yuyuka.billiards.pojo;

public class NewsCommentItem {
    private int id;
    private int userId;
    private int consultationId;
    private String created;
    private String content;
    private BilliardsUsers billiardsUsers;


    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getConsultationId() {
        return consultationId;
    }

    public String getCreated() {
        return created;
    }

    public String getContent() {
        return content;
    }

    public BilliardsUsers getBilliardsUsers() {
        return billiardsUsers;
    }
}
