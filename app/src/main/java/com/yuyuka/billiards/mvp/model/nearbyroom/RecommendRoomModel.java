package com.yuyuka.billiards.mvp.model.nearbyroom;

import com.google.gson.Gson;
import com.yuyuka.billiards.base.BaseModel;
import com.yuyuka.billiards.constants.UrlConstant;
import com.yuyuka.billiards.mvp.contract.rearbyroom.RecommendRoomContract;
import com.yuyuka.billiards.net.BizContent;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.net.RequestParam;
import com.yuyuka.billiards.pojo.BilliardsRoomPojo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class RecommendRoomModel extends BaseModel implements RecommendRoomContract.IRecommendRoomModel {
    @Override
    public Observable<HttpResult> getRecommendRoomList(double lat, double lng,int page) {
        BizContent content = new BizContent();
        content.setPositionLatitude(lat);
        content.setPositionLongitude(lng);
        content.buildPageQueryDto(page);
        RequestParam requestParam = new RequestParam(UrlConstant.LIST,convertBizContent(content));
        return mService.simpleRequest(requestParam);

    }
}
