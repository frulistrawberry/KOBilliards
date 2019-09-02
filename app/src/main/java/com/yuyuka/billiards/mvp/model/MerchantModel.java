package com.yuyuka.billiards.mvp.model;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.yuyuka.billiards.base.BaseModel;
import com.yuyuka.billiards.constants.UrlConstant;
import com.yuyuka.billiards.mvp.contract.merchant.BilliardsCoachListContract;
import com.yuyuka.billiards.mvp.contract.merchant.BilliardsRoomListContract;
import com.yuyuka.billiards.mvp.contract.merchant.BilliardsRoomSearchContract;
import com.yuyuka.billiards.mvp.contract.merchant.CollectionRoomContract;
import com.yuyuka.billiards.mvp.contract.merchant.MerchantCommentContract;
import com.yuyuka.billiards.mvp.contract.merchant.OrderConfirmContract;
import com.yuyuka.billiards.mvp.contract.merchant.RecommendRoomContract;
import com.yuyuka.billiards.mvp.contract.merchant.RoomDetailContract;
import com.yuyuka.billiards.net.BizContent;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.net.RequestParam;
import com.yuyuka.billiards.pojo.BilliardsCoachPojo;
import com.yuyuka.billiards.pojo.BilliardsRoomPojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public class MerchantModel extends BaseModel implements BilliardsCoachListContract.IBilliardsCoachListModel,
        CollectionRoomContract.ICollectionRoomModel, BilliardsRoomSearchContract.IBilliardsRoomSearchModel,
        BilliardsRoomListContract.IBilliardsRoomListModel, RecommendRoomContract.IRecommendRoomModel,
        RoomDetailContract.IRoomDetailModel, OrderConfirmContract.IOrderConfirmModel, MerchantCommentContract.IMerchantCommentModel {
    @Override
    public Observable<HttpResult> getBilliardsCoachList(int page) {
        return Observable.create(emitter -> {
            Thread.sleep(1000);
            List<BilliardsCoachPojo> bizEntity = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                bizEntity.add(new BilliardsCoachPojo());
            }
            HttpResult result = new HttpResult();
            result.setCode(10000);
            result.setMsg("请求成功");
            result.setBizContent(new Gson().toJson(bizEntity));
            result.setSign("");
            emitter.onNext(result);
            emitter.onComplete();
        });
    }

    @Override
    public Observable<HttpResult> getBilliardsRoomList(int page) {
        return Observable.create(emitter -> {
            Thread.sleep(1000);
            List<BilliardsRoomPojo> bizEntity = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                bizEntity.add(new BilliardsRoomPojo());
            }
            HttpResult result = new HttpResult();
            result.setCode(10000);
            result.setMsg("请求成功");
            result.setBizContent(new Gson().toJson(bizEntity));
            result.setSign("");
            emitter.onNext(result);
            emitter.onComplete();
        });
    }

    @Override
    public Observable<HttpResult> getCollectionRoomList(int page) {
        return Observable.create(emitter -> {
            Thread.sleep(1000);
            List<BilliardsRoomPojo> bizEntity = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                bizEntity.add(new BilliardsRoomPojo());
            }
            HttpResult result = new HttpResult();
            result.setCode(10000);
            result.setMsg("请求成功");
            result.setBizContent(new Gson().toJson(bizEntity));
            result.setSign("");
            emitter.onNext(result);
            emitter.onComplete();
        });
    }


    @Override
    public Observable<HttpResult> getRoomInfo(int roomId) {
        return null;
    }

    /**
     *
     * @param lat 纬度
     * @param lng 经度
     * @param sortCondition 排序 1:距离升序 2:距离降序 3:星级/价格升序 4:星级/价格降序
     * @param keywords 搜索关键字
     * @param page 页码
     */
    @Override
    public Observable<HttpResult> getRecommendRoomList(String keywords,double lat, double lng, int sortCondition, int page) {
        BizContent content = new BizContent();
        content.setQueryType(1);
        if (!TextUtils.isEmpty(keywords))
            content.setKeyword(keywords);
        Map<String,Object> params = new HashMap<>();
        params.put("latitude",lat);
        params.put("longitude",lng);
        Map<String,Object> order = new HashMap<>();
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
                order.put("field","minimumPayment");
                order.put("orderType","asc");
                break;
            case 4:
                order.put("field","minimumPayment");
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
     * 获取球厅产品
     * @param billiardsInfoId 球厅id
     * @param weekNum 星期几
     * @return
     */
    @Override
    public Observable<HttpResult> getGoodsInfo(String billiardsInfoId, int weekNum) {
        BizContent content = new BizContent();
        content.setBilliardsInfoId(billiardsInfoId);
        content.setWeekNum(weekNum);
        RequestParam requestParam = new RequestParam(UrlConstant.GOODS_AND_PROMOTION_LIST,convertBizContent(content));
        return mService.simpleRequest(requestParam);
    }


    /**
     * 获取商户套餐
     * @param billiardsInfoId 商户id
     * @return
     */
    @Override
    public Observable<HttpResult> getPackages(String billiardsInfoId) {
        BizContent content = new BizContent();
        content.setBilliardsInfoId(billiardsInfoId);
        RequestParam requestParam = new RequestParam(UrlConstant.GOODS_SETMEAL_LIST,convertBizContent(content));
        return mService.simpleRequest(requestParam);
    }

    /**
     * 商户评论接口
     * @param billiardsId 商户id
     * @param messageInfo 留言信息
     * @param gameTypeList 玩法类型
     * @param population 总体
     * @param local 位置
     * @param service 服务
     * @param hygiene 卫生
     * @param facilities 设施
     */
    @Override
    public Observable<HttpResult> comment(String billiardsId, String messageInfo, List<String> gameTypeList, int population, int local, int service, int hygiene, int facilities) {
        BizContent content = new BizContent();
        content.setBilliardsId(billiardsId);
        BizContent.StarClass starClass = new BizContent.StarClass();
        starClass.setPopulation(population);
        starClass.setLocal(local);
        starClass.setService(service);
        starClass.setHygiene(hygiene);
        starClass.setFacilities(facilities);
        content.setStarClass(starClass);
        content.setGameTypeList(gameTypeList);
        content.setMessageInfo(messageInfo);
        content.setUserId(12);
        RequestParam requestParam = new RequestParam(UrlConstant.APPRASIE_PUT,convertBizContent(content));
        return mService.simpleRequest(requestParam);
    }
}
