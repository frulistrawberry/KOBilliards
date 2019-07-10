package com.kobilliards.mvp.presenter.nearbyroom;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kobilliards.base.BasePresenter;
import com.kobilliards.mvp.contract.rearbyroom.BilliardsCoachListContract;
import com.kobilliards.mvp.model.nearbyroom.BilliardsCoachListModel;
import com.kobilliards.net.RespObserver;
import com.kobilliards.pojo.BilliardsCoachPojo;
import com.kobilliards.pojo.BilliardsRoomPojo;
import com.kobilliards.utils.CollectionUtils;
import com.kobilliards.utils.RxUtils;

import java.lang.reflect.Type;
import java.util.List;

public class BilliardsCoachListPresenter extends BasePresenter<BilliardsCoachListContract.IBilliardsCoachListView, BilliardsCoachListContract.IBilliardsCoachListModel> {
    public BilliardsCoachListPresenter(BilliardsCoachListContract.IBilliardsCoachListView view) {
        super(view, new BilliardsCoachListModel());
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
