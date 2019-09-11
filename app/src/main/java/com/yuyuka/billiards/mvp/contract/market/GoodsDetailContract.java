package com.yuyuka.billiards.mvp.contract.market;

import com.yuyuka.billiards.base.IBaseModel;
import com.yuyuka.billiards.base.IBaseView;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.pojo.GoodsComment;
import com.yuyuka.billiards.pojo.GoodsPojo;

import java.util.List;

import io.reactivex.Observable;

public interface GoodsDetailContract {
    interface IGoodsDetailView extends IBaseView {
        void showGoodsInfo(GoodsPojo data);
        void showCommentList(List<GoodsComment> data,int totalCount);
        void showCommentSuccess(String msg);
        void showWantSuccess(String msg);
    }

    interface IGoodsDetailModel extends IBaseModel{
        Observable<HttpResult> getGoodsInfo(int id);
        Observable<HttpResult> getCommentList(int billiardsSecondMallId,int page);
        Observable<HttpResult> comment(int billiardsSecondMallId,String content);
        Observable<HttpResult> want(int billiardsSecondMallId);
    }
}
