package com.yuyuka.billiards.mvp.contract.merchant;

import com.yuyuka.billiards.base.IBaseModel;
import com.yuyuka.billiards.base.IBaseView;
import com.yuyuka.billiards.net.HttpResult;

import java.util.List;

import io.reactivex.Observable;

public interface MerchantCommentContract {
    interface IMerchantCommentView extends IBaseView{
        void showCommentSuccess();
    }

    interface IMerchantCommentModel extends IBaseModel{
        Observable<HttpResult> comment(String id, String messageInfo, List<String> gameTypeList,int population,int local,int service,int hygiene,int facilities);
    }
}
