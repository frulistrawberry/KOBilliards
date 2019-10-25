package com.yuyuka.billiards.pojo;

import java.io.Serializable;

public class MinePojo implements Serializable {
    private int id;
    private String userName;
    private String loginName;
    private String wxId;
    private String phoneNum;
    private String headImage;
    private String created;
    private String currentDuan;
    private String hisDuan;
    private long combatEffectiveness;
    private String realName;
    private CustomNoticePojo.Duan rankingConfigurtion;
    private int loginType;
    private CustomNoticePojo.Duan hisRankingConfigurtion;
    private int fansTotal;
    private int praiseTotal;
    private int followTotal;
    private int releaseTotal;
    private int sex;
    private String warZone;
    private int creditScore;
    private int authentication;

    public int getAuthentication() {
        return authentication;
    }

    public void setAuthentication(int authentication) {
        this.authentication = authentication;
    }

    private BilliardsTotalReturnBaseDto billiardsTotalReturnBaseDto;


    public String getWarZone() {
        return warZone;
    }

    public void setWarZone(String warZone) {
        this.warZone = warZone;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    public BilliardsTotalReturnBaseDto getBilliardsTotalReturnBaseDto() {
        return billiardsTotalReturnBaseDto;
    }

    public void setBilliardsTotalReturnBaseDto(BilliardsTotalReturnBaseDto billiardsTotalReturnBaseDto) {
        this.billiardsTotalReturnBaseDto = billiardsTotalReturnBaseDto;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getCurrentDuan() {
        return currentDuan;
    }

    public void setCurrentDuan(String currentDuan) {
        this.currentDuan = currentDuan;
    }

    public String getHisDuan() {
        return hisDuan;
    }

    public void setHisDuan(String hisDuan) {
        this.hisDuan = hisDuan;
    }

    public long getCombatEffectiveness() {
        return combatEffectiveness;
    }

    public void setCombatEffectiveness(long combatEffectiveness) {
        this.combatEffectiveness = combatEffectiveness;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public CustomNoticePojo.Duan getRankingConfigurtion() {
        return rankingConfigurtion;
    }

    public void setRankingConfigurtion(CustomNoticePojo.Duan rankingConfigurtion) {
        this.rankingConfigurtion = rankingConfigurtion;
    }

    public int getLoginType() {
        return loginType;
    }

    public void setLoginType(int loginType) {
        this.loginType = loginType;
    }

    public CustomNoticePojo.Duan getHisRankingConfigurtion() {
        return hisRankingConfigurtion;
    }

    public void setHisRankingConfigurtion(CustomNoticePojo.Duan hisRankingConfigurtion) {
        this.hisRankingConfigurtion = hisRankingConfigurtion;
    }

    public int getFansTotal() {
        return fansTotal;
    }

    public void setFansTotal(int fansTotal) {
        this.fansTotal = fansTotal;
    }

    public int getPraiseTotal() {
        return praiseTotal;
    }

    public void setPraiseTotal(int praiseTotal) {
        this.praiseTotal = praiseTotal;
    }

    public int getFollowTotal() {
        return followTotal;
    }

    public void setFollowTotal(int followTotal) {
        this.followTotal = followTotal;
    }

    public int getReleaseTotal() {
        return releaseTotal;
    }

    public void setReleaseTotal(int releaseTotal) {
        this.releaseTotal = releaseTotal;
    }
}
