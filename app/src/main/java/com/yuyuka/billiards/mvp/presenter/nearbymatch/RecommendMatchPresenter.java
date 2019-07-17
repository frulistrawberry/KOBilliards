package com.yuyuka.billiards.mvp.presenter.nearbymatch;


import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuyuka.billiards.base.BasePresenter;
import com.yuyuka.billiards.mvp.contract.nearbymatch.RecommendMatchContract;
import com.yuyuka.billiards.mvp.model.nearbymatch.RecommendMatchModel;
import com.yuyuka.billiards.net.RespObserver;
import com.yuyuka.billiards.pojo.ListData;
import com.yuyuka.billiards.pojo.MatchPojo;
import com.yuyuka.billiards.utils.CollectionUtils;
import com.yuyuka.billiards.utils.RxUtils;

import java.lang.reflect.Type;


public class RecommendMatchPresenter extends BasePresenter<RecommendMatchContract.IRecommendMatchView, RecommendMatchContract.IRecommendMatchModel> {

    public RecommendMatchPresenter(RecommendMatchContract.IRecommendMatchView view) {
        super(view,new RecommendMatchModel());
    }

    public void getRecommendMatchList(double lat,double lng,int status,int page){
        getView().showLoading();
        mModel.getRecommendMatchList(lat,lng,status,page)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {

                    @Override
                    public void onResult(String msg, String bizContent) {
                        getView().hideLoading();

                        if (TextUtils.isEmpty(bizContent)){
                            getView().showEmpty();
                            return;
                        }
                        Type type = new TypeToken<ListData<MatchPojo>>(){}.getType();
                        ListData<MatchPojo> data = new Gson().fromJson(bizContent,type);

                        if (CollectionUtils.isEmpty(data.getDataList()))
                            getView().showEmpty();
                        else
                            getView().showRecommendMatchList(data.getDataList());
                    }

                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().hideLoading();
                        getView().showError(errMsg);
                    }
                });

    }
}