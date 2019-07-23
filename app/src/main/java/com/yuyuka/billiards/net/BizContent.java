package com.yuyuka.billiards.net;

public class BizContent {

    private String mobile;
    private PageQueryDto pageQueryDto;
    private Double latitude;
    private Double longitude;
    private Double positionLatitude;
    private Double positionLongitude;
    private Integer status;
    private Integer cityId;

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
