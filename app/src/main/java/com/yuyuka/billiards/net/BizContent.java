package com.yuyuka.billiards.net;

import java.util.List;
import java.util.Map;

public class BizContent {

    private String mobile;
    private PageQueryDto pageQueryDto;
    private Double latitude;
    private Double longitude;
    private Double positionLatitude;
    private Double positionLongitude;
    private Integer status;
    private Integer cityId;
    private Integer weekNum;
    private String billiardsInfoId;
    private Integer modeType;
    private Integer typeId;
    private Integer queryType;
    private String keyword;
    private Integer billiardsId;
    private Map<String,Object> parms;
    private Map<String,Object> order;
    private StarClass starClass;
    private List<String> gameTypeList;
    private String messageInfo;
    private Integer userId;
    private Integer matchInfoId;
    private Integer id;
    private Integer billiardsSecondMallId;
    private String content;
    private Integer consultationId;
    private Integer followId;
    private Integer bizId;
    private Integer bizType;
    private Integer messageId;
    private String replyContent;
    private Integer consultationType;
    private Integer viewLongtime;
    private String address;
    private String title;
    private String coverImageAdd;
    private String contentInfo;
    private String billiardsConsultationTagDtoIds;
    private Integer collectionsType;
    private Long poolTableId;
    private Integer orderType;
    private Long billiardsPoolTable;
    private Integer competitionType;
    private String smsCode;
    private BilliardsMakeAppOrderInfo billiardsMakeAppOrderInfo;

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public void setBilliardsPoolTable(Long billiardsPoolTable) {
        this.billiardsPoolTable = billiardsPoolTable;
    }

    public void setCompetitionType(Integer competitionType) {
        this.competitionType = competitionType;
    }

    public void setBilliardsMakeAppOrderInfo(BilliardsMakeAppOrderInfo billiardsMakeAppOrderInfo) {
        this.billiardsMakeAppOrderInfo = billiardsMakeAppOrderInfo;
    }

    public void setPoolTableId(Long poolTableId) {
        this.poolTableId = poolTableId;
    }

    public void setCollectionsType(Integer collectionsType) {
        this.collectionsType = collectionsType;
    }

    public void setConsultationType(Integer consultationType) {
        this.consultationType = consultationType;
    }

    public void setViewLongtime(Integer viewLongtime) {
        this.viewLongtime = viewLongtime;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCoverImageAdd(String coverImageAdd) {
        this.coverImageAdd = coverImageAdd;
    }

    public void setContentInfo(String contentInfo) {
        this.contentInfo = contentInfo;
    }

    public void setBilliardsConsultationTagDtoIds(String billiardsConsultationTagDtoIds) {
        this.billiardsConsultationTagDtoIds = billiardsConsultationTagDtoIds;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public void setBilliardsId(Integer billiardsId) {
        this.billiardsId = billiardsId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public void setBizId(Integer bizId) {
        this.bizId = bizId;
    }

    public void setBizType(Integer bizType) {
        this.bizType = bizType;
    }

    public void setFollowId(Integer followId) {
        this.followId = followId;
    }

    public void setConsultationId(Integer consultationId) {
        this.consultationId = consultationId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setBilliardsSecondMallId(Integer billiardsSecondMallId) {
        this.billiardsSecondMallId = billiardsSecondMallId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMatchInfoId(Integer matchInfoId) {
        this.matchInfoId = matchInfoId;
    }

    public void setMessageInfo(String messageInfo) {
        this.messageInfo = messageInfo;
    }

    public void setGameTypeList(List<String> gameTypeList) {
        this.gameTypeList = gameTypeList;
    }

    public void setStarClass(StarClass starClass) {
        this.starClass = starClass;
    }

    public void setBilliardsId(String billiardsId) {
        this.billiardsId = Integer.valueOf(billiardsId);
    }

    public void setParms(Map<String, Object> parms) {
        this.parms = parms;
    }

    public void setOrder(Map<String, Object> order) {
        this.order = order;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setParams(Map<String, Object> params) {
        this.parms = params;
    }

    public void setQueryType(Integer queryType) {
        this.queryType = queryType;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public void setModeType(Integer modeType) {
        this.modeType = modeType;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public void setPageQueryDto(PageQueryDto pageQueryDto) {
        this.pageQueryDto = pageQueryDto;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setWeekNum(Integer weekNum) {
        this.weekNum = weekNum;
    }

    public void setBilliardsInfoId(String billiardsInfoId) {
        this.billiardsInfoId = billiardsInfoId;
    }

    public void setPositionLatitude(Double positionLatitude) {
        this.positionLatitude = positionLatitude;
    }

    public void setPositionLongitude(Double positionLongitude) {
        this.positionLongitude = positionLongitude;
    }

    public void buildPageQueryDto(int start, int limit) {
        this.pageQueryDto = new PageQueryDto(start,limit);
    }

    public void buildPageQueryDto(int start) {
        this.pageQueryDto = new PageQueryDto(start,10);
    }

    public static class PageQueryDto{
        private Integer start;
        private Integer limit;

        public PageQueryDto(Integer start, Integer limit) {
            this.start = start;
            this.limit = limit;
        }
    }

    public static class StarClass{
        private int population;
        private int local;
        private int service;
        private int hygiene;
        private int facilities;

        public void setPopulation(int population) {
            this.population = population;
        }

        public void setLocal(int local) {
            this.local = local;
        }

        public void setService(int service) {
            this.service = service;
        }

        public void setHygiene(int hygiene) {
            this.hygiene = hygiene;
        }

        public void setFacilities(int facilities) {
            this.facilities = facilities;
        }
    }

    public static class BilliardsMakeAppOrderInfo{
        Integer userId;
        Integer payType;
        Integer payChannel;

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public void setPayType(Integer payType) {
            this.payType = payType;
        }

        public void setPayChannel(Integer payChannel) {
            this.payChannel = payChannel;
        }
    }


}
