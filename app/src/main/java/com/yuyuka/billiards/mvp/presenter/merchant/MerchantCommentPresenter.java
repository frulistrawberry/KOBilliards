package com.yuyuka.billiards.mvp.presenter.merchant;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuyuka.billiards.base.BasePresenter;
import com.yuyuka.billiards.mvp.contract.merchant.MerchantCommentContract;
import com.yuyuka.billiards.mvp.model.MerchantModel;
import com.yuyuka.billiards.net.RespObserver;
import com.yuyuka.billiards.pojo.TaoCan;
import com.yuyuka.billiards.utils.RxUtils;

import java.lang.reflect.Type;
import java.util.List;

public class MerchantCommentPresenter extends BasePresenter<MerchantCommentContract.IMerchantCommentView, MerchantCommentContract.IMerchantCommentModel> {
    public MerchantCommentPresenter(MerchantCommentContract.IMerchantCommentView view) {
        super(view, new MerchantModel());
    }

    public void  comment(String billiardsId, String messageInfo, List<String> gameTypeList, int population, int local, int service, int hygiene, int facilities){
        getView().showLoading();
        mModel.comment(billiardsId,messageInfo,gameTypeList,population,local,service,hygiene,facilities)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {

                    @Override
                    public void onResult(String msg, String bizContent) {
                        if (TextUtils.isEmpty(bizContent)){
                            getView().showEmpty();
                            return;
                        }
                        getView().hideLoading();
                        getView().showCommentSuccess();

                    }

                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().hideLoading();
                        getView().showError(errMsg);
                    }
                });
    }
}
