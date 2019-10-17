package com.yuyuka.billiards.mvp.contract;

import com.yuyuka.billiards.base.BaseModel;
import com.yuyuka.billiards.base.IBaseModel;
import com.yuyuka.billiards.base.IBaseView;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.pojo.CustomNoticePojo;
import com.yuyuka.billiards.pojo.CustomNoticePojo.Battle;

import java.util.List;

import io.reactivex.Observable;

public interface HomeContract {
    interface IHomeView extends IBaseView {
        void showTable(List<Battle> data);
    }

    interface IHomeModel extends IBaseModel {
        Observable<HttpResult> myTable();
        Observable<HttpResult> getBattle();
    }
}
