package com.yuyuka.billiards.mvp.presenter.match;


import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuyuka.billiards.base.BasePresenter;
import com.yuyuka.billiards.mvp.contract.match.RecommendMatchContract;
import com.yuyuka.billiards.mvp.model.MatchModel;
import com.yuyuka.billiards.net.RespObserver;
import com.yuyuka.billiards.pojo.ListData;
import com.yuyuka.billiards.pojo.BilliardsMatchPojo;
import com.yuyuka.billiards.utils.CollectionUtils;
import com.yuyuka.billiards.utils.RxUtils;

import java.lang.reflect.Type;


public class RecommendMatchPresenter extends BasePresenter<RecommendMatchContract.IRecommendMatchView, RecommendMatchContract.IRecommendMatchModel> {

    public RecommendMatchPresenter(RecommendMatchContract.IRecommendMatchView view) {
        super(view,new MatchModel());
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
                        Type type = new TypeToken<ListData<BilliardsMatchPojo>>(){}.getType();
                        ListData<BilliardsMatchPojo> data = new Gson().fromJson(bizContent,type);

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
