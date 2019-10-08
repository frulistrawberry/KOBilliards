package com.yuyuka.billiards.pojo;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

public class NewsReplyItem implements Serializable , MultiItemEntity {
    int id;
    int messageId;
    int userId;
    int consultationId;
    String replyContent;
    BilliardsUsers billiardsUsers;

    public int getId() {
        return id;
    }

    public int getMessageId() {
        return messageId;
    }

    public int getUserId() {
        return userId;
    }

    public int getConsultationId() {
        return consultationId;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public BilliardsUsers getBilliardsUsers() {
        return billiardsUsers;
    }

    @Override
    public int getItemType() {
        return 1;
    }
}
