package com.yuyuka.billiards.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuyuka.billiards.base.BaseFragment;
import com.yuyuka.billiards.base.BaseMvpFragment;
import com.yuyuka.billiards.ui.activity.room.NearbyRoomActivity;

public class HomeFragment extends BaseFragment {
    @Override
    protected View createView(LayoutInflater inflater, ViewGroup parent) {
        return null;
    }

    @Override
    protected void initData() {
        NearbyRoomActivity.launcher(getContext());
    }

    @Override
    protected void initView() {

    }
}
