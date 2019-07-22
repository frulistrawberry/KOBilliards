package com.yuyuka.billiards.mvp.presenter.room;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuyuka.billiards.base.BasePresenter;
import com.yuyuka.billiards.mvp.contract.room.RecommendRoomContract;
import com.yuyuka.billiards.mvp.model.room.RecommendRoomModel;
import com.yuyuka.billiards.net.RespObserver;
import com.yuyuka.billiards.pojo.BilliardsRoomPojo;
import com.yuyuka.billiards.pojo.ListData;
import com.yuyuka.billiards.utils.CollectionUtils;
import com.yuyuka.billiards.utils.RxUtils;

import java.lang.reflect.Type;

public class RecommendRoomPresenter extends BasePresenter<RecommendRoomContract.IRecommendRoomView, RecommendRoomContract.IRecommendRoomModel> {

    public RecommendRoomPresenter(RecommendRoomContract.IRecommendRoomView view) {
        super(view,new RecommendRoomModel());
    }

    public void getRecommendRoomList(double lat,double lng,int page){
        getView().showLoading();
        mModel.getRecommendRoomList(lat,lng,page)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {

                    @Override
                    public void onResult(String msg, String bizContent) {
                        getView().hideLoading();
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
                    }

                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().hideLoading();
                        getView().showError(errMsg);
                    }
                });

    }
}
