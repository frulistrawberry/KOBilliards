package com.yuyuka.billiards.mvp.presenter.ko;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuyuka.billiards.base.BasePresenter;
import com.yuyuka.billiards.mvp.contract.ko.KOTypeContract;
import com.yuyuka.billiards.mvp.model.KOModel;
import com.yuyuka.billiards.net.RespObserver;
import com.yuyuka.billiards.pojo.BilliardsMatchPojo;
import com.yuyuka.billiards.pojo.KOClassPojo;
import com.yuyuka.billiards.pojo.ListData;
import com.yuyuka.billiards.utils.CollectionUtils;
import com.yuyuka.billiards.utils.RxUtils;

import java.lang.reflect.Type;
import java.util.List;

public class KOClassPresenter extends BasePresenter<KOTypeContract.IKOTypeView, KOTypeContract.IKOTypeModel> {
    public KOClassPresenter(KOTypeContract.IKOTypeView view) {
        super(view, new KOModel());
    }

    public void getCrippleTypeList(int modelType){
        getView().showLoading();
        mModel.getKOClassList(modelType)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {

                    @Override
                    public void onResult(String msg, String bizContent) {
                        getView().hideLoading();

                        if (TextUtils.isEmpty(bizContent)){
                            getView().showEmpty();
                            return;
                        }
                        Type type = new TypeToken<List<KOClassPojo>>(){}.getType();
                        List<KOClassPojo> data = new Gson().fromJson(bizContent,type);

                        if (CollectionUtils.isEmpty(data))
                            getView().showEmpty();
                        else
                            getView().showKOClassList(data);
                    }

                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().hideLoading();
                        getView().showError(errMsg);
                    }
                });
    }
}
