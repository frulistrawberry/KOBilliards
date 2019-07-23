package com.yuyuka.billiards.mvp.model.room;

import com.yuyuka.billiards.base.BaseModel;
import com.yuyuka.billiards.constants.UrlConstant;
import com.yuyuka.billiards.mvp.contract.room.RecommendRoomContract;
import com.yuyuka.billiards.net.BizContent;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.net.RequestParam;

import io.reactivex.Observable;

public class RecommendRoomModel extends BaseModel implements RecommendRoomContract.IRecommendRoomModel {
    @Override
    public Observable<HttpResult> getRecommendRoomList(double lat, double lng,int page) {
        BizContent content = new BizContent();
        content.setPositionLatitude(lat);
        content.setPositionLongitude(lng);
        content.setCityId(1);
        content.buildPageQueryDto(page);
        RequestParam requestParam = new RequestParam(UrlConstant.LIST,convertBizContent(content));
        return mService.simpleRequest(requestParam);

    }
}
