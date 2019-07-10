package com.kobilliards.mvp.presenter.nearbyroom;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kobilliards.base.BasePresenter;
import com.kobilliards.mvp.contract.rearbyroom.BilliardsRoomListContract;
import com.kobilliards.mvp.contract.rearbyroom.CollectionRoomContract;
import com.kobilliards.mvp.model.nearbyroom.BilliardsRoomModel;
import com.kobilliards.mvp.model.nearbyroom.CollectionRoomModel;
import com.kobilliards.net.RespObserver;
import com.kobilliards.pojo.BilliardsRoomPojo;
import com.kobilliards.utils.CollectionUtils;
import com.kobilliards.utils.RxUtils;

import java.lang.reflect.Type;
import java.util.List;

public class BilliardsRoomListPresenter extends BasePresenter<BilliardsRoomListContract.IBilliardsRoomListView, BilliardsRoomListContract.IBilliardsRoomListModel> {

    public BilliardsRoomListPresenter(BilliardsRoomListContract.IBilliardsRoomListView view) {
        super(view,new BilliardsRoomModel());
    }

    public void getBilliardsRoomList(int page){
        getView().showLoading();
        mModel.getBilliardsRoomList(page)
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
                            getView().showBilliardsRoomList(data);
                    }

                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().hideLoading();
                        getView().showError(errMsg);
                    }
                });

    }
}
