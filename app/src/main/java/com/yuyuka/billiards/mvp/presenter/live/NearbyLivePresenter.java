package com.yuyuka.billiards.mvp.presenter.live;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuyuka.billiards.base.BasePresenter;
import com.yuyuka.billiards.mvp.contract.live.NearbyLiveContract;
import com.yuyuka.billiards.mvp.model.live.NearbyLiveModel;
import com.yuyuka.billiards.net.RespObserver;
import com.yuyuka.billiards.pojo.ListData;
import com.yuyuka.billiards.pojo.LivePojo;
import com.yuyuka.billiards.utils.CollectionUtils;

import java.lang.reflect.Type;

public class NearbyLivePresenter extends BasePresenter<NearbyLiveContract.INearbyLiveView, NearbyLiveContract.INearbyLiveModel> {
    public NearbyLivePresenter(NearbyLiveContract.INearbyLiveView view) {
        super(view, new NearbyLiveModel());
    }

    public void getNearbyLiveList(int page){
        getView().showLoading();
        mModel.getNearbyLiveList(page)
                .compose(getView().bindToLifecycle())
                .subscribe(new RespObserver() {
                    @Override
                    public void onResult(String msg, String bizEntity) {
                        getView().hideLoading();
                        if (TextUtils.isEmpty(bizEntity)){
                            getView().showEmpty();
                            return;
                        }
                        Type type = new TypeToken<ListData<LivePojo>>(){}.getType();
                        ListData<LivePojo> data = new Gson().fromJson(bizEntity,type);

                        if (CollectionUtils.isEmpty(data.getDataList()))
                            getView().showEmpty();
                        else
                            getView().showNearbyLiveList(data.getDataList());
                    }

                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().hideLoading();
                        getView().showError(errMsg);
                    }
                });
    }
}
