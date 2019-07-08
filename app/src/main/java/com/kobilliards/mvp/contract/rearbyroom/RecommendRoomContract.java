package com.kobilliards.mvp.contract.rearbyroom;


import com.kobilliards.base.IBaseModel;
import com.kobilliards.base.IBaseView;
import com.kobilliards.net.HttpResult;
import com.kobilliards.pojo.BilliardsRoomPojo;

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
