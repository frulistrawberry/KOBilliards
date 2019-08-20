package com.yuyuka.billiards.ui.fragment.live;

import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseListFragment;
import com.yuyuka.billiards.event.OffsetChangeEvent;
import com.yuyuka.billiards.mvp.contract.live.NearbyLiveContract;
import com.yuyuka.billiards.mvp.presenter.live.NearbyLivePresenter;
import com.yuyuka.billiards.pojo.LivePojo;
import com.yuyuka.billiards.pojo.VideoPojo;
import com.yuyuka.billiards.ui.adapter.live.LiveAdapter;
import com.yuyuka.billiards.widget.AppBarStateChangeListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import retrofit2.http.GET;

public class NearbyLiveFragment extends BaseListFragment<NearbyLivePresenter> implements NearbyLiveContract.INearbyLiveView {

    boolean canRefresh;

    @Override
    protected boolean isLoadMoreEnable() {
        return true;
    }

    @Override
    protected void onRefresh() {
        mCurrentPage = 0;
        getPresenter().getNearbyLiveList(mCurrentPage);
    }

    @Override
    protected void onLoadMore() {
        super.onLoadMore();
        mCurrentPage++;
        getPresenter().getNearbyLiveList(mCurrentPage);
    }

    @Override
    protected NearbyLivePresenter getPresenter() {
        return new NearbyLivePresenter(this);
    }

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.include_ptr_recycler,parent,false);
    }

    @Override
    protected void initData() {
        mAdapter = new LiveAdapter();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Override
    protected void initView() {
        super.initView();
        mPtrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                onRefresh();
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return canRefresh && super.checkCanDoRefresh(frame, content, header);
            }
        });
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        mRecyclerView.setAdapter(mAdapter);
        onRefresh();
    }

    @Override
    public void showNearbyLiveList(List<LivePojo> dataList) {
        if (mCurrentPage == 0){
            mAdapter.setNewData(dataList);
        }else {
            mAdapter.addData(dataList);
        }
    }

    @Subscribe
    public void onEvent(OffsetChangeEvent event){
        if (event.from.equals("HomeFragment")){
            canRefresh = event.state == AppBarStateChangeListener.State.COLLAPSED;
        }
    }
}
