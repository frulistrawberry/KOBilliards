package com.yuyuka.billiards.pojo;

import java.io.Serializable;

public class CustomNoticePojo implements Serializable {
    private int noticeType;

    private BizContent bizContent;

    public void setNoticeType(int noticeType){
        this.noticeType = noticeType;
    }
    public int getNoticeType(){
        return this.noticeType;
    }
    public void setBizContent(BizContent bizContent){
        this.bizContent = bizContent;
    }
    public BizContent getBizContent(){
        return this.bizContent;
    }

    public static class BizContent implements Serializable{
        private Battle battle;

        private User user1;
        private User user2;

        public User getUser2() {
            return user2;
        }

        public void setUser2(User user2) {
            this.user2 = user2;
        }

        public void setBattle(Battle battle){
            this.battle = battle;
        }
        public Battle getBattle(){
            return this.battle;
        }
        public void setUser1(User user1){
            this.user1 = user1;
        }
        public User getUser1(){
            return this.user1;
        }

    }

    public static class Battle implements Serializable{
        private int id;

        private int userId1;

        private int battleType;

        private int status;

        private String beginDate;

        private String created;

        private int refOrderId;

        private long beforeOne;

        private long beginTime;

        public void setId(int id){
            this.id = id;
        }
        public int getId(){
            return this.id;
        }
        public void setUserId1(int userId1){
            this.userId1 = userId1;
        }
        public int getUserId1(){
            return this.userId1;
        }
        public void setBattleType(int battleType){
            this.battleType = battleType;
        }
        public int getBattleType(){
            return this.battleType;
        }
        public void setStatus(int status){
            this.status = status;
        }
        public int getStatus(){
            return this.status;
        }
        public void setBeginDate(String beginDate){
            this.beginDate = beginDate;
        }
        public String getBeginDate(){
            return this.beginDate;
        }
        public void setCreated(String created){
            this.created = created;
        }
        public String getCreated(){
            return this.created;
        }
        public void setRefOrderId(int refOrderId){
            this.refOrderId = refOrderId;
        }
        public int getRefOrderId(){
            return this.refOrderId;
        }
        public void setBeforeOne(int beforeOne){
            this.beforeOne = beforeOne;
        }
        public long getBeforeOne(){
            return this.beforeOne;
        }
        public void setBeginTime(int beginTime){
            this.beginTime = beginTime;
        }
        public long getBeginTime(){
            return this.beginTime;
        }

    }

    public static class User implements Serializable{
        private int id;

        private String userName;

        private String loginName;

        private String wxId;

        private String phoneNum;

        private String headImage;

        private String created;

        private int sex;

        private String realName;

        public void setId(int id){
            this.id = id;
        }
        public int getId(){
            return this.id;
        }
        public void setUserName(String userName){
            this.userName = userName;
        }
        public String getUserName(){
            return this.userName;
        }
        public void setLoginName(String loginName){
            this.loginName = loginName;
        }
        public String getLoginName(){
            return this.loginName;
        }
        public void setWxId(String wxId){
            this.wxId = wxId;
        }
        public String getWxId(){
            return this.wxId;
        }
        public void setPhoneNum(String phoneNum){
            this.phoneNum = phoneNum;
        }
        public String getPhoneNum(){
            return this.phoneNum;
        }
        public void setHeadImage(String headImage){
            this.headImage = headImage;
        }
        public String getHeadImage(){
            return this.headImage;
        }
        public void setCreated(String created){
            this.created = created;
        }
        public String getCreated(){
            return this.created;
        }
        public void setSex(int sex){
            this.sex = sex;
        }
        public int getSex(){
            return this.sex;
        }
        public void setRealName(String realName){
            this.realName = realName;
        }
        public String getRealName(){
            return this.realName;
        }

    }
}
