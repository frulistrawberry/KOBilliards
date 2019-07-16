package com.yuyuka.billiards.mvp.contract.rearbyroom;

import com.yuyuka.billiards.base.IBaseModel;
import com.yuyuka.billiards.base.IBaseView;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.pojo.BilliardsRoomPojo;

import java.util.List;

import io.reactivex.Observable;

public interface BilliardsRoomSearchContract {
    interface IBilliardsRoomSearchView extends IBaseView {
        void showBilliardsRoomList(List<BilliardsRoomPojo> roomList);
    }

    interface IBilliardsRoomSearchModel extends IBaseModel {
        Observable<HttpResult> getBilliardsRoomList(int page);
    }
}
