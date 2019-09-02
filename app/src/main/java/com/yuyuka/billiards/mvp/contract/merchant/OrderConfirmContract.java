package com.yuyuka.billiards.mvp.contract.merchant;

import com.yuyuka.billiards.base.IBaseModel;
import com.yuyuka.billiards.base.IBaseView;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.pojo.TaoCan;

import java.util.List;

import io.reactivex.Observable;

public interface OrderConfirmContract {
    interface IOrderConfirmView extends IBaseView {
        void showPackages(List<TaoCan> data);
    }

    interface IOrderConfirmModel extends IBaseModel{
        Observable<HttpResult> getPackages(String billiardsInfoId);
    }
}
