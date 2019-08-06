package com.yuyuka.billiards.base;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.utils.ToastUtils;
import com.yuyuka.billiards.utils.ViewUtils;

import butterknife.BindView;

/**
 * 列表Activity
 *
 * 布局文件必须包含
 * 1 PtrClassicFrameLayout或其子类 id 为 layout_ptr
 * 2 RecyclerView 或其子类 id 为 recycler_view
 * 子类必须初始化 mAdapter
 */

public abstract class BaseListActivity<P extends BasePresenter> extends BaseRefreshActivity<P>{

    protected String mEmptyMsg = "空空如也";//空视图提示信息
    protected int mEmptyIcon = R.mipmap.ic_empty;//空视图图标
    protected int mCurrentPage = 1;//当前页码

    @BindView(R.id.recycler_view)
    protected RecyclerView mRecyclerView;

    protected BaseQuickAdapter mAdapter;


    @Override
    protected void initView() {
        super.initView();
        if (isLoadMoreEnable()) {
            mAdapter.setOnLoadMoreListener(this::onLoadMore, mRecyclerView);
        }

    }

    @Override
    public void showLoading() {
        if (!mPtrLayout.isRefreshing()&&!mAdapter.isLoading()){
            mAdapter.setEmptyView(ViewUtils.genLoadingView(this));
        }
    }


    @Override
    public void hideLoading() {
        super.hideLoading();
        if (mAdapter == null)
            return;
        if (isLoadMoreEnable()&&mAdapter.isLoading())
            mAdapter.loadMoreComplete();
    }

    @Override
    public void showEmpty() {
        if (mAdapter == null)
            return;
        if (mCurrentPage == 1)
            mAdapter.setEmptyView(ViewUtils.genEmptyView(getContext(),mEmptyIcon,mEmptyMsg));
        else if (isLoadMoreEnable()){
            mAdapter.loadMoreEnd(false);
            ToastUtils.showToast(getContext(),"已全部加载完成");
        }
    }


    @Override
    public void showError(String errMsg) {
        super.showError(errMsg);
        if (mAdapter == null)
            return;
        if (mCurrentPage == 1){
            super.showError(errMsg);
        }else if (isLoadMoreEnable()){
           mAdapter.loadMoreFail();
           mCurrentPage --;
        }
    }

    /**
     * 需要加载更多功能时 重写此方法 实现加载更多逻辑
     */
    protected void onLoadMore(){

    }

    /**
     * 子类必须重写此方法
     *
     * @return 是否支持加载更多
     */
    protected abstract boolean isLoadMoreEnable();
}
