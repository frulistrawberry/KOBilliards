package com.yuyuka.billiards.mvp.presenter.room;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuyuka.billiards.base.BasePresenter;
import com.yuyuka.billiards.mvp.contract.room.RoomDetailContract;
import com.yuyuka.billiards.mvp.model.RoomModel;
import com.yuyuka.billiards.net.RespObserver;
import com.yuyuka.billiards.pojo.BilliardsGoods;
import com.yuyuka.billiards.utils.RxUtils;

import java.lang.reflect.Type;
import java.util.List;

public class RoomDetailPresenter extends BasePresenter<RoomDetailContract.IRoomDetailView, RoomDetailContract.IRoomDetailModel> {

    private Handler mHandler;
    private int requestCount = 0;
    public RoomDetailPresenter(RoomDetailContract.IRoomDetailView view) {
        super(view, new RoomModel());
        mHandler = new Handler(msg -> {
            requestCount++;
            if (requestCount == 1){
                getView().hideLoading();
            }
            return false;
        });

    }

    public void getGoodsInfo(int billiardsId,int weekNum,boolean alone){
        getView().showLoading();
        if (alone)
            requestCount = 0;
        mModel.getGoodsInfo(billiardsId,weekNum)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {

                    @Override
                    public void onResult(String msg, String bizContent) {
                        if (TextUtils.isEmpty(bizContent)){
                            getView().showEmpty();
                            return;
                        }
                        Type type = new TypeToken<List<BilliardsGoods>>(){}.getType();
                        List<BilliardsGoods> data = new Gson().fromJson(bizContent,type);
                        getView().showGoodsInfo(data);
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
