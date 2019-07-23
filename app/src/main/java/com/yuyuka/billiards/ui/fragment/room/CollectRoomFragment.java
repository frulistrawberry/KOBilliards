package com.yuyuka.billiards.ui.fragment.room;

import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseListFragment;
import com.yuyuka.billiards.event.OffsetChangeEvent;
import com.yuyuka.billiards.mvp.contract.room.CollectionRoomContract;
import com.yuyuka.billiards.mvp.presenter.room.CollectionRoomPresenter;
import com.yuyuka.billiards.pojo.BilliardsRoomPojo;
import com.yuyuka.billiards.ui.adapter.room.RoomAdapter;
import com.yuyuka.billiards.widget.AppBarStateChangeListener;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class CollectRoomFragment extends BaseListFragment<CollectionRoomPresenter> implements  CollectionRoomContract.ICollectionRoomView {


    private boolean isHeaderOpened;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.include_ptr_recycler,parent,false);
    }

    @Override
    protected void initData() {
        mAdapter = new RoomAdapter();
    }

    @Override
    protected void initView() {
        super.initView();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        mPtrLayout.setPtrHandler(new PtrDefaultHandler() {

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                if (isHeaderOpened)
                    return super.checkCanDoRefresh(frame, content, header);
                else
                    return false;
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                onRefresh();

            }
        });

        onRefresh();

    }

    @Override
    protected void onRefresh() {
        mCurrentPage=1;
        mPresenter.getRecommendRoomList(mCurrentPage);
    }

    @Override
    protected void onLoadMore() {
        super.onLoadMore();
        mCurrentPage++;
        mPresenter.getRecommendRoomList(mCurrentPage);

    }

    @Override
    protected boolean isLoadMoreEnable() {
        return true;
    }





    @Override
    protected CollectionRoomPresenter getPresenter() {
        return new CollectionRoomPresenter(this);
    }

    @Override
    public void showCollectionRoomList(List<BilliardsRoomPojo> roomList) {
        if (mCurrentPage == 1){
            mAdapter.setNewData(roomList);
        }else {
            mAdapter.addData(roomList);
        }
    }

    @Subscribe
    public void onEvent(OffsetChangeEvent event){
        if (event.from.equals("NearbyRoomActivity")){
            isHeaderOpened = event.state== AppBarStateChangeListener.State.EXPANDED;
        }
    }



}
