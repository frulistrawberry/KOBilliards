package com.yuyuka.billiards.mvp.model;

import com.google.gson.Gson;
import com.yuyuka.billiards.base.BaseModel;
import com.yuyuka.billiards.mvp.contract.market.GoodsListContract;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.pojo.GoodsPojo;
import com.yuyuka.billiards.pojo.ListData;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class MarketModel extends BaseModel implements GoodsListContract.IGoodsListModel {

    @Override
    public Observable<HttpResult> getGoodsList(String keywords, int sortCondition, int typeCondition, int quickCondition, int lowPrice, int highPrice, int releaseTimeCondition, int otherCondition, int page) {
        return null;
    }
}
