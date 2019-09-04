package com.yuyuka.billiards.mvp.model;

import android.text.TextUtils;

import com.yuyuka.billiards.base.BaseModel;
import com.yuyuka.billiards.constants.UrlConstant;
import com.yuyuka.billiards.mvp.contract.news.NewsListContract;
import com.yuyuka.billiards.net.BizContent;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.net.RequestParam;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;

public class NewsModel extends BaseModel implements NewsListContract.INewsListModel {


    /**
     * 资讯列表
     * @param page 页码
     * @param queryType 类型 0文章 1小视频 2视频 3推荐 4首页 5关注
     * @param keywords 搜索关键字
     * @return
     */
    @Override
    public Observable<HttpResult> getNewsList(String keywords,int queryType,int page) {
        if (queryType < 5){
            BizContent content = new BizContent();
            content.setQueryType(5);
            if (!TextUtils.isEmpty(keywords))
                content.setKeyword(keywords);
            Map<String,Object> params = new HashMap<>();
            if (queryType<3)
                params.put("consultationType",queryType);
            else if (queryType == 3)
                params.put("queryLevel","recommend");
            else if (queryType == 4)
                params.put("queryLevel","main");
            content.setParams(params);
            content.buildPageQueryDto(page);
            RequestParam requestParam = new RequestParam(UrlConstant.SEARCH_BIZ,convertBizContent(content));
            return mService.simpleRequest(requestParam);
        }else {
            BizContent content = new BizContent();
            content.buildPageQueryDto(page);
            content.setUserId(12);
            RequestParam requestParam = new RequestParam(UrlConstant.AUTHORIZED_CONSULATION_LIST_FOLLOW,convertBizContent(content));
            return mService.simpleRequest(requestParam);
        }

    }
}
