package com.yuyuka.billiards.mvp.contract.match;

import com.yuyuka.billiards.base.IBaseModel;
import com.yuyuka.billiards.base.IBaseView;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.pojo.TablePojo;

import io.reactivex.Observable;

public interface TableContract {

    interface ITableView extends IBaseView{
        void showTableInfo(TablePojo data);

    }

    interface ITableModel extends IBaseModel {
        Observable<HttpResult> getTableInfo(long tableId);
        Observable<HttpResult> getOrder(long tableId);
    }
}
