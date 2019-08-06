package com.yuyuka.billiards.mvp.model;

import com.google.gson.Gson;
import com.yuyuka.billiards.base.BaseModel;
import com.yuyuka.billiards.constants.UrlConstant;
import com.yuyuka.billiards.mvp.contract.room.BilliardsCoachListContract;
import com.yuyuka.billiards.mvp.contract.room.BilliardsRoomListContract;
import com.yuyuka.billiards.mvp.contract.room.BilliardsRoomSearchContract;
import com.yuyuka.billiards.mvp.contract.room.CollectionRoomContract;
import com.yuyuka.billiards.mvp.contract.room.RecommendRoomContract;
import com.yuyuka.billiards.mvp.contract.room.RoomDetailContract;
import com.yuyuka.billiards.net.BizContent;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.net.RequestParam;
import com.yuyuka.billiards.pojo.BilliardsCoachPojo;
import com.yuyuka.billiards.pojo.BilliardsRoomPojo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class RoomModel extends BaseModel implements BilliardsCoachListContract.IBilliardsCoachListModel,
        CollectionRoomContract.ICollectionRoomModel, BilliardsRoomSearchContract.IBilliardsRoomSearchModel,
        BilliardsRoomListContract.IBilliardsRoomListModel, RecommendRoomContract.IRecommendRoomModel,
        RoomDetailContract.IRoomDetailModel {
    @Override
    public Observable<HttpResult> getBilliardsCoachList(int page) {
        return Observable.create(emitter -> {
            Thread.sleep(1000);
            List<BilliardsCoachPojo> bizEntity = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                bizEntity.add(new BilliardsCoachPojo());
            }
            HttpResult result = new HttpResult();
            result.setCode(10000);
            result.setMsg("请求成功");
            result.setBizContent(new Gson().toJson(bizEntity));
            result.setSign("");
            emitter.onNext(result);
            emitter.onComplete();
        });
    }

    @Override
    public Observable<HttpResult> getBilliardsRoomList(int page) {
        return Observable.create(emitter -> {
            Thread.sleep(1000);
            List<BilliardsRoomPojo> bizEntity = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                bizEntity.add(new BilliardsRoomPojo());
            }
            HttpResult result = new HttpResult();
            result.setCode(10000);
            result.setMsg("请求成功");
            result.setBizContent(new Gson().toJson(bizEntity));
            result.setSign("");
            emitter.onNext(result);
            emitter.onComplete();
        });
    }

    @Override
    public Observable<HttpResult> getCollectionRoomList(int page) {
        return Observable.create(emitter -> {
            Thread.sleep(1000);
            List<BilliardsRoomPojo> bizEntity = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                bizEntity.add(new BilliardsRoomPojo());
            }
            HttpResult result = new HttpResult();
            result.setCode(10000);
            result.setMsg("请求成功");
            result.setBizContent(new Gson().toJson(bizEntity));
            result.setSign("");
            emitter.onNext(result);
            emitter.onComplete();
        });
    }

    @Override
    public Observable<HttpResult> getRecommendRoomList(double lat, double lng, int page) {
        BizContent content = new BizContent();
        content.setPositionLatitude(lat);
        content.setPositionLongitude(lng);
        content.setCityId(1);
        content.buildPageQueryDto(page);
        RequestParam requestParam = new RequestParam(UrlConstant.LIST,convertBizContent(content));
        return mService.simpleRequest(requestParam);
    }

    @Override
    public Observable<HttpResult> getRoomInfo(int roomId) {
        return null;
    }

    @Override
    public Observable<HttpResult> getGoodsInfo(int billiardsInfoId, int weekNum) {
        BizContent content = new BizContent();
        content.setBilliardsInfoId(billiardsInfoId);
        content.setWeekNum(weekNum);
        RequestParam requestParam = new RequestParam(UrlConstant.GOODS_AND_PROMOTION_LIST,convertBizContent(content));
        return mService.simpleRequest(requestParam);
    }
}
