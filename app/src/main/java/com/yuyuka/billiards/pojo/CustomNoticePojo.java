package com.yuyuka.billiards.pojo;

import java.io.Serializable;
import java.util.List;

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

        private UserRank userRank;

        public UserRank getUserRank() {
            return userRank;
        }

        public void setUserRank(UserRank userRank) {
            this.userRank = userRank;
        }

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
        private int user1Point;
        private int user2Point;

        public void setBeforeOne(long beforeOne) {
            this.beforeOne = beforeOne;
        }

        public void setBeginTime(long beginTime) {
            this.beginTime = beginTime;
        }

        public int getUser1Point() {
            return user1Point;
        }

        public void setUser1Point(int user1Point) {
            this.user1Point = user1Point;
        }

        public int getUser2Point() {
            return user2Point;
        }

        public void setUser2Point(int user2Point) {
            this.user2Point = user2Point;
        }

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

    public class UserRank implements Serializable{
        private int userId;

        private int currentDuanPoint;

        private int hisDuanPoint;

        private Duan currentDuan;

        private Duan hisDuan;

        private int combatValue;

        private int addCombatValue;

        private User billiardsUsers;

        public void setUserId(int userId){
            this.userId = userId;
        }
        public int getUserId(){
            return this.userId;
        }
        public void setCurrentDuanPoint(int currentDuanPoint){
            this.currentDuanPoint = currentDuanPoint;
        }
        public int getCurrentDuanPoint(){
            return this.currentDuanPoint;
        }
        public void setHisDuanPoint(int hisDuanPoint){
            this.hisDuanPoint = hisDuanPoint;
        }
        public int getHisDuanPoint(){
            return this.hisDuanPoint;
        }
        public void setCurrentDuan(Duan currentDuan){
            this.currentDuan = currentDuan;
        }
        public Duan getCurrentDuan(){
            return this.currentDuan;
        }
        public void setHisDuan(Duan hisDuan){
            this.hisDuan = hisDuan;
        }
        public Duan getHisDuan(){
            return this.hisDuan;
        }
        public void setCombatValue(int combatValue){
            this.combatValue = combatValue;
        }
        public int getCombatValue(){
            return this.combatValue;
        }
        public void setAddCombatValue(int addCombatValue){
            this.addCombatValue = addCombatValue;
        }
        public int getAddCombatValue(){
            return this.addCombatValue;
        }
        public void setBilliardsUsers(User billiardsUsers){
            this.billiardsUsers = billiardsUsers;
        }
        public User getBilliardsUsers(){
            return this.billiardsUsers;
        }

    }

    public static class Duan implements Serializable{
        private String name;

        private int facingWinPoint;

        private int facingFailPoint;

        private int facingContinuity;

        private int rankWinPoint;

        private int rankFailPoint;

        private int rankContinuity;

        private int matchWinPoint;

        private int matchFailPoint;

        private int matchContinuity;

        private int no;

        private int firstNum;

        private List<RankingObjList> rankingObjList ;

        private UserAt userAt;

        public void setName(String name){
            this.name = name;
        }
        public String getName(){
            return this.name;
        }
        public void setFacingWinPoint(int facingWinPoint){
            this.facingWinPoint = facingWinPoint;
        }
        public int getFacingWinPoint(){
            return this.facingWinPoint;
        }
        public void setFacingFailPoint(int facingFailPoint){
            this.facingFailPoint = facingFailPoint;
        }
        public int getFacingFailPoint(){
            return this.facingFailPoint;
        }
        public void setFacingContinuity(int facingContinuity){
            this.facingContinuity = facingContinuity;
        }
        public int getFacingContinuity(){
            return this.facingContinuity;
        }
        public void setRankWinPoint(int rankWinPoint){
            this.rankWinPoint = rankWinPoint;
        }
        public int getRankWinPoint(){
            return this.rankWinPoint;
        }
        public void setRankFailPoint(int rankFailPoint){
            this.rankFailPoint = rankFailPoint;
        }
        public int getRankFailPoint(){
            return this.rankFailPoint;
        }
        public void setRankContinuity(int rankContinuity){
            this.rankContinuity = rankContinuity;
        }
        public int getRankContinuity(){
            return this.rankContinuity;
        }
        public void setMatchWinPoint(int matchWinPoint){
            this.matchWinPoint = matchWinPoint;
        }
        public int getMatchWinPoint(){
            return this.matchWinPoint;
        }
        public void setMatchFailPoint(int matchFailPoint){
            this.matchFailPoint = matchFailPoint;
        }
        public int getMatchFailPoint(){
            return this.matchFailPoint;
        }
        public void setMatchContinuity(int matchContinuity){
            this.matchContinuity = matchContinuity;
        }
        public int getMatchContinuity(){
            return this.matchContinuity;
        }
        public void setNo(int no){
            this.no = no;
        }
        public int getNo(){
            return this.no;
        }
        public void setFirstNum(int firstNum){
            this.firstNum = firstNum;
        }
        public int getFirstNum(){
            return this.firstNum;
        }
        public void setRankingObjList(List<RankingObjList> rankingObjList){
            this.rankingObjList = rankingObjList;
        }
        public List<RankingObjList> getRankingObjList(){
            return this.rankingObjList;
        }
        public void setUserAt(UserAt userAt){
            this.userAt = userAt;
        }
        public UserAt getUserAt(){
            return this.userAt;
        }

    }

    public static class UserAt implements Serializable{
        private int point;

        private int rank;

        public void setPoint(int point){
            this.point = point;
        }
        public int getPoint(){
            return this.point;
        }
        public void setRank(int rank){
            this.rank = rank;
        }
        public int getRank(){
            return this.rank;
        }

    }

    public static class RankingObjList implements Serializable{
        private int point;

        private int rank;

        public void setPoint(int point){
            this.point = point;
        }
        public int getPoint(){
            return this.point;
        }
        public void setRank(int rank){
            this.rank = rank;
        }
        public int getRank(){
            return this.rank;
        }

    }

}
