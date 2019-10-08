package com.yuyuka.billiards.pojo;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

public class NewsCommentItem implements Serializable, MultiItemEntity {
    private int id;
    private int userId;
    private int consultationId;
    private String created;
    private String content;
    private BilliardsUsers billiardsUsers;
    private boolean isAttention;

    public boolean isAttention() {
        return isAttention;
    }

    public void setAttention(boolean attention) {
        isAttention = attention;
    }

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

    @Override
    public int getItemType() {
        return 0;
    }
}
