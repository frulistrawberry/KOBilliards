package com.yuyuka.billiards.ui.fragment.roomball;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseListFragment;
import com.yuyuka.billiards.base.BasePresenter;
import com.yuyuka.billiards.pojo.PreliminaryBean;
import com.yuyuka.billiards.ui.activity.room.ContestantActivity;
import com.yuyuka.billiards.ui.adapter.roomball.PreliminaryAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class PreliminaryFragment extends BaseListFragment implements BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.layout_ptr)
    PtrClassicFrameLayout layoutPtr;
    List<PreliminaryBean> list;

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
    protected View createView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.include_ptr_recycler, parent, false);
    }

    @Override
    protected void initData() {
        mAdapter = new PreliminaryAdapter();
        list=new ArrayList<>();
        mAdapter.setOnItemClickListener(this::onItemClick);
    }

    @Override
    protected void initView() {
        super.initView();
        layoutPtr.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

            }
        });
        for (int i=0;i<7;i++){
            PreliminaryBean preliminaryBean = new PreliminaryBean();
            list.add(preliminaryBean);
        }
        mAdapter.setNewData(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ContestantActivity.launcher(getContext());
    }
}
