package com.yuyuka.billiards.ui.fragment.message;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseFragment;
import com.yuyuka.billiards.base.BaseListFragment;
import com.yuyuka.billiards.base.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;

public class GroupFragment extends BaseFragment {


    @Override
    protected View createView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.layout_groupfragment, parent, false);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }
}
