package com.yuyuka.billiards.mvp.presenter.news;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuyuka.billiards.base.BasePresenter;
import com.yuyuka.billiards.mvp.contract.news.NewsListContract;
import com.yuyuka.billiards.mvp.model.NewsModel;
import com.yuyuka.billiards.net.RespObserver;
import com.yuyuka.billiards.pojo.ListData;
import com.yuyuka.billiards.pojo.NewsItem;
import com.yuyuka.billiards.utils.CollectionUtils;
import com.yuyuka.billiards.utils.RxUtils;

import java.lang.reflect.Type;

public class NewsListPresenter extends BasePresenter<NewsListContract.INewsListView, NewsListContract.INewsListModel> {
    public NewsListPresenter(NewsListContract.INewsListView view) {
        super(view, new NewsModel());
    }

    public void getNewsList(String keywords, int queryType,int page){
        getView().showLoading();
        mModel.getNewsList(keywords,queryType,page)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {
                    @Override
                    public void onResult(String msg, String bizContent) {
                        getView().hideLoading();
                        if (TextUtils.isEmpty(bizContent)){
                            getView().showEmpty();
                            return;
                        }
                        Type type = new TypeToken<ListData<NewsItem>>(){}.getType();
                        ListData<NewsItem> data = new Gson().fromJson(bizContent,type);
                        if (data == null){
                            getView().showEmpty();
                            return;
                        }
                        if (CollectionUtils.isEmpty(data.getDataList()))
                            getView().showEmpty();
                        else
                            getView().showNewsList(data.getDataList());
                    }
                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().hideLoading();
                        getView().showError(errMsg);
                    }
                });

        }
}
