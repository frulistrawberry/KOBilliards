package com.yuyuka.billiards.mvp.contract.news;

import com.yuyuka.billiards.base.IBaseModel;
import com.yuyuka.billiards.base.IBaseView;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.pojo.NewsCommentItem;
import com.yuyuka.billiards.pojo.NewsItem;
import com.yuyuka.billiards.pojo.NewsReplyItem;

import java.util.List;

import io.reactivex.Observable;


public interface NewsContract {
    interface INewsView extends IBaseView{
        void showAttentionSuccess(String msg);
        void showAttentionFailure(String msg);
        void showCommentSuccess(String msg);
        void showCommentFailure(String msg);
        void showPraiseSuccess(String msg);
        void showPraiseFailure(String msg);
        void showCommentList(List<NewsCommentItem> data);
        void showNewsInfo(NewsItem data);
        void showReplyList(List<NewsReplyItem> data);
        void showDisAttenttionSuccess(String msg);
        void showDisAttentionFailure(String msg);

    }

    interface INewsModel extends IBaseModel{
        Observable<HttpResult> getNewsComment(int consultationId,int page);
        Observable<HttpResult> getReplyList(int messageId,int page);
        Observable<HttpResult> attention(int followId);
        Observable<HttpResult> disattention(int followId);
        Observable<HttpResult> comment(int consultationId,String content);
        Observable<HttpResult> praise(int bizId);
        Observable<HttpResult> getNewsInfo(int consultationId);
        Observable<HttpResult> reply(int consultationId,String content);
    }
}
