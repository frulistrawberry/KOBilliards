package com.yuyuka.billiards.ui.fragment.message;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseListFragment;
import com.yuyuka.billiards.base.BasePresenter;
import com.yuyuka.billiards.pojo.GroupFragmentBean;
import com.yuyuka.billiards.ui.activity.message.GroupXiangActivity;
import com.yuyuka.billiards.ui.adapter.message.GroupFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class GroupFragment extends BaseListFragment implements BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.yanzheng_message)
    LinearLayout yanzhengMessage;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.layout_ptr)
    PtrClassicFrameLayout layoutPtr;
    List<GroupFragmentBean> list;
    @Override
    protected View createView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.layout_groupfragment, parent, false);
    }

    @Override
    protected void initData() {
       mAdapter=new GroupFragmentAdapter();
       list=new ArrayList<>();
       mAdapter.setOnItemClickListener(this::onItemClick);
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
        for (int i=0; i<8;i++){
            GroupFragmentBean fragmentBean = new GroupFragmentBean();
            list.add(fragmentBean);
        }
        mAdapter.setNewData(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
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

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        GroupXiangActivity.launcher(getContext());
    }
}
