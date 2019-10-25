package com.yuyuka.billiards.mvp.contract.mine;

import com.yuyuka.billiards.base.IBaseModel;
import com.yuyuka.billiards.base.IBaseView;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.pojo.MinePojo;

import io.reactivex.Observable;

public interface MineContract {
    interface IMineView extends IBaseView {
        void showMineData(MinePojo data);
    }

    interface IMinewModel extends IBaseModel{
        Observable<HttpResult> getMineData();
    }
}
