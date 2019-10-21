package com.yuyuka.billiards.mvp.presenter.merchant;

import android.os.Handler;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuyuka.billiards.base.BasePresenter;
import com.yuyuka.billiards.mvp.contract.merchant.RoomDetailContract;
import com.yuyuka.billiards.mvp.model.MerchantModel;
import com.yuyuka.billiards.net.RespObserver;
import com.yuyuka.billiards.pojo.BilliardsGoods;
import com.yuyuka.billiards.pojo.BilliardsRoomPojo;
import com.yuyuka.billiards.pojo.OrderPojo;
import com.yuyuka.billiards.utils.RxUtils;
import com.yuyuka.billiards.utils.log.LogUtil;

import java.lang.reflect.Type;
import java.util.List;

public class RoomDetailPresenter extends BasePresenter<RoomDetailContract.IRoomDetailView, RoomDetailContract.IRoomDetailModel> {

    public RoomDetailPresenter(RoomDetailContract.IRoomDetailView view) {
        super(view, new MerchantModel());


    }

    public void getMerchantInfo(String billiardsId){
        getView().showLoading();
        mModel.getRoomInfo(billiardsId)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {

                    @Override
                    public void onResult(String msg, String bizContent) {
                        if (TextUtils.isEmpty(bizContent)){
                            getView().showEmpty();
                            return;
                        }
                        BilliardsRoomPojo data = new Gson().fromJson(bizContent,BilliardsRoomPojo.class);
                        getView().showRoomInfo(data);
                            getView().hideLoading();
                    }

                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().hideLoading();
                        getView().showError(errMsg);
                    }
                });
    }

    public void getGoodsInfo(String billiardsId,int weekNum){
        getView().showLoading();
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
                        getView().hideLoading();
                    }

                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().hideLoading();
                        getView().showError(errMsg);
                    }
                });
    }

    public void pairse(int merchantId){

    }

    public void collect(int merchantId){
        getView().showProgressDialog();
        mModel.collect(merchantId)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {

                    @Override
                    public void onResult(String msg, String bizContent) {
                       getView().dismissProgressDialog();
                       getView().showCollectSuccess(msg);
                    }

                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().dismissProgressDialog();
                        getView().showError(errMsg);
                    }
                });
    }

    public void testRevert(int setMealId,String remark,String beginDate,String endDate,int billiardsGood){
        getView().showProgressDialog();
        mModel.tackOrder(setMealId,remark,beginDate,endDate,billiardsGood)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {

                    @Override
                    public void onResult(String msg, String bizContent) {
                        getView().dismissProgressDialog();
                        if (TextUtils.isEmpty(bizContent)){
                            return;
                        }
                        OrderPojo data = new Gson().fromJson(bizContent,OrderPojo.class);
                        getView().showOrderSuccess(data);

                    }

                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().dismissProgressDialog();
                        getView().showError(errMsg);
                    }
                });
    }
}
