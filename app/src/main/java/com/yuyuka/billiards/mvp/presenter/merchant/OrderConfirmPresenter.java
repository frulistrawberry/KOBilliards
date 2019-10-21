package com.yuyuka.billiards.mvp.presenter.merchant;

import android.text.TextUtils;

import com.autonavi.ae.guide.observer.GSoundPlayObserver;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuyuka.billiards.base.BasePresenter;
import com.yuyuka.billiards.mvp.contract.merchant.OrderConfirmContract;
import com.yuyuka.billiards.mvp.model.MerchantModel;
import com.yuyuka.billiards.net.RespObserver;
import com.yuyuka.billiards.pojo.BilliardsRoomPojo;
import com.yuyuka.billiards.pojo.GoodsAmount;
import com.yuyuka.billiards.pojo.ListData;
import com.yuyuka.billiards.pojo.TaoCan;
import com.yuyuka.billiards.utils.CollectionUtils;
import com.yuyuka.billiards.utils.RxUtils;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.http.PUT;

public class OrderConfirmPresenter extends BasePresenter<OrderConfirmContract.IOrderConfirmView, OrderConfirmContract.IOrderConfirmModel> {
    public OrderConfirmPresenter(OrderConfirmContract.IOrderConfirmView view) {
        super(view, new MerchantModel());
    }

    public void getSetmeal(String id){
        getView().showLoading();
        mModel.getPackages(id)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {

                    @Override
                    public void onResult(String msg, String bizContent) {
                        if (TextUtils.isEmpty(bizContent)){
                            getView().showEmpty();
                            return;
                        }
                        Type type = new TypeToken<List<TaoCan>>(){}.getType();
                        List<TaoCan> data = new Gson().fromJson(bizContent,type);
                        getView().showPackages(data);
                        getView().hideLoading();

                    }

                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().hideLoading();
                        getView().showError(errMsg);
                    }
                });
    }

    public void getGoodsAmount(int goodsId,String startDate,String endDate){
        getView().showLoading();
        mModel.getAmount(goodsId,startDate,endDate)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {

                    @Override
                    public void onResult(String msg, String bizContent) {
                        if (TextUtils.isEmpty(bizContent)){
                            getView().showEmpty();
                            return;
                        }
                        GoodsAmount data = new Gson().fromJson(bizContent,GoodsAmount.class);
                        getView().showAmount(data);

                    }

                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().hideLoading();
                        getView().showError(errMsg);
                    }
                });
    }
}
