package com.kobilliards.mvp.contract.rearbyroom;

import com.kobilliards.base.IBaseModel;
import com.kobilliards.base.IBaseView;
import com.kobilliards.net.HttpResult;
import com.kobilliards.pojo.BilliardsRoomPojo;

import java.util.List;

import io.reactivex.Observable;

public interface BilliardsRoomListContract {
    interface IBilliardsRoomListView extends IBaseView {
        void showBilliardsRoomList(List<BilliardsRoomPojo> roomList);
    }

    interface IBilliardsRoomListModel extends IBaseModel {
        Observable<HttpResult> getBilliardsRoomList(int page);
    }
}
