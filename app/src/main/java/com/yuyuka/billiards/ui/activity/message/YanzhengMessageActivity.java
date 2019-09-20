package com.yuyuka.billiards.ui.activity.message;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseListActivity;
import com.yuyuka.billiards.base.BasePresenter;
import com.yuyuka.billiards.pojo.YanzhengBean;
import com.yuyuka.billiards.ui.adapter.message.YanzhenMessageAdapter;
import com.yuyuka.billiards.ui.adapter.message.YanzhengXiangActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class YanzhengMessageActivity extends BaseListActivity implements BaseSectionQuickAdapter.OnItemClickListener {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.layout_ptr)
    PtrClassicFrameLayout layoutPtr;
    List<YanzhengBean> yanzhengBeanList;

    public static void launcher(Context context) {
        context.startActivity(new Intent(context, YanzhengMessageActivity.class));
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("验证消息").showBack().show();
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
        for (int i=0;i<10 ;i++){
            YanzhengBean yanzhengBean = new YanzhengBean(false,"一天前");
            yanzhengBeanList.add(yanzhengBean);
        }
        mAdapter.setNewData(yanzhengBeanList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onRefresh() {

    }

    @Override
    protected boolean isLoadMoreEnable() {
        return false;
    }

    @Override
    protected void initData() {
          mAdapter=new YanzhenMessageAdapter();
          yanzhengBeanList=new ArrayList<>();
          mAdapter.setOnItemClickListener(this::onItemClick);
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        YanzhengXiangActivity.launcher(this);
    }
}
