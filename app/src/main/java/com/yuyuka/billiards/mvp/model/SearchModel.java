package com.yuyuka.billiards.mvp.model;

import com.yuyuka.billiards.base.BaseModel;
import com.yuyuka.billiards.constants.UrlConstant;
import com.yuyuka.billiards.mvp.contract.search.SearchContract;
import com.yuyuka.billiards.net.BizContent;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.net.RequestParam;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;

public class SearchModel extends BaseModel implements SearchContract.ISearchModel {
    @Override
    public Observable<HttpResult> getResult(String keywords,double latitude,double longitude, int page) {
        BizContent content = new BizContent();
        content.buildPageQueryDto(page);
        content.setKeyword(keywords);
        Map<String,Object> params = new HashMap<>();
        params.put("latitude",latitude);
        params.put("longitude",longitude);
        content.setParams(params);
        RequestParam requestParam = new RequestParam(UrlConstant.SEARCH_INDEX,convertBizContent(content));
        return mService.simpleRequest(requestParam);
    }
}
