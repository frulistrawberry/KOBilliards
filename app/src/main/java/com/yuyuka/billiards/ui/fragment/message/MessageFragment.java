package com.yuyuka.billiards.ui.fragment.message;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseFragment;

import butterknife.BindView;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;

public class MessageFragment extends BaseFragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.layout_ptr)
    PtrClassicFrameLayout layoutPtr;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.layout_eessagefaragment, parent, false);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

}
