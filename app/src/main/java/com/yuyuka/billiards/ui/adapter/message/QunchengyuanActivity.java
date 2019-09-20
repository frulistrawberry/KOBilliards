package com.yuyuka.billiards.ui.adapter.message;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseListActivity;
import com.yuyuka.billiards.base.BasePresenter;
import com.yuyuka.billiards.pojo.QunchengBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;


public class QunchengyuanActivity extends BaseListActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.layout_ptr)
    PtrClassicFrameLayout layoutPtr;
    private List<QunchengBean> list;

    public static void launcher(Context context) {
        context.startActivity(new Intent(context, QunchengyuanActivity.class));
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("成员").showBack().show();
    }

    @Override
    protected boolean isLoadMoreEnable() {
        return false;
    }

    @Override
    protected void onRefresh() {

    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initData() {
       mAdapter=new QunchengyuanAdapter();
       list=new ArrayList<>();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.include_ptr_recycler);
        mPtrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

            }

        });
        for (int i=0;i<20;i++){
            QunchengBean qunchengBean = new QunchengBean();
            list.add(qunchengBean);
        }
        mAdapter.setNewData(list);
        recyclerView.setLayoutManager(new GridLayoutManager(this,5));
        recyclerView.setAdapter(mAdapter);
    }

}
