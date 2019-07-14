package com.yuyuka.billiards.base;

import android.os.Bundle;
import android.support.annotation.Nullable;


public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity implements IBaseView {

    protected P mPresenter;

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        dismissProgressDialog();
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showError(String errMsg) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = getPresenter();

        super.onCreate(savedInstanceState);


    }

    protected abstract P getPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

}
