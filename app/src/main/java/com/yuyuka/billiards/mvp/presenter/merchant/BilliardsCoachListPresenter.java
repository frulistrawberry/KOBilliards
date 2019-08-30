package com.yuyuka.billiards.mvp.presenter.merchant;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuyuka.billiards.base.BasePresenter;
import com.yuyuka.billiards.mvp.contract.merchant.BilliardsCoachListContract;
import com.yuyuka.billiards.mvp.model.MerchantModel;
import com.yuyuka.billiards.net.RespObserver;
import com.yuyuka.billiards.pojo.BilliardsCoachPojo;
import com.yuyuka.billiards.utils.CollectionUtils;
import com.yuyuka.billiards.utils.RxUtils;

import java.lang.reflect.Type;
import java.util.List;

public class BilliardsCoachListPresenter extends BasePresenter<BilliardsCoachListContract.IBilliardsCoachListView, BilliardsCoachListContract.IBilliardsCoachListModel> {
    public BilliardsCoachListPresenter(BilliardsCoachListContract.IBilliardsCoachListView view) {
        super(view, new MerchantModel());
    }

    public void getBilliardsCoachList(int page){
        getView().showLoading();
        mModel.getBilliardsCoachList(page)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {

                    @Override
                    public void onResult(String msg, String bizContent) {
                        getView().hideLoading();
                        if (TextUtils.isEmpty(bizContent)){
                            getView().showEmpty();
                            return;
                        }
                        Type type = new TypeToken<List<BilliardsCoachPojo>>(){}.getType();
                        List<BilliardsCoachPojo> data = new Gson().fromJson(bizContent,type);

                        if (CollectionUtils.isEmpty(data))
                            getView().showEmpty();
                        else
                            getView().showBilliardsCoachList(data);
                    }

                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().hideLoading();
                        getView().showError(errMsg);
                    }
                });

    }
}
