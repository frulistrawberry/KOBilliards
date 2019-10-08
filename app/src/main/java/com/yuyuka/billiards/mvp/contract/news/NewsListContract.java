package com.yuyuka.billiards.mvp.contract.news;

import com.yuyuka.billiards.base.IBaseModel;
import com.yuyuka.billiards.base.IBaseView;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.pojo.BilliardsUsers;
import com.yuyuka.billiards.pojo.NewsItem;

import java.util.List;

import io.reactivex.Observable;

public interface NewsListContract {
    interface INewsListView extends IBaseView {
        void showNewsList(List<NewsItem> newsList);
        void showUserList(List<BilliardsUsers> userList);
    }

    interface INewsListModel extends IBaseModel {
        Observable<HttpResult> getNewsList(String keywords,int queryType,int page);
    }
}
