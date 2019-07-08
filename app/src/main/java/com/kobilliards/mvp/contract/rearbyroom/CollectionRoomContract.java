package com.kobilliards.mvp.contract.rearbyroom;


import com.kobilliards.base.IBaseModel;
import com.kobilliards.base.IBaseView;
import com.kobilliards.net.HttpResult;
import com.kobilliards.pojo.BilliardsRoomPojo;

import java.util.List;

import io.reactivex.Observable;

public interface CollectionRoomContract {
    interface ICollectionRoomView extends IBaseView {
        void showCollectionRoomList(List<BilliardsRoomPojo> roomList);
    }

    interface  ICollectionRoomModel extends IBaseModel {
        Observable<HttpResult> getCollectionRoomList(int page);
    }
}
