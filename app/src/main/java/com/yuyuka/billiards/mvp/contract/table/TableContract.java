package com.yuyuka.billiards.mvp.contract.table;

import com.yuyuka.billiards.base.IBaseModel;
import com.yuyuka.billiards.base.IBaseView;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.pojo.OrderPojo;
import com.yuyuka.billiards.pojo.TablePojo;

import io.reactivex.Observable;

public interface TableContract {

    interface ITableView extends IBaseView {
        void showTableInfo(TablePojo data);
        void showOrderSuccess(OrderPojo data);
        void showOrderFailure(String msg);
        void showEnterSuccess();
        void showEnterFailure();

    }

    interface ITableModel extends IBaseModel {
        Observable<HttpResult> getTableInfo(long tableId);
        Observable<HttpResult> tackOrder(int orderType,long billiardsPoolTable,int competitionType,int payType,int payChannel);
        Observable<HttpResult> enterMatch(int id,int refOrderId,int payChannel);
    }
}
