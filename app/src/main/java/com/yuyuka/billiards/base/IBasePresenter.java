package com.yuyuka.billiards.base;

public interface IBasePresenter<V extends IBaseView> {

    void attachView(V view);

    void detachView();

}
