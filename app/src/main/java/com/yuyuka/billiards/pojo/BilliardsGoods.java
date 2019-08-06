package com.yuyuka.billiards.pojo;

import java.io.Serializable;
import java.util.List;

public class BilliardsGoods implements Serializable {
    private int id;
    private String goodsName;
    private int goodsAmount;
    private String created;
    private int billiardsId;
    private String goodsInfo;
    private int isDelete;
    private int examine;
    private String goodsImage;
    private List<BilliardsPromotionList> billiardsPromotionList;
    private List<BilliardsGoodsScheduledTimeDtoList> billiardsGoodsScheduledTimeDtoList;

    public int getId() {
        return id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public int getGoodsAmount() {
        return goodsAmount;
    }

    public String getCreated() {
        return created;
    }

    public int getBilliardsId() {
        return billiardsId;
    }

    public String getGoodsInfo() {
        return goodsInfo;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public int getExamine() {
        return examine;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public List<BilliardsPromotionList> getBilliardsPromotionList() {
        return billiardsPromotionList;
    }

    public List<BilliardsGoodsScheduledTimeDtoList> getBilliardsGoodsScheduledTimeDtoList() {
        return billiardsGoodsScheduledTimeDtoList;
    }

    public static class BilliardsPromotionList {
        private int id;
        private int weekNum;
        private int proType;
        private int discount;
        private int price;
        private int goodsId;
        private int clock;
        private long amount;

        public int getId() {
            return id;
        }

        public int getWeekNum() {
            return weekNum;
        }

        public int getProType() {
            return proType;
        }

        public int getDiscount() {
            return discount;
        }

        public int getPrice() {
            return price;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public int getClock() {
            return clock;
        }

        public long getAmount() {
            return amount;
        }
    }

    public class BilliardsGoodsScheduledTimeDtoList {
        private int id;
        private int week;
        private int clock;
        private int goodsId;

        public int getId() {
            return id;
        }

        public int getWeek() {
            return week;
        }

        public int getClock() {
            return clock;
        }

        public int getGoodsId() {
            return goodsId;
        }
    }
}
