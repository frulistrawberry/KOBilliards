package com.kobilliards.ui.activity.rearbyroom;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;

import com.kobilliards.R;
import com.kobilliards.base.BaseListActivity;
import com.kobilliards.mvp.contract.rearbyroom.BilliardsCoachListContract;
import com.kobilliards.mvp.presenter.nearbyroom.BilliardsCoachListPresenter;
import com.kobilliards.pojo.BilliardsCoachPojo;
import com.kobilliards.ui.adapter.BilliardsCoachAdapter;

import java.util.List;

public class BilliardsCoachListActivity extends BaseListActivity<BilliardsCoachListPresenter> implements BilliardsCoachListContract.IBilliardsCoachListView {

    public static void launcher(Context context){
        Intent intent = new Intent(context,BilliardsCoachListActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected boolean isLoadMoreEnable() {
        return true;
    }

    @Override
    protected void onRefresh() {
        mCurrentPage = 1;
        mPresenter.getBilliardsCoachList(mCurrentPage);
    }
    @Override
    protected void onLoadMore() {
        super.onLoadMore();
        mCurrentPage++;
        mPresenter.getBilliardsCoachList(mCurrentPage);
    }

    @Override
    protected BilliardsCoachListPresenter getPresenter() {
        return new BilliardsCoachListPresenter(this);
    }

    @Override
    protected void initData() {
        mAdapter = new BilliardsCoachAdapter();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activiry_billiards_coach_list);
        super.initView();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("明星助教").showBack().show();
    }

    @Override
    public void showBilliardsCoachList(List<BilliardsCoachPojo> coachList) {
        if (mCurrentPage == 1){
            mAdapter.setNewData(coachList);
        }else {
            mAdapter.addData(coachList);
        }
    }
}
