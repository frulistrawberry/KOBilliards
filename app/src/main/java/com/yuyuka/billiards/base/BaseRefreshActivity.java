package com.yuyuka.billiards.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.kobilliards.R;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 需要下拉刷新的Activity
 * 布局文件里必须包含PtrClassicFrameLayout或其子类 id 为layout_ptr
 * @param <P>
 */

public abstract class BaseRefreshActivity<P extends BasePresenter> extends BaseMvpActivity<P>{

    @BindView(R.id.layout_ptr)
    protected PtrClassicFrameLayout mPtrLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void initView() {
        mPtrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                onRefresh();
            }
        });
    }


    @Override
    public void hideLoading() {
        if (mPtrLayout.isRefreshing())
            mPtrLayout.refreshComplete();
        dismissProgressDialog();

    }

    @Override
    public void showLoading() {
        if (!mPtrLayout.isRefreshing()){
            showProgressDialog();
        }

    }

    /**
     * 子类必须实现此方法添加刷新逻辑
     */
    protected abstract void onRefresh();

}
