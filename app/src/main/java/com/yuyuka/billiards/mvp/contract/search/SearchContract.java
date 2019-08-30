package com.yuyuka.billiards.mvp.contract.search;

import com.yuyuka.billiards.base.IBaseModel;
import com.yuyuka.billiards.base.IBaseView;
import com.yuyuka.billiards.net.HttpResult;

import io.reactivex.Observable;

public interface SearchContract  {
    interface ISearchView extends IBaseView{
        void showResultList();
    }

    interface  ISearchModel extends IBaseModel{
        Observable<HttpResult> getResult(String keywords,double latitude,double longitude,int page);
    }
}
