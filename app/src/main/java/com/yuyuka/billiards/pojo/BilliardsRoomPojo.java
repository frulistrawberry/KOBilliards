package com.yuyuka.billiards.pojo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class BilliardsRoomPojo implements Serializable {
    private String id;
    private int billiardsId;
    private double positionLongitude;
    private double positionLatitude;
    private String billiardsName;
    private long billLevel;
    private String traffic;
    private String policy;
    private String businessDate;
    private String position;
    private int cityId;
    private String phoneNumber;
    private String created;
    private int examine;
    private String headImage;
    private int appraiseCount;
    private int doBusiness;
    private int start;
    private int limit;
    private Map<String,String> tag;
    private List<ImagePojo> billiardsImages;
    private BilliardsUser billiardsUsers;
    private int minimumPayment;

    public String getId() {
        return id;
    }

    public int getMinimumPayment() {
        return minimumPayment;
    }

    public int getBilliardsId() {
        return billiardsId;
    }

    public double getPositionLongitude() {
        return positionLongitude;
    }

    public double getPositionLatitude() {
        return positionLatitude;
    }

    public String getBilliardsName() {
        return billiardsName;
    }

    public long getBillLevel() {
        return billLevel;
    }

    public String getTraffic() {
        return traffic;
    }

    public String getPolicy() {
        return policy;
    }

    public String getBusinessDate() {
        return businessDate;
    }

    public String getPosition() {
        return position;
    }

    public int getCityId() {
        return cityId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCreated() {
        return created;
    }

    public int getExamine() {
        return examine;
    }

    public String getHeadImage() {
        return headImage;
    }

    public int getStart() {
        return start;
    }

    public int getLimit() {
        return limit;
    }

    public int getAppraiseCount() {
        return appraiseCount;
    }

    public int getDoBusiness() {
        return doBusiness;
    }

    public Map<String, String> getTag() {
        return tag;
    }

    public List<ImagePojo> getBilliardsImages() {
        return billiardsImages;
    }

    public BilliardsUser getBilliardsUsers() {
        return billiardsUsers;
    }

    public static class BilliardsImage{
        private int id;
        private int billiardsId;
        private int imageType;
        private String address;

        public int getId() {
            return id;
        }

        public int getBilliardsId() {
            return billiardsId;
        }

        public int getImageType() {
            return imageType;
        }

        public String getAddress() {
            return address;
        }
    }

    public static class BilliardsUser{
        private String headImage;
        private String created;

        public String getHeadImage() {
            return headImage;
        }

        public String getCreated() {
            return created;
        }
    }


}
