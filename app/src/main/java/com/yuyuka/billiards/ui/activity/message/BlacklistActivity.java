package com.yuyuka.billiards.ui.activity.message;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseListActivity;
import com.yuyuka.billiards.base.BasePresenter;
import com.yuyuka.billiards.pojo.GroupFragmentBean;
import com.yuyuka.billiards.ui.adapter.message.GroupFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class BlacklistActivity extends BaseListActivity implements BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.layout_ptr)
    PtrClassicFrameLayout layoutPtr;
    List<GroupFragmentBean> list;
    public static void launcher(Context context) {
        context.startActivity(new Intent(context, BlacklistActivity.class));
    }
    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("黑名单").showBack().show();
    }

    @Override
    protected boolean isLoadMoreEnable() {
        return false;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        setContentView(R.layout.include_ptr_recycler);
        mPtrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return false;
            }
        });
        for (int i=0; i<8;i++){
            GroupFragmentBean fragmentBean = new GroupFragmentBean();
            list.add(fragmentBean);
        }
        mAdapter.setNewData(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mAdapter=new GroupFragmentAdapter();
        list=new ArrayList<>();
        mAdapter.setOnItemClickListener(this::onItemClick);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        BlackActivity.launcher(this);
    }
}
