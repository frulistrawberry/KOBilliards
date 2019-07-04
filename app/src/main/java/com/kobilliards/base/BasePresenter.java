package com.kobilliards.base;



import com.trello.rxlifecycle2.internal.Preconditions;

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



    protected V getView(){
        return mView.get();
    }

}
