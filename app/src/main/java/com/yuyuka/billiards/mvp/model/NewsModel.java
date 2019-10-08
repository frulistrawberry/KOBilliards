package com.yuyuka.billiards.mvp.model;

import android.text.TextUtils;

import com.yuyuka.billiards.base.BaseModel;
import com.yuyuka.billiards.constants.UrlConstant;
import com.yuyuka.billiards.mvp.contract.news.NewsContract;
import com.yuyuka.billiards.mvp.contract.news.NewsListContract;
import com.yuyuka.billiards.mvp.contract.news.ReleaseContract;
import com.yuyuka.billiards.net.BizContent;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.net.RequestParam;
import com.yuyuka.billiards.utils.CommonUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;

public class NewsModel extends BaseModel implements NewsListContract.INewsListModel, NewsContract.INewsModel, ReleaseContract.IReleaseModel {


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
            else if (queryType == 4){
                params.put("queryLevel","main");
                params.put("-consultationType",0);
            }
            content.setParams(params);
            content.buildPageQueryDto(page);
            RequestParam requestParam = new RequestParam(UrlConstant.SEARCH_BIZ,convertBizContent(content));
            return mService.simpleRequest(requestParam);
        }else {
            BizContent content = new BizContent();
            content.buildPageQueryDto(page);
            content.setUserId(CommonUtils.getUserId());
            RequestParam requestParam = new RequestParam(UrlConstant.AUTHORIZED_CONSULATION_LIST_FOLLOW,convertBizContent(content));
            return mService.simpleRequest(requestParam);
        }

    }

    /**
     * 获取资讯评论列表
     * @param consultationId
     * @param page
     * @return
     */
    @Override
    public Observable<HttpResult> getNewsComment(int consultationId, int page) {
        BizContent content = new BizContent();
        content.buildPageQueryDto(page);
        content.setConsultationId(consultationId);
        RequestParam requestParam = new RequestParam(UrlConstant.CONSULTATION_MESSAGE_LIST,convertBizContent(content));
        return mService.simpleRequest(requestParam);
    }

    @Override
    public Observable<HttpResult> getReplyList(int messageId, int page) {
        BizContent content = new BizContent();
        content.buildPageQueryDto(page);
        content.setMessageId(messageId);
        RequestParam requestParam = new RequestParam(UrlConstant.CONSULTATION_MESSAGE_REPLY_LIST,convertBizContent(content));
        return mService.simpleRequest(requestParam);
    }

    /**
     * 用户关注
     * @param followId
     * @return
     */
    @Override
    public Observable<HttpResult> attention(int followId) {
        BizContent content = new BizContent();
        content.setFollowId(followId);
        content.setUserId(CommonUtils.getUserId());
        RequestParam requestParam = new RequestParam(UrlConstant.AUTHORIZED_USER_PUT_FOLLOW,convertBizContent(content));
        return mService.simpleRequest(requestParam);
    }

    @Override
    public Observable<HttpResult> disattention(int followId) {
        BizContent content = new BizContent();
        content.setFollowId(followId);
        content.setUserId(CommonUtils.getUserId());
        RequestParam requestParam = new RequestParam(UrlConstant.AUTHORIZED_USER_DEL_FOLLOW,convertBizContent(content));
        return mService.simpleRequest(requestParam);
    }

    @Override
    public Observable<HttpResult> comment(int consultationId, String content) {
        BizContent bizContent = new BizContent();
        bizContent.setContent(content);
        bizContent.setConsultationId(consultationId);
        bizContent.setUserId(CommonUtils.getUserId());
        RequestParam requestParam = new RequestParam(UrlConstant.AUTHORIZED_CONSULTATION_MESSAGE_PUT,convertBizContent(bizContent));
        return mService.simpleRequest(requestParam);
    }

    @Override
    public Observable<HttpResult> praise(int bizId) {
        BizContent bizContent = new BizContent();
        bizContent.setBizId(bizId);
        bizContent.setBizType(5);
        bizContent.setUserId(CommonUtils.getUserId());
        RequestParam requestParam = new RequestParam(UrlConstant.AUTHORIZED_USER_PUT_APPRECIATE,convertBizContent(bizContent));
        return mService.simpleRequest(requestParam);
    }

    @Override
    public Observable<HttpResult> getNewsInfo(int consultationId) {
        BizContent bizContent = new BizContent();
        bizContent.setId(consultationId);
        RequestParam requestParam = new RequestParam(UrlConstant.CONSULATION_GET,convertBizContent(bizContent));
        return mService.simpleRequest(requestParam);
    }

    @Override
    public Observable<HttpResult> reply(int consultationId, String content) {
        BizContent bizContent = new BizContent();
        bizContent.setReplyContent(content);
        bizContent.setMessageId(consultationId);
        bizContent.setUserId(CommonUtils.getUserId());
        RequestParam requestParam = new RequestParam(UrlConstant.AUTHORIZED_CONSULTATION_MESSAGE_REPLY_PUT,convertBizContent(bizContent));
        return mService.simpleRequest(requestParam);
    }

    @Override
    public Observable<HttpResult> releaseNews(int consultationType, int viewLongtime, String address, String coverImageAdd, String contentInfo, String title, String billiardsConsultationTagDtoIds) {
        BizContent bizContent = new BizContent();
        bizContent.setConsultationType(consultationType);
        bizContent.setViewLongtime(viewLongtime);
        bizContent.setAddress(address);
        bizContent.setCoverImageAdd(coverImageAdd);
        bizContent.setContentInfo(contentInfo);
        bizContent.setTitle(title);
        bizContent.setBilliardsConsultationTagDtoIds(billiardsConsultationTagDtoIds);
        bizContent.setUserId(CommonUtils.getUserId());
        RequestParam requestParam = new RequestParam(UrlConstant.AUTHORIZED_AUTHORIZED_CONSULTATION_PUT,convertBizContent(bizContent));
        return mService.simpleRequest(requestParam);
    }

    @Override
    public Observable<HttpResult> getTags() {
        RequestParam requestParam = new RequestParam(UrlConstant.AUTHORIZED_CONSULTATION_LIST_TAG_CONFIG,convertBizContent(null));
        return mService.simpleRequest(requestParam);
    }
}
