package com.yuyuka.billiards.mvp.model;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.yuyuka.billiards.base.BaseModel;
import com.yuyuka.billiards.constants.UrlConstant;
import com.yuyuka.billiards.mvp.contract.market.GoodsDetailContract;
import com.yuyuka.billiards.mvp.contract.market.GoodsListContract;
import com.yuyuka.billiards.net.BizContent;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.net.RequestParam;
import com.yuyuka.billiards.pojo.GoodsPojo;
import com.yuyuka.billiards.pojo.ListData;
import com.yuyuka.billiards.utils.CommonUtils;
import com.yuyuka.billiards.utils.DateUtils;

import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public class MarketModel extends BaseModel implements GoodsListContract.IGoodsListModel , GoodsDetailContract.IGoodsDetailModel {


    /**
     * 获取二手商品列表
     * @param keywords 搜索关键字
     * @param lat 经度
     * @param lng 纬度
     * @param sortCondition 排序 1:距离升序 2:距离降序 3:价格升序 4:价格降序
     * @param typeCondition 类型筛选 1:球杆 2:球盒 3:球桌 4:其他
     * @param quickCondition 快速筛选 0:全新 1:实拍 2:支持验货
     * @param lowPrice 最低价
     * @param highPrice 最高价
     * @param releaseTimeCondition 发布时间筛选 1:1天内 2:7天内 3:14天内 4:30天内
     * @param otherCondition 交易类型筛选 0:免费送 1:包路费 2:自提
     * @param page 页码
     * @return
     */
    @Override
    public Observable<HttpResult> getGoodsList(String keywords,double lat,double lng, int sortCondition, int typeCondition, int quickCondition, int lowPrice, int highPrice, int releaseTimeCondition, int otherCondition, int page) {
        BizContent content = new BizContent();
        content.setQueryType(4);
        if (!TextUtils.isEmpty(keywords))
            content.setKeyword(keywords);
        Map<String,Object> params = new HashMap<>();
        params.put("latitude",lat);
        params.put("longitude",lng);
        if (typeCondition != -1){
            params.put("typeId",typeCondition);
        }
        if (quickCondition != -1){
            params.put("secondMallType",quickCondition);
        }
        if (lowPrice!=-1 && highPrice !=-1){
            params.put("price","["+lowPrice+" TO "+highPrice+"]");
        }
        if (releaseTimeCondition != -1){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            long currentTime = System.currentTimeMillis();
            long date = 0;
            switch (releaseTimeCondition){
                case 1:
                    date = currentTime - (24*60*60*1000);
                    break;
                case 2:
                    date = currentTime - (24*60*60*1000*7);
                    break;
                case 3:
                    date = currentTime - (24*60*60*1000*14);
                    break;
                case 4:
                    date = currentTime - (24*60*60*1000*30);
                    break;
            }
            params.put("created","["+format.format(new Date())+" TO "+format.format(new Date(date))+"]");
        }
        if (otherCondition != -1){
            params.put("transactionType",otherCondition);
        }
        Map<String,Object> order = new HashMap<>();
        //排序
        switch (sortCondition){
            case 1:
                order.put("field","geodist()");
                order.put("orderType","asc");
                break;
            case 2:
                order.put("field","geodist()");
                order.put("orderType","desc");
                break;
            case 3:
                order.put("field","price");
                order.put("orderType","asc");
                break;
            case 4:
                order.put("field","price");
                order.put("orderType","desc");
                break;
        }
        content.setOrder(order);
        content.setParams(params);
        content.buildPageQueryDto(page);
        RequestParam requestParam = new RequestParam(UrlConstant.SEARCH_BIZ,convertBizContent(content));
        return mService.simpleRequest(requestParam);
    }

    /**
     * 获取商品详情
     * @param id 商品ID
     * @return
     */
    @Override
    public Observable<HttpResult> getGoodsInfo(int id) {
        BizContent content = new BizContent();
        content.setId(id);
        RequestParam requestParam = new RequestParam(UrlConstant.SECOND_INFO_GET,convertBizContent(content));
        return mService.simpleRequest(requestParam);
    }

    /**
     * 获取商品评论列表
     * @param billiardsSecondMallId 商品id
     * @param page 页码
     * @return
     */
    @Override
    public Observable<HttpResult> getCommentList(int billiardsSecondMallId,int page) {
        BizContent content = new BizContent();
        content.setBilliardsSecondMallId(billiardsSecondMallId);
        content.buildPageQueryDto(page);
        RequestParam requestParam = new RequestParam(UrlConstant.SECOND_INFO_MESSAGE_LIST,convertBizContent(content));
        return mService.simpleRequest(requestParam);
    }

    /**
     * 商品留言
     * @param billiardsSecondMallId 商品id
     * @param content 评论内容
     * @return
     */
    @Override
    public Observable<HttpResult> comment(int billiardsSecondMallId, String content) {
        BizContent bizContent = new BizContent();
        bizContent.setBilliardsSecondMallId(billiardsSecondMallId);
        bizContent.setContent(content);
        bizContent.setUserId(CommonUtils.getUserId());
        RequestParam requestParam = new RequestParam(UrlConstant.SECOND_INFO_MESSAGE_PUT,convertBizContent(bizContent));
        return mService.simpleRequest(requestParam);
    }

    @Override
    public Observable<HttpResult> want(int billiardsSecondMallId) {
        BizContent content = new BizContent();
        content.setBilliardsSecondMallId(billiardsSecondMallId);
        content.setUserId(CommonUtils.getUserId());
        RequestParam requestParam = new RequestParam(UrlConstant.SECOND_INFO_MEWANT,convertBizContent(content));
        return mService.simpleRequest(requestParam);
    }

    @Override
    public Observable<HttpResult> praise(int bizId) {
        BizContent bizContent = new BizContent();
        bizContent.setBizId(bizId);
        bizContent.setBizType(4);
        bizContent.setUserId(CommonUtils.getUserId());
        RequestParam requestParam = new RequestParam(UrlConstant.AUTHORIZED_USER_PUT_APPRECIATE,convertBizContent(bizContent));
        return mService.simpleRequest(requestParam);
    }

    @Override
    public Observable<HttpResult> collect(int billiardsInfoId) {
        BizContent content = new BizContent();
        content.setBizId(billiardsInfoId);
        content.setCollectionsType(4);
        content.setUserId(CommonUtils.getUserId());
        RequestParam requestParam = new RequestParam(UrlConstant.AUTHORIZED_USER_PUT_COLLECT,convertBizContent(content));
        return mService.simpleRequest(requestParam);
    }


}
