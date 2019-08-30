package com.yuyuka.billiards.mvp.model;

import com.yuyuka.billiards.base.BaseModel;
import com.yuyuka.billiards.constants.UrlConstant;
import com.yuyuka.billiards.mvp.contract.news.NewsListContract;
import com.yuyuka.billiards.net.BizContent;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.net.RequestParam;

import io.reactivex.Observable;

public class NewsModel extends BaseModel implements NewsListContract.INewsListModel {
    @Override
    public Observable<HttpResult> getNewsList(int page, int queryType) {
        BizContent content = new BizContent();
        content.setQueryType(queryType);
        content.buildPageQueryDto(page);
        RequestParam requestParam = new RequestParam(UrlConstant.CONSULATION_LIST,convertBizContent(content));
        return mService.simpleRequest(requestParam);
    }
}
