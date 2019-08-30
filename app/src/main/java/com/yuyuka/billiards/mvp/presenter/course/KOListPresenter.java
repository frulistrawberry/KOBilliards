package com.yuyuka.billiards.mvp.presenter.course;


import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuyuka.billiards.base.BasePresenter;
import com.yuyuka.billiards.mvp.contract.course.KOListContract;
import com.yuyuka.billiards.mvp.model.CourseModel;
import com.yuyuka.billiards.net.RespObserver;
import com.yuyuka.billiards.pojo.KOListPojo;
import com.yuyuka.billiards.utils.CollectionUtils;
import com.yuyuka.billiards.utils.RxUtils;

import java.lang.reflect.Type;
import java.util.List;


public class KOListPresenter extends BasePresenter<KOListContract.IKOListView, KOListContract.IKOListModel> {

    public KOListPresenter(KOListContract.IKOListView view) {
        super(view,new CourseModel());
    }

    public void getKOList(int id,int page){
        getView().showLoading();
        mModel.getKOList(id,page)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {

                    @Override
                    public void onResult(String msg, String bizContent) {
                        getView().hideLoading();

                        if (TextUtils.isEmpty(bizContent)){
                            getView().showEmpty();
                            return;
                        }
                        Type type = new TypeToken<List<KOListPojo>>(){}.getType();
                        List<KOListPojo> data = new Gson().fromJson(bizContent,type);

                        if (CollectionUtils.isEmpty(data))
                            getView().showEmpty();
                        else
                            getView().showKOList(data);
                    }

                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().hideLoading();
                        getView().showError(errMsg);
                    }
                });

    }
}
