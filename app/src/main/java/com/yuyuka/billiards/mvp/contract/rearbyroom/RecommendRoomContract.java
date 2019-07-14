package com.yuyuka.billiards.mvp.contract.rearbyroom;


import com.yuyuka.billiards.base.IBaseModel;
import com.yuyuka.billiards.base.IBaseView;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.pojo.BilliardsRoomPojo;

import java.util.List;

import io.reactivex.Observable;

public interface RecommendRoomContract {
    interface IRecommendRoomView extends IBaseView {
        void showRecommendRoomList(List<BilliardsRoomPojo> roomList);
    }

    interface  IRecommendRoomModel extends IBaseModel {
        Observable<HttpResult> getRecommendRoomList(int page, String sort, String filter);
    }
}
