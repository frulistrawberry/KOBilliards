package com.yuyuka.billiards.mvp.model;

import com.yuyuka.billiards.base.BaseModel;
import com.yuyuka.billiards.constants.UrlConstant;
import com.yuyuka.billiards.mvp.contract.course.CourseDetailContract;
import com.yuyuka.billiards.mvp.contract.course.KOListContract;
import com.yuyuka.billiards.mvp.contract.course.KOTypeContract;
import com.yuyuka.billiards.net.BizContent;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.net.RequestParam;
import com.yuyuka.billiards.utils.CommonUtils;

import io.reactivex.Observable;

public class CourseModel extends BaseModel implements KOTypeContract.IKOTypeModel, KOListContract.IKOListModel, CourseDetailContract.ICourseDetailModel {

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

    @Override
    public Observable<HttpResult> getCourseInfo(int id) {
        BizContent content = new BizContent();
        content.setId(id);
        RequestParam requestParam = new RequestParam(UrlConstant.CRIPPLE_INFO_GET,convertBizContent(content));
        return mService.simpleRequest(requestParam);
    }

    @Override
    public Observable<HttpResult> collect(int billiardsInfoId) {
        BizContent content = new BizContent();
        content.setBizId(billiardsInfoId);
        content.setCollectionsType(0);
        content.setUserId(CommonUtils.getUserId());
        RequestParam requestParam = new RequestParam(UrlConstant.AUTHORIZED_USER_PUT_COLLECT,convertBizContent(content));
        return mService.simpleRequest(requestParam);
    }

    @Override
    public Observable<HttpResult> praise(int bizId) {
        BizContent bizContent = new BizContent();
        bizContent.setBizId(bizId);
        bizContent.setBizType(0);
        bizContent.setUserId(CommonUtils.getUserId());
        RequestParam requestParam = new RequestParam(UrlConstant.AUTHORIZED_USER_PUT_APPRECIATE,convertBizContent(bizContent));
        return mService.simpleRequest(requestParam);
    }


}
