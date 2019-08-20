package com.yuyuka.billiards.ui.fragment.news;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseListFragment;
import com.yuyuka.billiards.mvp.contract.news.NewsListContract;
import com.yuyuka.billiards.mvp.presenter.news.NewsListPresenter;
import com.yuyuka.billiards.pojo.NewsItem;
import com.yuyuka.billiards.ui.adapter.news.NewsAdapter;
import com.yuyuka.billiards.ui.fragment.NewsFragment;
import com.yuyuka.billiards.utils.log.LogUtil;

import java.util.List;

public class NewsListFragment extends BaseListFragment<NewsListPresenter> implements NewsListContract.INewsListView {
    int queryType;


    public static Fragment newFragment(int queryType){
        Fragment fragment = new NewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("queryType",queryType);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected boolean isLoadMoreEnable() {
        return true;
    }

    @Override
    protected void onRefresh() {
        mCurrentPage = 0;
        getPresenter().getNewsList(mCurrentPage,queryType);
    }

    @Override
    protected void onLoadMore() {
        super.onLoadMore();
        mCurrentPage ++;
        getPresenter().getNewsList(mCurrentPage,queryType);
    }

    @Override
    protected NewsListPresenter getPresenter() {
        return new NewsListPresenter(this);
    }

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.include_ptr_recycler,parent,false);
    }

    @Override
    protected void initView() {
        super.initView();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        mRecyclerView.setAdapter(mAdapter);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {
                int itemType = mAdapter.getItemViewType(i);
                if (itemType == 1)
                    return 1;
                else
                    return 2;
            }
        });
        mRecyclerView.setLayoutManager(gridLayoutManager);
        onRefresh();
    }

    @Override
    protected void initData() {
        mAdapter = new NewsAdapter();
        assert getArguments() != null;
        queryType = getArguments().getInt("queryType",0);
    }

    @Override
    public void showNewsList(List<NewsItem> newsList) {
        if (mCurrentPage == 0){
            mAdapter.setNewData(newsList);
        }else {
            mAdapter.addData(newsList);
        }
    }
}
