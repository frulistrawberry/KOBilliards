package com.yuyuka.billiards.mvp.model;

import com.yuyuka.billiards.base.BaseModel;
import com.yuyuka.billiards.constants.UrlConstant;
import com.yuyuka.billiards.mvp.contract.ko.KOListContract;
import com.yuyuka.billiards.mvp.contract.ko.KOTypeContract;
import com.yuyuka.billiards.net.BizContent;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.net.RequestParam;

import io.reactivex.Observable;

public class KOModel extends BaseModel implements KOTypeContract.IKOTypeModel , KOListContract.IKOListModel {

    @Override
    public Observable<HttpResult> getKOClassList(int modeType) {
        BizContent content = new BizContent();
        content.setModeType(modeType);
        RequestParam requestParam = new RequestParam(UrlConstant.CRIPPLE_TYPE_LIST,convertBizContent(content));
        return mService.simpleRequest(requestParam);
    }

    @Override
    public Observable<HttpResult> getKOList(int id, int page) {
        BizContent content = new BizContent();
        content.buildPageQueryDto(page);
        content.setTypeId(id);
        RequestParam requestParam = new RequestParam(UrlConstant.CRIPPLE_INFO_LIST,convertBizContent(content));
        return mService.simpleRequest(requestParam);
    }
}
