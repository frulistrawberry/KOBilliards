package com.yuyuka.billiards.mvp.model;

import com.yuyuka.billiards.base.BaseModel;
import com.yuyuka.billiards.constants.UrlConstant;
import com.yuyuka.billiards.mvp.contract.table.TableContract;
import com.yuyuka.billiards.net.BizContent;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.net.RequestParam;
import com.yuyuka.billiards.utils.CommonUtils;

import io.reactivex.Observable;

public class TableModel extends BaseModel implements TableContract.ITableModel {
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
        content.setRefOrderId(refOrderId);
        content.setPayChannel(payChannel);
        RequestParam requestParam = new RequestParam(UrlConstant.PLACE_JOIN,convertBizContent(content));
        return mService.simpleRequest(requestParam);
    }
}
