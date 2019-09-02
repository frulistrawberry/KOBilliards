package com.yuyuka.billiards.net;

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
    private Map<String,Object> parms;
    private Map<String,Object> order;

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


}
