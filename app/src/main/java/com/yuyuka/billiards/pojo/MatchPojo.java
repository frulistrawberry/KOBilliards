package com.yuyuka.billiards.pojo;

import java.io.Serializable;
import java.util.List;

public class MatchPojo implements Serializable {
    private int id;
    private int totalBonus;
    private String headImageAdd;
    private String matchName;
    private String beginDate;
    private String endDate;
    private String created;
    private int billiardsId;
    private String tableNums;
    private int signUp;
    private int status;
    private List billiardsMatchImagesList;
    private BilliardsInfo billiardsInfo;
    private int start;
    private int limit;

    public int getId() {
        return id;
    }

    public int getTotalBonus() {
        return totalBonus;
    }

    public String getHeadImageAdd() {
        return headImageAdd;
    }

    public String getMatchName() {
        return matchName;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getCreated() {
        return created;
    }

    public int getBilliardsId() {
        return billiardsId;
    }

    public String getTableNums() {
        return tableNums;
    }

    public int getSignUp() {
        return signUp;
    }

    public int getStatus() {
        return status;
    }

    public List getBilliardsMatchImagesList() {
        return billiardsMatchImagesList;
    }

    public BilliardsInfo getBilliardsInfo() {
        return billiardsInfo;
    }

    public int getStart() {
        return start;
    }

    public int getLimit() {
        return limit;
    }

    public class BilliardsInfo {
        private int billiardsId;
        private double positionLongitude;
        private double positionLatitude;
        private String billiardsName;
        private int billLevel;
        private String traffic;
        private String policy;
        private String businessDate;
        private String position;
        private int cityId;
        private String phoneNumber;
        private String created;
        private int start;
        private int limit;

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

        public int getBillLevel() {
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

        public int getStart() {
            return start;
        }

        public int getLimit() {
            return limit;
        }
    }

}
