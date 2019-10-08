package com.yuyuka.billiards.pojo;

import java.io.Serializable;
import java.util.List;

public class AttentionNewsListPojo implements Serializable {
    List<BilliardsUsers> userFollow;

    List<NewsItem> consultationList;

    public void setUserFollow(List<BilliardsUsers> userFollow) {
        this.userFollow = userFollow;
    }

    public void setConsultationList(List<NewsItem> consultationList) {
        this.consultationList = consultationList;
    }

    public List<BilliardsUsers> getUserFollow() {
        return userFollow;
    }

    public List<NewsItem> getConsultationList() {
        return consultationList;
    }
}
