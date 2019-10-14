package com.yuyuka.billiards.mvp.contract.table;

import com.yuyuka.billiards.base.IBaseModel;
import com.yuyuka.billiards.base.IBaseView;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.pojo.OrderPojo;
import com.yuyuka.billiards.pojo.TablePojo;

import io.reactivex.Observable;

public interface PointContract {

    interface IPointView extends IBaseView {

    }

    interface IPointModel extends IBaseModel {
        Observable<HttpResult> sendPoint(int id,int userId,int point);
        Observable<HttpResult> confirm(int id);
    }
}
