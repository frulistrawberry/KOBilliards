package com.yuyuka.billiards.mvp.presenter.table;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.yuyuka.billiards.base.BasePresenter;
import com.yuyuka.billiards.mvp.contract.table.PointContract;
import com.yuyuka.billiards.mvp.model.TableModel;
import com.yuyuka.billiards.net.RespObserver;
import com.yuyuka.billiards.pojo.OrderPojo;
import com.yuyuka.billiards.utils.RxUtils;

public class PointPresenter extends BasePresenter<PointContract.IPointView, PointContract.IPointModel> {

    public PointPresenter(PointContract.IPointView view) {
        super(view, new TableModel());
    }

    public void sendPoint(int id,int userId,int point){
        getView().showProgressDialog();
        mModel.sendPoint(id,userId,point)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {

                    @Override
                    public void onResult(String msg, String bizContent) {
                        getView().dismissProgressDialog();
                        if (TextUtils.isEmpty(bizContent)){
                            return;
                        }


                    }

                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().dismissProgressDialog();
                        getView().showError(errMsg);
                    }
                });
    }

    public void confirmPoint(int id){
        getView().showProgressDialog();
        mModel.confirm(id)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {

                    @Override
                    public void onResult(String msg, String bizContent) {
                        getView().dismissProgressDialog();
                        if (TextUtils.isEmpty(bizContent)){
                            return;
                        }


                    }

                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().dismissProgressDialog();
                        getView().showError(errMsg);
                    }
                });
    }
}
