package com.yuyuka.billiards.ui.activity.room;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseListActivity;
import com.yuyuka.billiards.base.BasePresenter;
import com.yuyuka.billiards.pojo.ContestantBean;
import com.yuyuka.billiards.ui.adapter.roomball.ContestantAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class ContestantActivity extends BaseListActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.layout_ptr)
    PtrClassicFrameLayout layoutPtr;
    private List<ContestantBean> list;

    public static void launcher(Context context) {
        context.startActivity(new Intent(context, ContestantActivity.class));
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("参赛选手").showBack().show();
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
          mAdapter=new ContestantAdapter();
          list=new ArrayList<>();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.layout_contestantactivity);
        layoutPtr.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

            }
        });
        for (int i=0;i<25;i++){
            ContestantBean contestantBean = new ContestantBean();
            list.add(contestantBean);
        }
        mAdapter.setNewData(list);
        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        recyclerView.setAdapter(mAdapter);
    }

}
