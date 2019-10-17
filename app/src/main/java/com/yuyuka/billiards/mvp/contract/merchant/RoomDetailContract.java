package com.yuyuka.billiards.mvp.contract.merchant;

import com.yuyuka.billiards.base.IBaseModel;
import com.yuyuka.billiards.base.IBaseView;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.pojo.BilliardsGoods;
import com.yuyuka.billiards.pojo.BilliardsRoomPojo;
import com.yuyuka.billiards.pojo.OrderPojo;
import com.yuyuka.billiards.pojo.RoomInfoPojo;

import java.util.List;

import io.reactivex.Observable;


public interface RoomDetailContract {
    interface IRoomDetailView extends IBaseView {
        void showRoomInfo(BilliardsRoomPojo info);
        void showGoodsInfo(List<BilliardsGoods> goods);
        void showCollectSuccess(String msg);
        void showCollectFailure(String msg);
        void showOrderSuccess(OrderPojo data);
    }

    interface IRoomDetailModel extends IBaseModel {
        Observable<HttpResult> getRoomInfo(String billiardsInfoId);
        Observable<HttpResult> collect(int billiardsInfoId);
        Observable<HttpResult> getGoodsInfo(String billiardsInfoId,int weekNum);
        Observable<HttpResult> tackOrder(long tableId,int goodsId);

    }
}
