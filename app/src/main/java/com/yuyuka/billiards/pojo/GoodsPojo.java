package com.yuyuka.billiards.pojo;

import java.io.Serializable;
import java.util.List;

public class GoodsPojo implements Serializable {
    private String created;
    private String goodsImages;
    private String goodsName;
    private String id;
    private String dist;
    private int originalPrice;
    private double price;
    private String remark;
    private int secondMallType;
    private int status;
    private int transactionType;
    private int typeId;
    private String userHeadImage;
    private String userId;
    private String userName;
    private int praiseCount;
    private List<ImagePojo> billiardsSecondMallImagesList;
    private BilliardsUsers billiardsUsers;
    private int wantCount;

    public int getWantCount() {
        return wantCount;
    }

    public BilliardsUsers getBilliardsUsers() {
        return billiardsUsers;
    }

    public List<ImagePojo> getBilliardsSecondMallImagesList() {
        return billiardsSecondMallImagesList;
    }

    public String getCreated() {
        return created;
    }

    public String getGoodsImages() {
        return goodsImages;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public String getId() {
        return id;
    }

    public String getDist() {
        return dist;
    }

    public int getOriginalPrice() {
        return originalPrice;
    }

    public double getPrice() {
        return price;
    }

    public String getRemark() {
        return remark;
    }

    public int getSecondMallType() {
        return secondMallType;
    }

    public int getStatus() {
        return status;
    }

    public int getTransactionType() {
        return transactionType;
    }

    public int getTypeId() {
        return typeId;
    }

    public String getUserHeadImage() {
        return userHeadImage;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public int getPraiseCount() {
        return praiseCount;
    }

}
