package com.yuyuka.billiards.pojo;

import java.io.Serializable;
import java.util.List;

public class BilliardsGoods implements Serializable {
    private int id;
    private String goodsName;
    private double goodsAmount;
    private String created;
    private int billiardsId;
    private int isDelete;
    private int examine;
    private String goodsImage;
    private int goodsType;
    private String reserveRuleUuid;
    private String promoRuleUuid;
    private String costRuleUuid;
    private BilliardsCostRules billiardsCostRules;
    private BilliardsReserveRulesInfo billiardsReserveRulesInfo;
    private BilliardsPromotionRulesInfo billiardsPromotionRulesInfo;
    private int minPrice;
    private String goodsInfo;
    private BilliardsPoolTable billiardsPoolTable;
    private MatchDetailPojo.BilliardsInfo billiardsInfo;

    public MatchDetailPojo.BilliardsInfo getBilliardsInfo() {
        return billiardsInfo;
    }

    public BilliardsPoolTable getBilliardsPoolTable() {
        return billiardsPoolTable;
    }

    public String getGoodsInfo() {
        return goodsInfo;
    }

    public int getId() {
        return id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public double getGoodsAmount() {
        return goodsAmount;
    }

    public String getCreated() {
        return created;
    }

    public int getBilliardsId() {
        return billiardsId;
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

    public int getGoodsType() {
        return goodsType;
    }

    public String getReserveRuleUuid() {
        return reserveRuleUuid;
    }

    public String getPromoRuleUuid() {
        return promoRuleUuid;
    }

    public String getCostRuleUuid() {
        return costRuleUuid;
    }

    public BilliardsCostRules getBilliardsCostRules() {
        return billiardsCostRules;
    }

    public BilliardsReserveRulesInfo getBilliardsReserveRulesInfo() {
        return billiardsReserveRulesInfo;
    }

    public BilliardsPromotionRulesInfo getBilliardsPromotionRulesInfo() {
        return billiardsPromotionRulesInfo;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public static class BilliardsCostRules {
        private int id;
        private int hourPrice;
        private String created;
        private String uuid;
        private String rulesName;
        private int billiardsId;

        public int getId() {
            return id;
        }

        public int getHourPrice() {
            return hourPrice;
        }

        public String getCreated() {
            return created;
        }

        public String getUuid() {
            return uuid;
        }

        public String getRulesName() {
            return rulesName;
        }

        public int getBilliardsId() {
            return billiardsId;
        }
    }

    public static class BilliardsReserveRulesInfo{
        private int billiardsId;
        private String created;
        private String rulesName;
        private List<BilliardsReserveRulesList> billiardsReserveRulesList;

        public int getBilliardsId() {
            return billiardsId;
        }

        public String getCreated() {
            return created;
        }

        public String getRulesName() {
            return rulesName;
        }

        public List<BilliardsReserveRulesList> getBilliardsReserveRulesList() {
            return billiardsReserveRulesList;
        }
    }

    public static class BilliardsReserveRulesList implements Comparable<BilliardsReserveRulesList> {
        private int id;
        private int weekNum;
        private int isDelete;
        private int clock;
        private String reserveName;
        private int billiardsId;
        private String created;
        private String uuid;

        public int getId() {
            return id;
        }

        public int getWeekNum() {
            return weekNum;
        }

        public int getIsDelete() {
            return isDelete;
        }

        public int getClock() {
            return clock;
        }

        public String getReserveName() {
            return reserveName;
        }

        public int getBilliardsId() {
            return billiardsId;
        }

        public String getCreated() {
            return created;
        }

        public String getUuid() {
            return uuid;
        }

        @Override
        public int compareTo(BilliardsReserveRulesList billiardsReserveRulesList) {
            return billiardsReserveRulesList.clock-this.clock;
        }
    }

    public static class BilliardsPromotionRulesInfo{
        private String uuid;
        private int billiardsId;
        private String created;
        private String weekJson;
        private String weekChina;
        private String rulesName;
        private double promoPrice;

        public String getUuid() {
            return uuid;
        }

        public int getBilliardsId() {
            return billiardsId;
        }

        public String getCreated() {
            return created;
        }

        public String getWeekJson() {
            return weekJson;
        }

        public String getWeekChina() {
            return weekChina;
        }

        public String getRulesName() {
            return rulesName;
        }

        public double getPromoPrice() {
            return promoPrice;
        }
    }

    public static class  BilliardsPromotionRulesList implements Comparable<BilliardsPromotionRulesList>{
        private int week;
        private int clock;

        public int getWeek() {
            return week;
        }

        public int getClock() {
            return clock;
        }

        @Override
        public int compareTo(BilliardsPromotionRulesList billiardsPromotionRulesList) {
            return billiardsPromotionRulesList.clock-this.clock;
        }
    }

    public static class BilliardsPoolTable {
        long id;

        public long getId() {
            return id;
        }
    }

}
