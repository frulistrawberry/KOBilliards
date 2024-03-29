package com.yuyuka.billiards.mvp.presenter.market;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuyuka.billiards.base.BasePresenter;
import com.yuyuka.billiards.mvp.contract.market.GoodsListContract;
import com.yuyuka.billiards.mvp.model.MarketModel;
import com.yuyuka.billiards.net.RespObserver;
import com.yuyuka.billiards.pojo.GoodsPojo;
import com.yuyuka.billiards.pojo.ListData;
import com.yuyuka.billiards.utils.CollectionUtils;
import com.yuyuka.billiards.utils.RxUtils;

import java.lang.reflect.Type;

public class GoodsListPresenter extends BasePresenter<GoodsListContract.IGoodsListView, GoodsListContract.IGoodsListModel> {
    public GoodsListPresenter(GoodsListContract.IGoodsListView view) {
        super(view, new MarketModel());
    }


    public void getGoodsList(String keywords,double lat,double lng, int sortCondition, int typeCondition,
                             int quickCondition, int lowPrice,int highPrice,
                             int releaseTimeCondition,int otherCondition , int page){
        getView().showLoading();
        mModel.getGoodsList(keywords,lat,lng,sortCondition, typeCondition, quickCondition, lowPrice, highPrice, releaseTimeCondition, otherCondition, page)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {
                    @Override
                    public void onResult(String msg, String bizEntity) {
                        getView().hideLoading();
                        if (TextUtils.isEmpty(bizEntity)){
                            getView().showEmpty();
                            return;
                        }
                        Type type = new TypeToken<ListData<GoodsPojo>>(){}.getType();
                        ListData<GoodsPojo> data = new Gson().fromJson(bizEntity,type);

                        if (CollectionUtils.isEmpty(data.getDataList()))
                            getView().showEmpty();
                        else
                            getView().showGoodsList(data.getDataList());
                    }

                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().hideLoading();
                        getView().showError(errMsg);
                    }
                });
    }
}
