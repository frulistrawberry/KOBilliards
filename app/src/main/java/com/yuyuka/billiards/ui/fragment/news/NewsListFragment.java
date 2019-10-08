package com.yuyuka.billiards.ui.fragment.news;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseListFragment;
import com.yuyuka.billiards.image.ImageManager;
import com.yuyuka.billiards.image.support.LoadOption;
import com.yuyuka.billiards.mvp.contract.news.NewsListContract;
import com.yuyuka.billiards.mvp.presenter.news.NewsListPresenter;
import com.yuyuka.billiards.pojo.BilliardsUsers;
import com.yuyuka.billiards.pojo.NewsItem;
import com.yuyuka.billiards.ui.adapter.news.NewsAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class NewsListFragment extends BaseListFragment<NewsListPresenter> implements NewsListContract.INewsListView {
    int queryType;
    boolean isFromHome;
    String keywords;

    View headerView;
    LinearLayout usersLayout;



    public static Fragment newFragment(int queryType,boolean isFromHome){
        Fragment fragment = new NewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("queryType",queryType);
        bundle.putBoolean("isFromHome",isFromHome);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static Fragment newFragment(int queryType){
        return newFragment(queryType,false);
    }


    @Override
    protected boolean isLoadMoreEnable() {
        return true;
    }

    @Override
    public void onRefresh() {
        mCurrentPage = 0;
        getPresenter().getNewsList(keywords,queryType,mCurrentPage);
    }

    public void onRefresh(String keywords) {
        this.keywords = keywords;
        mAdapter.setNewData(null);
        onRefresh();
    }

    @Override
    protected void onLoadMore() {
        super.onLoadMore();
        mCurrentPage ++;
        getPresenter().getNewsList(keywords,queryType,mCurrentPage);
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
        if (isFromHome){
            mPtrLayout.setPtrHandler(new PtrDefaultHandler() {
                @Override
                public void onRefreshBegin(PtrFrameLayout frame) {

                }

                @Override
                public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                    return false;
                }
            });
        }
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
        if (queryType == 5){
            headerView = LayoutInflater.from(getContext()).inflate(R.layout.view_attention_users,mRecyclerView,false);
            usersLayout = headerView.findViewById(R.id.ll_users);
            headerView.setVisibility(View.GONE);
            mAdapter.addHeaderView(headerView);
            mAdapter.setHeaderAndEmpty(true);
        }

        onRefresh();
    }

    @Override
    protected void initData() {
        assert getArguments() != null;
        queryType = getArguments().getInt("queryType",0);
        isFromHome = getArguments().getBoolean("isFromHome",false);

        if (queryType == 4){
            mAdapter = new NewsAdapter("home");
        }else if (queryType == 3){
            mAdapter = new NewsAdapter("recommend");

        }else if (queryType == 5){
            mAdapter = new NewsAdapter("attention");

        }else
            mAdapter = new NewsAdapter("news");

    }

    @Override
    public void showNewsList(List<NewsItem> newsList) {
        if (mCurrentPage == 0){
            mAdapter.setNewData(newsList);
        }else {
            mAdapter.addData(newsList);
        }
    }

    @Override
    public void showUserList(List<BilliardsUsers> userList) {
        headerView.setVisibility(View.VISIBLE);
        usersLayout.removeAllViews();
        for (int i = 0; i < userList.size(); i++) {
            View userView = LayoutInflater.from(getContext()).inflate(R.layout.item_attention_users,null);
            ImageView headIv = userView.findViewById(R.id.iv_head);
            ImageManager.getInstance().loadNet(userList.get(i).getHeadImage(),headIv,new LoadOption().setIsCircle(true));
            TextView userTv = userView.findViewById(R.id.tv_user);
            userTv.setText(userList.get(i).getUserName());
            usersLayout.addView(userView);
        }
    }

    @Override
    public void hideLoading() {
        super.hideLoading();
        EventBus.getDefault().post("refresh_complete");
    }
}
