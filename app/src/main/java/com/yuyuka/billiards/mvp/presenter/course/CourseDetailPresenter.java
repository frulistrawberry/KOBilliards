package com.yuyuka.billiards.mvp.presenter.course;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuyuka.billiards.base.BasePresenter;
import com.yuyuka.billiards.mvp.contract.course.CourseDetailContract;
import com.yuyuka.billiards.mvp.model.CourseModel;
import com.yuyuka.billiards.net.RespObserver;
import com.yuyuka.billiards.pojo.KOClassPojo;
import com.yuyuka.billiards.pojo.KOListPojo;
import com.yuyuka.billiards.utils.CollectionUtils;
import com.yuyuka.billiards.utils.RxUtils;

import java.lang.reflect.Type;
import java.util.List;

public class CourseDetailPresenter extends BasePresenter<CourseDetailContract.ICourseDetailView, CourseDetailContract.ICourseDetailModel> {
    public CourseDetailPresenter(CourseDetailContract.ICourseDetailView view) {
        super(view, new CourseModel());
    }

    public void getCourseInfo(int id){
        getView().showLoading();
        mModel.getCourseInfo(id)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {

                    @Override
                    public void onResult(String msg, String bizContent) {
                        getView().hideLoading();

                        if (TextUtils.isEmpty(bizContent)){
                            getView().showEmpty();
                            return;
                        }
                        KOListPojo data = new Gson().fromJson(bizContent,KOListPojo.class);

                        getView().showCourseInfo(data);
                    }

                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().hideLoading();
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
                        getView().showCollectSuccess(msg);

                    }
                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().dismissProgressDialog();
                        getView().showCollectSuccess(errMsg);
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
                        getView().showCollectSuccess(msg);
                    }

                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().dismissProgressDialog();
                        getView().showError(errMsg);
                    }
                });
    }
}
