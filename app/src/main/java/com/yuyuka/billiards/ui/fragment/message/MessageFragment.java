package com.yuyuka.billiards.ui.fragment.message;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.netease.nimlib.sdk.msg.model.RecentContact;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseListFragment;
import com.yuyuka.billiards.base.BasePresenter;
import com.yuyuka.billiards.pojo.MessageBean;
import com.yuyuka.billiards.ui.adapter.message.MessageFragmentAdapter;
import com.yuyuka.billiards.utils.SwipeListLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class MessageFragment extends BaseListFragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.layout_ptr)
    PtrClassicFrameLayout layoutPtr;
    private List<RecentContact> data;
    private MessageFragmentAdapter adapter;
    @Override
    protected View createView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.include_ptr_recycler, parent, false);
    }

    @Override
    protected void initData() {
         adapter=new MessageFragmentAdapter(getContext());
    }

    @Override
    protected void initView() {
        mPtrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return false;
            }
        });

        adapter.setNewData(data);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.setBtnOnClickListen(new MessageFragmentAdapter.BtnOnClickListen() {
            @Override
            public void onTopListen(int position, SwipeListLayout swipeListLayout) {

            }

            @Override
            public void onDeleteListen(int position, SwipeListLayout swipeListLayout) {


            }
        });
    }

    @Override
    public void onRefresh() {

    }

    @Override
    protected boolean isLoadMoreEnable() {
        return false;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }
}
