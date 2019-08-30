package com.yuyuka.billiards.mvp.contract.merchant;

import com.yuyuka.billiards.base.IBaseModel;
import com.yuyuka.billiards.base.IBaseView;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.pojo.BilliardsRoomPojo;

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
