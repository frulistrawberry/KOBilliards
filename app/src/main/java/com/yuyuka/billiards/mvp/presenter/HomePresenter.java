package com.yuyuka.billiards.mvp.presenter;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuyuka.billiards.base.BasePresenter;
import com.yuyuka.billiards.mvp.contract.HomeContract;
import com.yuyuka.billiards.mvp.model.HomeModel;
import com.yuyuka.billiards.net.RespObserver;
import com.yuyuka.billiards.pojo.CustomNoticePojo;
import com.yuyuka.billiards.pojo.ListData;
import com.yuyuka.billiards.pojo.NewsItem;
import com.yuyuka.billiards.utils.CollectionUtils;
import com.yuyuka.billiards.utils.RxUtils;

import java.lang.reflect.Type;
import java.util.List;

public class HomePresenter extends BasePresenter<HomeContract.IHomeView, HomeContract.IHomeModel> {

    public HomePresenter(HomeContract.IHomeView view) {
        super(view, new HomeModel());
    }

    public void getBattle(){
        mModel.getBattle()
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {

                    @Override
                    public void onResult(String msg, String bizContent) {
                        getView().dismissProgressDialog();
                        if (TextUtils.isEmpty(bizContent)){
                            return;
                        }

                        Type type = new TypeToken<List<CustomNoticePojo.Battle>>(){}.getType();
                        List<CustomNoticePojo.Battle> data = new Gson().fromJson(bizContent,type);
                        getView().showTable(data);


                    }

                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().showError(errMsg);
                    }
                });


    }

    public void myTable(){
        mModel.myTable()
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
                        getView().showError(errMsg);
                    }
                });

    }
}
