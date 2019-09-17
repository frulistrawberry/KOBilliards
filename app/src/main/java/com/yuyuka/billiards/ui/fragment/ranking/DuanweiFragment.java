package com.yuyuka.billiards.ui.fragment.ranking;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseListFragment;
import com.yuyuka.billiards.base.BasePresenter;
import com.yuyuka.billiards.net.CanJuBean;
import com.yuyuka.billiards.ui.adapter.ranking.CanJuAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class DuanweiFragment extends BaseListFragment {
    List<CanJuBean> canJuBeanList;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.layout_ptr)
    PtrClassicFrameLayout layoutPtr;
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
        return inflater.inflate(R.layout.layout_duanweifragment,parent,false);
    }

    @Override
    protected void initData() {
        mAdapter =new CanJuAdapter();
        canJuBeanList=new ArrayList<>();
    }

    @Override
    protected void initView() {
        super.initView();
        mPtrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return false;
            }
        });
        for (int i=0;i<10;i++){
            CanJuBean canJuBean=new CanJuBean();
            canJuBeanList.add(canJuBean);
        }
        mAdapter.setNewData(canJuBeanList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
    }
}
