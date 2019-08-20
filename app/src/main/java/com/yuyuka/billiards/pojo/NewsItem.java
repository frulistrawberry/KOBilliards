package com.yuyuka.billiards.pojo;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class NewsItem implements MultiItemEntity {

    private int id;
    private int userId;
    private int consultationType;
    private String address;
    private int isExamine;
    private String created;
    private String title;
    private int isDelete;
    private int viewLongtime;
    private String contentInfo;
    private String coverImageAdd;
    private BilliardsUsers billiardsUsers;


    @Override
    public int getItemType() {
        return consultationType;
    }

    public static class BilliardsUsers{
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

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getConsultationType() {
        return consultationType;
    }

    public String getAddress() {
        return address;
    }

    public int getIsExamine() {
        return isExamine;
    }

    public String getCreated() {
        return created;
    }

    public String getTitle() {
        return title;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public int getViewLongtime() {
        return viewLongtime;
    }

    public String getContentInfo() {
        return contentInfo;
    }

    public String getCoverImageAdd() {
        return coverImageAdd;
    }

    public BilliardsUsers getBilliardsUsers() {
        return billiardsUsers;
    }
}
