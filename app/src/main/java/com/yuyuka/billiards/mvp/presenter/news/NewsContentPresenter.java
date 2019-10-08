package com.yuyuka.billiards.mvp.presenter.news;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuyuka.billiards.base.BasePresenter;
import com.yuyuka.billiards.mvp.contract.news.NewsContract;
import com.yuyuka.billiards.mvp.model.NewsModel;
import com.yuyuka.billiards.net.RespObserver;
import com.yuyuka.billiards.pojo.ListData;
import com.yuyuka.billiards.pojo.NewsCommentItem;
import com.yuyuka.billiards.pojo.NewsItem;
import com.yuyuka.billiards.pojo.NewsReplyItem;
import com.yuyuka.billiards.utils.CollectionUtils;
import com.yuyuka.billiards.utils.RxUtils;

import java.lang.reflect.Type;

public class NewsContentPresenter extends BasePresenter<NewsContract.INewsView, NewsContract.INewsModel> {
    public NewsContentPresenter(NewsContract.INewsView view) {
        super(view, new NewsModel());
    }

    public void getNewsComment(int consultationId,int page){
        getView().showLoading();
        mModel.getNewsComment(consultationId,page)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {
                    @Override
                    public void onResult(String msg, String bizContent) {
                        getView().hideLoading();
                        if (TextUtils.isEmpty(bizContent)){
                            getView().showEmpty();
                            return;
                        }
                        Type type = new TypeToken<ListData<NewsCommentItem>>(){}.getType();
                        ListData<NewsCommentItem> data = new Gson().fromJson(bizContent,type);
                        if (data == null){
                            getView().showEmpty();
                            return;
                        }
                        if (CollectionUtils.isEmpty(data.getDataList()))
                            getView().showEmpty();
                        else
                            getView().showCommentList(data.getDataList());
                    }
                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().hideLoading();
                        getView().showError(errMsg);
                    }
                });
    }

    public void getReplyList(int messageId,int page){
        getView().showLoading();
        mModel.getReplyList(messageId,page)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {
                    @Override
                    public void onResult(String msg, String bizContent) {
                        getView().hideLoading();
                        if (TextUtils.isEmpty(bizContent)){
                            getView().showEmpty();
                            return;
                        }
                        Type type = new TypeToken<ListData<NewsReplyItem>>(){}.getType();
                        ListData<NewsReplyItem> data = new Gson().fromJson(bizContent,type);
                        if (data == null){
                            getView().showEmpty();
                            return;
                        }
                        if (CollectionUtils.isEmpty(data.getDataList()))
                            getView().showEmpty();
                        else
                            getView().showReplyList(data.getDataList());
                    }
                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().hideLoading();
                        getView().showError(errMsg);
                    }
                });
    }


    public void getNewsInfo(int consultationId){
        getView().showProgressDialog();
        mModel.getNewsInfo(consultationId)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {
                    @Override
                    public void onResult(String msg, String bizContent) {
                        getView().dismissProgressDialog();
                        NewsItem newsInfo = new Gson().fromJson(bizContent,NewsItem.class);
                        getView().showNewsInfo(newsInfo);

                    }
                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().dismissProgressDialog();
                        getView().showError(errMsg);
                    }
                });
    }

    public void comment(int consultationId,String content){
        getView().showProgressDialog();
        mModel.comment(consultationId,content)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {
                    @Override
                    public void onResult(String msg, String bizContent) {
                        getView().dismissProgressDialog();
                        getView().showCommentSuccess(msg);

                    }
                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().dismissProgressDialog();
                        getView().showCommentFailure(errMsg);
                    }
                });
    }

    public void attention(int followId){
        getView().showProgressDialog();
        mModel.attention(followId)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {
                    @Override
                    public void onResult(String msg, String bizContent) {
                        getView().dismissProgressDialog();
                        getView().showAttentionSuccess(msg);

                    }
                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().dismissProgressDialog();
                        getView().showAttentionFailure(errMsg);
                    }
                });
    }

    public void disattention(int followId){
        getView().showProgressDialog();
        mModel.disattention(followId)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {
                    @Override
                    public void onResult(String msg, String bizContent) {
                        getView().dismissProgressDialog();
                        getView().showDisAttenttionSuccess(msg);

                    }
                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().dismissProgressDialog();
                        getView().showDisAttenttionSuccess(errMsg);
                    }
                });
    }


    public void appreciate(int id){
        getView().showProgressDialog();
        mModel.praise(id)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {
                    @Override
                    public void onResult(String msg, String bizContent) {
                        getView().dismissProgressDialog();
                        getView().showAttentionSuccess(msg);

                    }
                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().dismissProgressDialog();
                        getView().showAttentionFailure(errMsg);
                    }
                });
    }

    public void reply(int consultationId,String content){
        getView().showProgressDialog();
        mModel.reply(consultationId,content)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {
                    @Override
                    public void onResult(String msg, String bizContent) {
                        getView().dismissProgressDialog();
                        getView().showCommentSuccess(msg);

                    }
                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().dismissProgressDialog();
                        getView().showCommentFailure(errMsg);
                    }
                });
    }


}
