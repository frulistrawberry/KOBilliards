package com.yuyuka.billiards.mvp.presenter.search;


import com.yuyuka.billiards.base.BasePresenter;
import com.yuyuka.billiards.mvp.contract.search.SearchContract;
import com.yuyuka.billiards.mvp.model.SearchModel;
import com.yuyuka.billiards.net.RespObserver;
import com.yuyuka.billiards.utils.RxUtils;


public class IndexSearchPresenter extends BasePresenter<SearchContract.ISearchView, SearchContract.ISearchModel> {
    public IndexSearchPresenter(SearchContract.ISearchView view) {
        super(view, new SearchModel());
    }

    public void searchIndex(String keywords,double lat,double lng,int page){
        mModel.getResult(keywords,lat,lng,page)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {

                    @Override
                    public void onResult(String msg, String bizContent) {


                    }

                    @Override
                    public void onError(int errCode, String errMsg) {

                    }
                });

    }
}
