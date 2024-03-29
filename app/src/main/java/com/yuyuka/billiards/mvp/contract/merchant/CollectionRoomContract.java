package com.yuyuka.billiards.mvp.contract.merchant;


import com.yuyuka.billiards.base.IBaseModel;
import com.yuyuka.billiards.base.IBaseView;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.pojo.BilliardsRoomPojo;

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
