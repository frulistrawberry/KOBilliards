package com.yuyuka.billiards.mvp.model;

import android.text.TextUtils;

import com.yuyuka.billiards.base.BaseModel;
import com.yuyuka.billiards.constants.UrlConstant;
import com.yuyuka.billiards.mvp.contract.match.MatchDetailContract;
import com.yuyuka.billiards.mvp.contract.match.RecommendMatchContract;
import com.yuyuka.billiards.net.BizContent;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.net.RequestParam;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;

public class MatchModel extends BaseModel implements RecommendMatchContract.IRecommendMatchModel, MatchDetailContract.IMatchDetailModel {


    /**
     * 获取比赛列表
     * @param keywords 搜索关键字
     * @param lat 纬度
     * @param lng 经度
     * @param sortCondition 排序 排序 1:距离升序 2:距离降序 3:奖金升序 4:奖金降序 5:时间升序 6时间降序
     * @param page 页码
     * @return
     */
    @Override
    public Observable<HttpResult> getRecommendMatchList(String keywords, double lat, double lng, int sortCondition, int page) {
        BizContent content = new BizContent();
        content.setQueryType(2);
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
                order.put("field","totalBonus");
                order.put("orderType","asc");
                break;
            case 4:
                order.put("field","totalBonus");
                order.put("orderType","desc");
                break;
            case 5:
                order.put("field","created");
                order.put("orderType","asc");
                break;
            case 6:
                order.put("field","created");
                order.put("orderType","desc");
                break;
        }
        content.setOrder(order);
        content.setParams(params);
        content.buildPageQueryDto(page);
        RequestParam requestParam = new RequestParam(UrlConstant.SEARCH_BIZ,convertBizContent(content));
        return mService.simpleRequest(requestParam);
    }

    @Override
    public Observable<HttpResult> getMatchDetail(String matchId) {
        BizContent content = new BizContent();
        content.setMatchInfoId(Integer.valueOf(matchId));
        RequestParam requestParam = new RequestParam(UrlConstant.MATCH_GET,convertBizContent(content));
        return mService.simpleRequest(requestParam);
    }

    @Override
    public Observable<HttpResult> getMatchBonus(String matchId) {
        BizContent content = new BizContent();
        content.setMatchInfoId(Integer.valueOf(matchId));
        RequestParam requestParam = new RequestParam(UrlConstant.MATCH_LIST_BONUS,convertBizContent(content));
        return mService.simpleRequest(requestParam);
    }
}
