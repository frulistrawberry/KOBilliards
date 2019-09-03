package com.yuyuka.billiards.mvp.contract.match;

import com.yuyuka.billiards.base.IBaseModel;
import com.yuyuka.billiards.base.IBaseView;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.pojo.MatchBonusPojo;
import com.yuyuka.billiards.pojo.MatchDetailPojo;

import java.util.List;

import io.reactivex.Observable;

public interface MatchDetailContract {
    interface IMatchDetailView extends IBaseView{
        void showMatchDetail(MatchDetailPojo data);
        void showMatchBonus(List<MatchBonusPojo> data);
    }

    interface IMatchDetailModel extends IBaseModel{
        Observable<HttpResult> getMatchDetail(String matchId);
        Observable<HttpResult> getMatchBonus(String matchId);
    }
}
