package com.yuyuka.billiards.mvp.presenter.match;

import android.app.Activity;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuyuka.billiards.base.BasePresenter;
import com.yuyuka.billiards.mvp.contract.match.TableContract;
import com.yuyuka.billiards.mvp.model.MatchModel;
import com.yuyuka.billiards.net.RespObserver;
import com.yuyuka.billiards.pojo.MatchBonusPojo;
import com.yuyuka.billiards.pojo.OrderPojo;
import com.yuyuka.billiards.pojo.TablePojo;
import com.yuyuka.billiards.ui.activity.match.TableActivity;
import com.yuyuka.billiards.utils.PayUtils;
import com.yuyuka.billiards.utils.RxUtils;

import java.lang.reflect.Type;
import java.util.List;

public class TablePresenter extends BasePresenter<TableContract.ITableView, TableContract.ITableModel> {
    public TablePresenter(TableContract.ITableView view) {
        super(view, new MatchModel());
    }

    public void getTableInfo(long tableId){
        getView().showProgressDialog();
        mModel.getTableInfo(tableId)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {

                    @Override
                    public void onResult(String msg, String bizContent) {
                        getView().dismissProgressDialog();
                        if (TextUtils.isEmpty(bizContent)){
                            return;
                        }
                        TablePojo data = new Gson().fromJson(bizContent,TablePojo.class);
                        getView().showTableInfo(data);

                    }

                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().dismissProgressDialog();
                        getView().showError(errMsg);
                    }
                });

    }

    public void beginMatch(int tableId,int matchType){
        getView().showLoading();

    }

    public void openTable(long tableId){
        getView().showLoading();
        mModel.getOrder(tableId)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {

                    @Override
                    public void onResult(String msg, String bizContent) {

                        getView().dismissProgressDialog();
                        OrderPojo orderPojo = new Gson().fromJson(bizContent,OrderPojo.class);
                        OrderPojo.OrderInfo orderInfo = orderPojo.getOrderInfo();
                        if (orderInfo!=null)
                            PayUtils.getInstance().wxPay((Activity) getView(),orderInfo.getPrepayId(),orderInfo.getNonceStr(),orderInfo.getTimeStamp(),orderInfo.getPaySign());

                    }

                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().dismissProgressDialog();
                        getView().showError(errMsg);
                    }
                });

    }

}
