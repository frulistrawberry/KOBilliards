package com.yuyuka.billiards.pojo;

public class TablePojo {
    private int id;

    private String typeName;

    private String tableName;

    private String promoRuleUuid;

    private int billiardsId;

    private double deoisitPrice;

    private String tableBrand;

    private String tableType;

    private String tableModel;

    private String sinkType;

    private String outerSize;

    private String interSize;

    private String created;

    private int tableNum;

    private int status;

    private int goodsId;

    private double amount;

    private int typeValue;

    private BilliardsGoods billiardsGoods;

    private BilliardsRoomPojo billiardsInfo;

    private BilliardsGoods.BilliardsCostRules billiardsCostRules;

    private BilliardsGoods.BilliardsPromotionRulesInfo billiardsPromotionRulesInfo;

    public int getId() {
        return id;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getTableName() {
        return tableName;
    }

    public String getPromoRuleUuid() {
        return promoRuleUuid;
    }

    public int getBilliardsId() {
        return billiardsId;
    }

    public double getDeoisitPrice() {
        return deoisitPrice;
    }

    public String getTableBrand() {
        return tableBrand;
    }

    public String getTableType() {
        return tableType;
    }

    public String getTableModel() {
        return tableModel;
    }

    public String getSinkType() {
        return sinkType;
    }

    public String getOuterSize() {
        return outerSize;
    }

    public String getInterSize() {
        return interSize;
    }

    public String getCreated() {
        return created;
    }

    public int getTableNum() {
        return tableNum;
    }

    public int getStatus() {
        return status;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public double getAmount() {
        return amount;
    }

    public int getTypeValue() {
        return typeValue;
    }

    public BilliardsGoods getBilliardsGoods() {
        return billiardsGoods;
    }

    public BilliardsRoomPojo getBilliardsInfo() {
        return billiardsInfo;
    }

    public BilliardsGoods.BilliardsCostRules getBilliardsCostRules() {
        return billiardsCostRules;
    }

    public BilliardsGoods.BilliardsPromotionRulesInfo getBilliardsPromotionRulesInfo() {
        return billiardsPromotionRulesInfo;
    }
}
