package com.yuyuka.billiards.ui.activity.room;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseListActivity;
import com.yuyuka.billiards.mvp.contract.room.BilliardsCoachListContract;
import com.yuyuka.billiards.mvp.presenter.room.BilliardsCoachListPresenter;
import com.yuyuka.billiards.pojo.BilliardsCoachPojo;
import com.yuyuka.billiards.ui.adapter.room.AssistantAdapter;

import java.util.List;

public class AssistantListActivity extends BaseListActivity<BilliardsCoachListPresenter> implements BilliardsCoachListContract.IBilliardsCoachListView {

    public static void launcher(Context context){
        Intent intent = new Intent(context, AssistantListActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected boolean isLoadMoreEnable() {
        return true;
    }

    @Override
    public void onRefresh() {
        mCurrentPage = 0;
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
        mAdapter = new AssistantAdapter();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activiry_billiards_coach_list);
        super.initView();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        onRefresh();
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("明星助教").showBack().show();
    }

    @Override
    public void showBilliardsCoachList(List<BilliardsCoachPojo> coachList) {
        if (mCurrentPage == 0){
            mAdapter.setNewData(coachList);
        }else {
            mAdapter.addData(coachList);
        }
    }
}
