package com.yuyuka.billiards.pojo;

public class OrderPojo {

    private int id;

    private int userId;

    private int appOrderId;

    private int payStatus;

    private double payAmount;

    private int payRefund;

    private int payChannel;

    private int status;

    private int creditPoint;

    private int payType;

    private OrderInfo orderInfo;

    private BilliardsMakeBattleOrderRef billiardsMakeBattleOrderRef;

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getAppOrderId() {
        return appOrderId;
    }

    public int getPayStatus() {
        return payStatus;
    }

    public double getPayAmount() {
        return payAmount;
    }

    public int getPayRefund() {
        return payRefund;
    }

    public int getPayChannel() {
        return payChannel;
    }

    public int getStatus() {
        return status;
    }

    public int getCreditPoint() {
        return creditPoint;
    }

    public int getPayType() {
        return payType;
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public BilliardsMakeBattleOrderRef getBilliardsMakeBattleOrderRef() {
        return billiardsMakeBattleOrderRef;
    }

    public static class OrderInfo {
        private String appId;

        private String timeStamp;

        private String nonceStr;

        private String prepayId;

        private String signType;

        private String paySign;

        public String getAppId() {
            return appId;
        }

        public String getTimeStamp() {
            return timeStamp;
        }

        public String getNonceStr() {
            return nonceStr;
        }

        public String getPrepayId() {
            return prepayId;
        }

        public String getSignType() {
            return signType;
        }

        public String getPaySign() {
            return paySign;
        }
    }

    public static class BilliardsMakeBattleOrderRef {
        private int id;

        private int userId1;

        private int battleType;

        private int status;

        private String beginDate;

        private String created;

        private int refOrderId;

        public int getId() {
            return id;
        }

        public int getUserId1() {
            return userId1;
        }

        public int getBattleType() {
            return battleType;
        }

        public int getStatus() {
            return status;
        }

        public String getBeginDate() {
            return beginDate;
        }

        public String getCreated() {
            return created;
        }

        public int getRefOrderId() {
            return refOrderId;
        }
    }

}
