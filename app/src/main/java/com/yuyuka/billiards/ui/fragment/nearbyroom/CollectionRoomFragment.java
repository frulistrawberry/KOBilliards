package com.yuyuka.billiards.ui.fragment.nearbyroom;

import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kobilliards.R;
import com.yuyuka.billiards.base.BaseListFragment;
import com.yuyuka.billiards.event.OffsetChangeEvent;
import com.yuyuka.billiards.mvp.contract.rearbyroom.CollectionRoomContract;
import com.yuyuka.billiards.mvp.presenter.nearbyroom.CollectionRoomPresenter;
import com.yuyuka.billiards.pojo.BilliardsRoomPojo;
import com.yuyuka.billiards.ui.adapter.BilliardsRoomAdapter;
import com.yuyuka.billiards.utils.BarUtils;

import java.util.List;

public class CollectionRoomFragment extends BaseListFragment<CollectionRoomPresenter> implements  CollectionRoomContract.ICollectionRoomView {


    @Override
    protected View createView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.include_ptr_recycler,parent,false);
    }

    @Override
    protected void initData() {
        mAdapter = new BilliardsRoomAdapter();
    }

    @Override
    protected void initView() {
        super.initView();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
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


}
