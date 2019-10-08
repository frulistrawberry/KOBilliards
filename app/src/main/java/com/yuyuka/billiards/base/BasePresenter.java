package com.yuyuka.billiards.base;



import com.google.gson.Gson;
import com.trello.rxlifecycle2.internal.Preconditions;
import com.yuyuka.billiards.net.RespObserver;
import com.yuyuka.billiards.pojo.UploadResult;
import com.yuyuka.billiards.utils.RxUtils;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;



public class BasePresenter<V extends IBaseView,M extends IBaseModel> implements IBasePresenter<V> {
    protected Reference<V> mView;
    protected M mModel;

    public BasePresenter(V view,M model) {
        Preconditions.checkNotNull(model, String.format("%s cannot be null", IBaseModel.class.getName()));
        Preconditions.checkNotNull(view, String.format("%s cannot be null", IBaseView.class.getName()));
        this.mModel = model;
        attachView(view);
    }

    @Override
    public void attachView(V view) {
        mView = new WeakReference<>(view);
    }

    @Override
    public void detachView() {
        if (mView != null) {
            mView.clear();
            mView = null;
        }

    }

    public void upload(String imagePath,int index){
        getView().showProgressDialog();
        mModel.upload(imagePath)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {

                    @Override
                    public void onResult(String msg, String bizContent) {
                        UploadResult result = new Gson().fromJson(bizContent,UploadResult.class);
                        result.setIndex(index);
                        getView().showUploadSuccess(result);
                    }

                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().showUploadFailure(index);
                    }
                });
    }



    protected V getView(){
        return mView.get();
    }

}
