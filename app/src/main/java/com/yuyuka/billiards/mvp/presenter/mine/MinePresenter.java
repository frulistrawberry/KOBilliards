package com.yuyuka.billiards.mvp.presenter.mine;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuyuka.billiards.base.BasePresenter;
import com.yuyuka.billiards.mvp.contract.mine.MineContract;
import com.yuyuka.billiards.mvp.model.MineModel;
import com.yuyuka.billiards.net.RespObserver;
import com.yuyuka.billiards.pojo.CustomNoticePojo;
import com.yuyuka.billiards.pojo.MinePojo;
import com.yuyuka.billiards.utils.RxUtils;

import java.lang.reflect.Type;
import java.util.List;

public class MinePresenter extends BasePresenter<MineContract.IMineView, MineContract.IMinewModel> {
    public MinePresenter(MineContract.IMineView view) {
        super(view, new MineModel());
    }

    public void getMineInfo(){
        getView().showLoading();
        mModel.getMineData()
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {

                        @Override
                        public void onResult(String msg, String bizContent) {
                            getView().hideLoading();
                            if (TextUtils.isEmpty(bizContent)){
                                return;
                            }
                            MinePojo data = new Gson().fromJson(bizContent,MinePojo.class);
                            getView().showMineData(data);


                        }

                        @Override
                        public void onError(int errCode, String errMsg) {
                            getView().hideLoading();
                            getView().showError(errMsg);
                        }
                    });

    }
}
