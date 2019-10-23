package com.yuyuka.billiards.mvp.model;

import com.yuyuka.billiards.base.BaseModel;
import com.yuyuka.billiards.constants.UrlConstant;
import com.yuyuka.billiards.mvp.contract.table.PointContract;
import com.yuyuka.billiards.mvp.contract.table.TableContract;
import com.yuyuka.billiards.net.BizContent;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.net.RequestParam;
import com.yuyuka.billiards.utils.CommonUtils;

import io.reactivex.Observable;

public class TableModel extends BaseModel implements TableContract.ITableModel, PointContract.IPointModel {
    @Override
    public Observable<HttpResult> getTableInfo(long tableId) {
        BizContent content = new BizContent();
        content.setPoolTableId(tableId);
        RequestParam requestParam = new RequestParam(UrlConstant.POOL_TABLE_API_GET,convertBizContent(content));
        return mService.simpleRequest(requestParam);
    }

    @Override
    public Observable<HttpResult> tackOrder(int orderType, long billiardsPoolTable, int competitionType, int payType, int payChannel) {
        BizContent content = new BizContent();
        if (billiardsPoolTable!=0)
            content.setBilliardsPoolTable(billiardsPoolTable);
        content.setOrderType(orderType);
        content.setCompetitionType(competitionType);
        BizContent.BilliardsMakeAppOrderInfo info = new BizContent.BilliardsMakeAppOrderInfo();
        info.setUserId(CommonUtils.getUserId());
        info.setPayType(payType);
        info.setPayChannel(payChannel);
        content.setBilliardsMakeAppOrderInfo(info);
        RequestParam requestParam = new RequestParam(UrlConstant.PLACE_ORDER,convertBizContent(content));
        return mService.simpleRequest(requestParam);
    }

    @Override
    public Observable<HttpResult> enterMatch(int id, int refOrderId, int payChannel) {
        BizContent content = new BizContent();
        content.setId(id);
        if (refOrderId!=0)
            content.setRefOrderId(refOrderId);
        content.setPayChannel(payChannel);
        content.setUserId(CommonUtils.getUserId());
        RequestParam requestParam = new RequestParam(UrlConstant.PLACE_JOIN,convertBizContent(content));
        return mService.simpleRequest(requestParam);
    }

    @Override
    public Observable<HttpResult> sendPoint(int id, int userId, int point) {
        BizContent content = new BizContent();
        content.setId(id);
        content.setUserId(userId);
        content.setPoint(point);
        RequestParam requestParam = new RequestParam(UrlConstant.SEND_POINT,convertBizContent(content));
        return mService.simpleRequest(requestParam);
    }

    @Override
    public Observable<HttpResult> confirm(int id) {
        BizContent content = new BizContent();
        content.setId(id);
        content.setUserId(CommonUtils.getUserId());
        RequestParam requestParam = new RequestParam(UrlConstant.CONFIRM_POINT,convertBizContent(content));
        return mService.simpleRequest(requestParam);
    }

    public Observable<HttpResult> settle(int id,int payChannel) {
        BizContent content = new BizContent();
        content.setId(id);
        content.setUserId(CommonUtils.getUserId());
        content.setPayChannel(payChannel);
        RequestParam requestParam = new RequestParam(UrlConstant.PLACE_SETTLE,convertBizContent(content));
        return mService.simpleRequest(requestParam);
    }

    @Override
    public Observable<HttpResult> orderPush(int id) {
        BizContent content = new BizContent();
        content.setId(id);
        RequestParam requestParam = new RequestParam(UrlConstant.PUSH_ORDER,convertBizContent(content));
        return mService.simpleRequest(requestParam);
    }

    @Override
    public Observable<HttpResult> opendOrder(int id) {
        BizContent content = new BizContent();
        content.setId(id);
        RequestParam requestParam = new RequestParam(UrlConstant.OPEND_ORDER,convertBizContent(content));
        return mService.simpleRequest(requestParam);
    }

    @Override
    public Observable<HttpResult> cancleOrder(int id) {
        BizContent content = new BizContent();
        content.setId(id);
        content.setUserId(CommonUtils.getUserId());
        RequestParam requestParam = new RequestParam(UrlConstant.PLACE_CANCEL,convertBizContent(content));
        return mService.simpleRequest(requestParam);
    }
}
