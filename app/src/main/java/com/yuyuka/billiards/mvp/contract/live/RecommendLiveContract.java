package com.yuyuka.billiards.mvp.contract.live;

import com.yuyuka.billiards.base.IBaseModel;
import com.yuyuka.billiards.base.IBaseView;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.pojo.LivePojo;

import java.util.List;

import io.reactivex.Observable;

public interface RecommendLiveContract {
    interface IRecommendLiveView extends IBaseView {
        void showRecommendLiveList(List<LivePojo> dataList);
    }

    interface IRecommendLiveModel extends IBaseModel{
        Observable<HttpResult> getRecommendLiveList(int page);
    }
}
