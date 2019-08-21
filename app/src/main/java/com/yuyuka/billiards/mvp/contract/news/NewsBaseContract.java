package com.yuyuka.billiards.mvp.contract.news;

import com.yuyuka.billiards.base.IBaseModel;
import com.yuyuka.billiards.base.IBaseView;

import io.reactivex.Observable;


public interface NewsBaseContract {
    interface INewsBaseView extends IBaseView{
        void showAttentionSuccess();
        void showAttentionFailure();
        void showCommentSuccess();
        void showCommentFailure();
        void showPraiseSuccess();
        void showPraiseFailure();
    }

    interface INewsBaseModel extends IBaseModel{

    }
}
