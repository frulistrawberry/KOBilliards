package com.yuyuka.billiards.mvp.contract.nearbymatch;


import com.yuyuka.billiards.base.IBaseModel;
import com.yuyuka.billiards.base.IBaseView;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.pojo.BilliardsMatchPojo;
import com.yuyuka.billiards.pojo.BilliardsRoomPojo;

import java.util.List;

import io.reactivex.Observable;

public interface RecommendMatchContract {
    interface IRecommendMatchView extends IBaseView {
        void showRecommendMatchList(List<BilliardsMatchPojo> matchList);
    }

    interface  IRecommendMatchModel extends IBaseModel {
        Observable<HttpResult> getRecommendMatchList(int page, String sort, String filter);
    }
}
