package com.yuyuka.billiards.mvp.presenter.market;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuyuka.billiards.base.BasePresenter;
import com.yuyuka.billiards.mvp.contract.market.GoodsDetailContract;
import com.yuyuka.billiards.mvp.model.MarketModel;
import com.yuyuka.billiards.net.RespObserver;
import com.yuyuka.billiards.pojo.GoodsComment;
import com.yuyuka.billiards.pojo.GoodsPojo;
import com.yuyuka.billiards.pojo.ListData;
import com.yuyuka.billiards.utils.CollectionUtils;
import com.yuyuka.billiards.utils.RxUtils;

import java.lang.reflect.Type;


public class GoodsDetailPresenter extends BasePresenter<GoodsDetailContract.IGoodsDetailView, GoodsDetailContract.IGoodsDetailModel> {
    public GoodsDetailPresenter(GoodsDetailContract.IGoodsDetailView view) {
        super(view, new MarketModel());
    }

    public void getGoodsInfo(int id){
        getView().showProgressDialog();
        mModel.getGoodsInfo(id)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {
                    @Override
                    public void onResult(String msg, String bizEntity) {
                        getView().dismissProgressDialog();
                        if (TextUtils.isEmpty(bizEntity)){
                            getView().showEmpty();
                            return;
                        }
                        GoodsPojo data = new Gson().fromJson(bizEntity,GoodsPojo.class);
                        getView().showGoodsInfo(data);
                    }

                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().dismissProgressDialog();
                        getView().showError(errMsg);
                    }
                });
    }
    public void getCommentList(int id,int page){
        getView().showLoading();
        mModel.getCommentList(id,page)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {
                    @Override
                    public void onResult(String msg, String bizEntity) {
                        getView().hideLoading();
                        if (TextUtils.isEmpty(bizEntity)){
                            getView().showEmpty();
                            return;
                        }
                        Type type = new TypeToken<ListData<GoodsComment>>(){}.getType();
                        ListData<GoodsComment> data = new Gson().fromJson(bizEntity,type);

                        if (CollectionUtils.isEmpty(data.getDataList()))
                            getView().showEmpty();
                        else
                            getView().showCommentList(data.getDataList(),data.getTotalCount());
                    }

                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().showError(errMsg);
                    }
                });
    }

    public void comment(int id,String content){
        getView().showProgressDialog();
        mModel.comment(id,content)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {
                    @Override
                    public void onResult(String msg, String bizEntity) {
                        getView().dismissProgressDialog();
                        getView().showCommentSuccess(msg);

                    }

                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().dismissProgressDialog();
                        getView().showError(errMsg);
                    }
                });

    }

    public void want(int id){
        getView().showProgressDialog();
        mModel.want(id)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {
                    @Override
                    public void onResult(String msg, String bizEntity) {
                        getView().dismissProgressDialog();
                        getView().showWantSuccess(msg);

                    }

                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().dismissProgressDialog();
                        getView().showError(errMsg);
                    }
                });

    }

    public void appreciate(int id){
        getView().showProgressDialog();
        mModel.praise(id)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {
                    @Override
                    public void onResult(String msg, String bizContent) {
                        getView().dismissProgressDialog();
                        getView().showWantSuccess(msg);

                    }
                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().dismissProgressDialog();
                        getView().showError(errMsg);
                    }
                });
    }

    public void collect(int merchantId){
        getView().showProgressDialog();
        mModel.collect(merchantId)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {

                    @Override
                    public void onResult(String msg, String bizContent) {
                        getView().dismissProgressDialog();
                        getView().showCommentSuccess(msg);
                    }

                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().dismissProgressDialog();
                        getView().showCommentSuccess(errMsg);
                    }
                });
    }


}
