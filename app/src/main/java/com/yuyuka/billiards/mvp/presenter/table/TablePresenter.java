package com.yuyuka.billiards.mvp.presenter.table;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.yuyuka.billiards.base.BasePresenter;
import com.yuyuka.billiards.mvp.contract.table.TableContract;
import com.yuyuka.billiards.mvp.model.TableModel;
import com.yuyuka.billiards.net.RespObserver;
import com.yuyuka.billiards.pojo.OrderPojo;
import com.yuyuka.billiards.pojo.TablePojo;
import com.yuyuka.billiards.utils.RxUtils;

public class TablePresenter extends BasePresenter<TableContract.ITableView, TableContract.ITableModel> {
    public TablePresenter(TableContract.ITableView view) {
        super(view,new TableModel());
    }

    public void getTableInfo(long tableId){
        getView().showProgressDialog();
        mModel.getTableInfo(tableId)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {

                    @Override
                    public void onResult(String msg, String bizContent) {
                        getView().dismissProgressDialog();
                        if (TextUtils.isEmpty(bizContent)){
                            return;
                        }
                        TablePojo data = new Gson().fromJson(bizContent,TablePojo.class);
                        getView().showTableInfo(data);

                    }

                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().dismissProgressDialog();
                        getView().showError(errMsg);
                    }
                });

    }


    public void openTable(long tableId,int payMode,int competitionType){
        getView().showProgressDialog();
        mModel.tackOrder(0,tableId,competitionType,1,payMode)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {

                    @Override
                    public void onResult(String msg, String bizContent) {
                        getView().dismissProgressDialog();
                        if (TextUtils.isEmpty(bizContent)){
                            return;
                        }
                        OrderPojo data = new Gson().fromJson(bizContent,OrderPojo.class);
                        getView().showOrderSuccess(data);

                    }

                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().dismissProgressDialog();
                        getView().showError(errMsg);
                    }
                });
    }

    public void enterMatch(int id,int refOrderId,int payChannel){

    }

}
