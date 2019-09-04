package com.yuyuka.billiards.mvp.contract.market;

import com.yuyuka.billiards.base.IBaseModel;
import com.yuyuka.billiards.base.IBaseView;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.pojo.GoodsPojo;

import java.util.List;

import io.reactivex.Observable;

public interface GoodsListContract {
    interface IGoodsListView extends IBaseView {
        void showGoodsList(List<GoodsPojo> dataList);
    }

    interface IGoodsListModel extends IBaseModel {
        Observable<HttpResult> getGoodsList(String keywords,int sortCondition, int typeCondition,
                                            int quickCondition, int lowPrice,int highPrice,
                                            int releaseTimeCondition,int otherCondition , int page);
    }
}
