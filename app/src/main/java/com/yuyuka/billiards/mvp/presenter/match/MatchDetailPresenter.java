package com.yuyuka.billiards.mvp.presenter.match;

import android.os.Handler;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuyuka.billiards.base.BasePresenter;
import com.yuyuka.billiards.mvp.contract.match.MatchDetailContract;
import com.yuyuka.billiards.mvp.model.MatchModel;
import com.yuyuka.billiards.net.RespObserver;
import com.yuyuka.billiards.pojo.BilliardsGoods;
import com.yuyuka.billiards.pojo.MatchBonusPojo;
import com.yuyuka.billiards.pojo.MatchDetailPojo;
import com.yuyuka.billiards.utils.RxUtils;

import java.lang.reflect.Type;
import java.util.List;

public class MatchDetailPresenter extends BasePresenter<MatchDetailContract.IMatchDetailView, MatchDetailContract.IMatchDetailModel> {

    private Handler mHandler;
    private int requestCount = 0;
    public MatchDetailPresenter(MatchDetailContract.IMatchDetailView view) {
        super(view, new MatchModel());
        mHandler = new Handler(msg -> {
            requestCount++;
            if (requestCount == 1){
                getView().hideLoading();
            }
            return false;
        });

    }


    public void getMatchBonus(String matchId,boolean alone){
        getView().showLoading();
        if (alone)
            requestCount = 0;
        mModel.getMatchBonus(matchId)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {

                    @Override
                    public void onResult(String msg, String bizContent) {
                        if (TextUtils.isEmpty(bizContent)){
                            getView().showEmpty();
                            return;
                        }
                        Type type = new TypeToken<List<MatchBonusPojo>>(){}.getType();
                        List<MatchBonusPojo> data = new Gson().fromJson(bizContent,type);
                        getView().showMatchBonus(data);
                        if (alone)
                            getView().hideLoading();
                        else {
                            mHandler.sendEmptyMessage(0);
                        }
                    }

                    @Override
                    public void onError(int errCode, String errMsg) {
                        if (alone)
                            getView().hideLoading();
                        else
                            mHandler.sendEmptyMessage(0);
                        getView().showError(errMsg);
                    }
                });
    }

    public void getMatchDetail(String matchId,boolean alone){
        getView().showLoading();
        if (alone)
            requestCount = 0;
        mModel.getMatchDetail(matchId)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {

                    @Override
                    public void onResult(String msg, String bizContent) {
                        if (TextUtils.isEmpty(bizContent)){
                            getView().showEmpty();
                            return;
                        }
                        MatchDetailPojo data = new Gson().fromJson(bizContent,MatchDetailPojo.class);
                        getView().showMatchDetail(data);
                        if (alone)
                            getView().hideLoading();
                        else {
                            mHandler.sendEmptyMessage(0);
                        }
                    }

                    @Override
                    public void onError(int errCode, String errMsg) {
                        if (alone)
                            getView().hideLoading();
                        else
                            mHandler.sendEmptyMessage(0);
                        getView().showError(errMsg);
                    }
                });
    }
}
