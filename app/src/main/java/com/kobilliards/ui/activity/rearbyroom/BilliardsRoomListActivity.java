package com.kobilliards.ui.activity.rearbyroom;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;

import com.kobilliards.R;
import com.kobilliards.base.BaseListActivity;
import com.kobilliards.mvp.contract.rearbyroom.BilliardsRoomListContract;
import com.kobilliards.mvp.presenter.nearbyroom.BilliardsRoomListPresenter;
import com.kobilliards.pojo.BilliardsRoomPojo;
import com.kobilliards.ui.adapter.BilliardsRoomAdapter;

import java.util.List;

public class BilliardsRoomListActivity extends BaseListActivity<BilliardsRoomListPresenter> implements BilliardsRoomListContract.IBilliardsRoomListView {
    String mTitle;

    public static void launcher(Context context,String title){
        Intent intent = new Intent(context,BilliardsRoomListActivity.class);
        intent.putExtra("title",title);
        context.startActivity(intent);

    }

    @Override
    protected boolean isLoadMoreEnable() {
        return true;
    }

    @Override
    protected void onRefresh() {
        mCurrentPage = 1;
        mPresenter.getBilliardsRoomList(mCurrentPage);
    }

    @Override
    protected void onLoadMore() {
        super.onLoadMore();
        mCurrentPage++;
        mPresenter.getBilliardsRoomList(mCurrentPage);
    }

    @Override
    protected BilliardsRoomListPresenter getPresenter() {
        return new BilliardsRoomListPresenter(this);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_billiards_room_list);
        super.initView();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        onRefresh();

    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle(mTitle).showBack().show();
    }

    @Override
    protected void initData() {
        mAdapter = new BilliardsRoomAdapter();
        mTitle = getIntent().getStringExtra("title");
    }

    @Override
    public void showBilliardsRoomList(List<BilliardsRoomPojo> roomList) {
        if (mCurrentPage == 1){
            mAdapter.setNewData(roomList);
        }else {
            mAdapter.addData(roomList);
        }
    }
}
