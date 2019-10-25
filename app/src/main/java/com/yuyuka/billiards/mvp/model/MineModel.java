package com.yuyuka.billiards.mvp.model;

import com.netease.nim.uikit.common.CommonUtil;
import com.yuyuka.billiards.base.BaseModel;
import com.yuyuka.billiards.constants.UrlConstant;
import com.yuyuka.billiards.mvp.contract.mine.MineContract;
import com.yuyuka.billiards.net.BizContent;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.net.RequestParam;
import com.yuyuka.billiards.utils.CommonUtils;

import io.reactivex.Observable;

public class MineModel extends BaseModel implements MineContract.IMinewModel {
    @Override
    public Observable<HttpResult> getMineData() {
        BizContent content = new BizContent();
        content.setUserId(CommonUtils.getUserId());
        RequestParam requestParam = new RequestParam(UrlConstant.AUTHORIZED_USERINFO_GET_MAIN,convertBizContent(content));
        return mService.simpleRequest(requestParam);
    }
}
