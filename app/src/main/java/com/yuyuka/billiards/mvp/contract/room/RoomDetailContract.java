package com.yuyuka.billiards.mvp.contract.room;

import com.yuyuka.billiards.base.IBaseModel;
import com.yuyuka.billiards.base.IBaseView;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.pojo.BilliardsGoods;
import com.yuyuka.billiards.pojo.RoomInfoPojo;

import java.util.List;

import io.reactivex.Observable;


public interface RoomDetailContract {
    interface IRoomDetailView extends IBaseView {
        void showRoomInfo(RoomInfoPojo info);
        void showGoodsInfo(List<BilliardsGoods> goods);
    }

    interface IRoomDetailModel extends IBaseModel {
        Observable<HttpResult> getRoomInfo(int billiardsInfoId);
        Observable<HttpResult> getGoodsInfo(int billiardsInfoId,int weekNum);

    }
}
