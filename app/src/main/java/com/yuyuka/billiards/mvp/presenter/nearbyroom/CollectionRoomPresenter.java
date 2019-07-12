package com.yuyuka.billiards.mvp.presenter.nearbyroom;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuyuka.billiards.base.BasePresenter;
import com.yuyuka.billiards.mvp.contract.rearbyroom.CollectionRoomContract;
import com.yuyuka.billiards.mvp.model.nearbyroom.CollectionRoomModel;
import com.yuyuka.billiards.net.RespObserver;
import com.yuyuka.billiards.pojo.BilliardsRoomPojo;
import com.yuyuka.billiards.utils.CollectionUtils;
import com.yuyuka.billiards.utils.RxUtils;

import java.lang.reflect.Type;
import java.util.List;

public class CollectionRoomPresenter extends BasePresenter<CollectionRoomContract.ICollectionRoomView, CollectionRoomContract.ICollectionRoomModel> {

    public CollectionRoomPresenter(CollectionRoomContract.ICollectionRoomView view) {
        super(view,new CollectionRoomModel());
    }

    public void getRecommendRoomList(int page){
        getView().showLoading();
        mModel.getCollectionRoomList(page)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {

                    @Override
                    public void onResult(String msg, String bizContent) {
                        getView().hideLoading();
                        if (TextUtils.isEmpty(bizContent)){
                            getView().showEmpty();
                            return;
                        }
                        Type type = new TypeToken<List<BilliardsRoomPojo>>(){}.getType();
                        List<BilliardsRoomPojo> data = new Gson().fromJson(bizContent,type);

                        if (CollectionUtils.isEmpty(data))
                            getView().showEmpty();
                        else
                            getView().showCollectionRoomList(data);
                    }

                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().hideLoading();
                        getView().showError(errMsg);
                    }
                });

    }
}
