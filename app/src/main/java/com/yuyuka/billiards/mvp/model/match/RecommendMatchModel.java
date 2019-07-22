package com.yuyuka.billiards.mvp.model.match;

import com.yuyuka.billiards.base.BaseModel;
import com.yuyuka.billiards.constants.UrlConstant;
import com.yuyuka.billiards.mvp.contract.match.RecommendMatchContract;
import com.yuyuka.billiards.net.BizContent;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.net.RequestParam;


import io.reactivex.Observable;

public class RecommendMatchModel extends BaseModel implements RecommendMatchContract.IRecommendMatchModel {
    @Override
    public Observable<HttpResult> getRecommendMatchList(double lat, double lng, int status,int page) {
        BizContent content = new BizContent();
        content.setLatitude(lat);
        content.setLongitude(lng);
        content.setStatus(status);
        content.buildPageQueryDto(page);
        RequestParam requestParam = new RequestParam(UrlConstant.MATCH_NEARBY_LIST_BEGINNING,convertBizContent(content));
        return mService.simpleRequest(requestParam);

    }
}
