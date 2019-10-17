package com.yuyuka.billiards.mvp.model;

import com.yuyuka.billiards.base.BaseModel;
import com.yuyuka.billiards.constants.UrlConstant;
import com.yuyuka.billiards.mvp.contract.HomeContract;
import com.yuyuka.billiards.net.BizContent;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.net.RequestParam;
import com.yuyuka.billiards.utils.CommonUtils;

import io.reactivex.Observable;

public class HomeModel extends BaseModel implements HomeContract.IHomeModel {
    @Override
    public Observable<HttpResult> myTable() {
        BizContent content = new BizContent();
        content.setUserId(CommonUtils.getUserId());
        RequestParam requestParam = new RequestParam(UrlConstant.AUTHORIZED_USER_PUSH_MY_TABLE,convertBizContent(content));
        return mService.simpleRequest(requestParam);
    }

    @Override
    public Observable<HttpResult> getBattle() {
        BizContent content = new BizContent();
        content.setUserId(CommonUtils.getUserId());
        RequestParam requestParam = new RequestParam(UrlConstant.AUTHORIZED_USER_MY_TABLE,convertBizContent(content));
        return mService.simpleRequest(requestParam);
    }


}
