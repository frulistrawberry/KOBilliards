package com.kobilliards.mvp.presenter.nearbyroom;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kobilliards.base.BasePresenter;
import com.kobilliards.mvp.contract.rearbyroom.RecommendRoomContract;
import com.kobilliards.mvp.model.nearbyroom.RecommendRoomModel;
import com.kobilliards.net.RespObserver;
import com.kobilliards.pojo.BilliardsRoomPojo;
import com.kobilliards.utils.CollectionUtils;
import com.kobilliards.utils.RxUtils;

import java.lang.reflect.Type;
import java.util.List;

public class RecommendRoomPresenter extends BasePresenter<RecommendRoomContract.IRecommendRoomView, RecommendRoomContract.IRecommendRoomModel> {

    public RecommendRoomPresenter(RecommendRoomContract.IRecommendRoomView view) {
        super(view,new RecommendRoomModel());
    }

    public void getRecommendRoomList(String sort,String filter,int page){
        getView().showLoading();
        mModel.getRecommendRoomList(page,sort,filter)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {

                    @Override
                    public void onResult(String msg, String bizContent) {
                        getView().hideLoading();
                        if (TextUtils.isEmpty(bizContent)){
                            getView().showEmpty();
                            return;
                        }
//                        Type type = new TypeToken<List<BilliardsRoomPojo>>(){}.getType();
//                        List<BilliardsRoomPojo> data = new Gson().fromJson(bizContent,type);
//
//                        if (CollectionUtils.isEmpty(data))
//                            getView().showEmpty();
//                        else
//                            getView().showRecommendRoomList(data);
                    }

                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().hideLoading();
                        getView().showError(errMsg);
                    }
                });

    }
}
