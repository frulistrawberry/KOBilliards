package com.yuyuka.billiards.pojo;

import java.io.Serializable;

public class TaoCan implements Serializable {
    private String mealName;
    private String mealInfo;
    private double mealAmount;
    private int id;

    public int getId() {
        return id;
    }

    public String getMealName() {
        return mealName;
    }

    public String getMealInfo() {
        return mealInfo;
    }

    public double getMealAmount() {
        return mealAmount;
    }
}
