package com.yuyuka.billiards.mvp.presenter.nearbyroom;

import android.text.TextUtils;

import com.yuyuka.billiards.base.BasePresenter;
import com.yuyuka.billiards.mvp.contract.rearbyroom.RecommendRoomContract;
import com.yuyuka.billiards.mvp.model.nearbyroom.RecommendRoomModel;
import com.yuyuka.billiards.net.RespObserver;
import com.yuyuka.billiards.pojo.BilliardsRoomPojo;
import com.yuyuka.billiards.utils.CollectionUtils;
import com.yuyuka.billiards.utils.RxUtils;

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
