package com.yuyuka.billiards.mvp.presenter.merchant;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuyuka.billiards.base.BasePresenter;
import com.yuyuka.billiards.mvp.contract.merchant.RecommendRoomContract;
import com.yuyuka.billiards.mvp.model.MerchantModel;
import com.yuyuka.billiards.net.RespObserver;
import com.yuyuka.billiards.pojo.BilliardsRoomPojo;
import com.yuyuka.billiards.pojo.ListData;
import com.yuyuka.billiards.utils.CollectionUtils;
import com.yuyuka.billiards.utils.RxUtils;

import java.lang.reflect.Type;

public class RecommendRoomPresenter extends BasePresenter<RecommendRoomContract.IRecommendRoomView, RecommendRoomContract.IRecommendRoomModel> {

    public RecommendRoomPresenter(RecommendRoomContract.IRecommendRoomView view) {
        super(view,new MerchantModel());
    }

    public void getRecommendRoomList(String keywords,double lat,double lng,int sortCondition, int page){
        getView().showLoading();
        mModel.getRecommendRoomList(keywords,lat,lng,sortCondition,page)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {

                    @Override
                    public void onResult(String msg, String bizContent) {
                        if (TextUtils.isEmpty(bizContent)){
                            getView().showEmpty();
                            return;
                        }
                        Type type = new TypeToken<ListData<BilliardsRoomPojo>>(){}.getType();
                        ListData<BilliardsRoomPojo> data = new Gson().fromJson(bizContent,type);

                        if (CollectionUtils.isEmpty(data.getDataList()))
                            getView().showEmpty();
                        else
                            getView().showRecommendRoomList(data.getDataList());
                        getView().hideLoading();

                    }

                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().hideLoading();
                        getView().showError(errMsg);
                    }
                });

    }
}
