package com.yuyuka.billiards.ui.activity.merchant;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseListActivity;
import com.yuyuka.billiards.mvp.contract.merchant.BilliardsRoomListContract;
import com.yuyuka.billiards.mvp.presenter.merchant.BilliardsRoomListPresenter;
import com.yuyuka.billiards.pojo.BilliardsRoomPojo;
import com.yuyuka.billiards.ui.adapter.room.RoomAdapter;

import java.util.List;

public class MerchantListActivity extends BaseListActivity<BilliardsRoomListPresenter> implements BilliardsRoomListContract.IBilliardsRoomListView {
    String mTitle;

    public static void launcher(Context context,String title){
        Intent intent = new Intent(context, MerchantListActivity.class);
        intent.putExtra("title",title);
        context.startActivity(intent);

    }

    @Override
    protected boolean isLoadMoreEnable() {
        return true;
    }

    @Override
    public void onRefresh() {
        mCurrentPage = 0;
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
        mAdapter = new RoomAdapter();
        mTitle = getIntent().getStringExtra("title");
    }

    @Override
    public void showBilliardsRoomList(List<BilliardsRoomPojo> roomList) {
        if (mCurrentPage == 0){
            mAdapter.setNewData(roomList);
        }else {
            mAdapter.addData(roomList);
        }
    }
}
