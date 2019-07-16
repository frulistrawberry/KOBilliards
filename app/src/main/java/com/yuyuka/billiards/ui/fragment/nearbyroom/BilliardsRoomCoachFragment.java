package com.yuyuka.billiards.ui.fragment.nearbyroom;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseFragment;
import com.yuyuka.billiards.ui.activity.rearbyroom.BilliardsSearchActivity;
import com.yuyuka.billiards.ui.activity.rearbyroom.CoachDetailActivity;
import com.yuyuka.billiards.utils.ScreenUtils;

import butterknife.BindView;

public class BilliardsRoomCoachFragment extends BaseFragment {
    @BindView(R.id.iv_coach)
    ImageView mCoachIv;

    int type = 0;

    public static Fragment newFragment(int type){
        Bundle bundle = new Bundle();
        bundle.putInt("type",type);
        BilliardsRoomCoachFragment fragment = new BilliardsRoomCoachFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.fragment_billiards_room_coach,parent,false);
    }

    @Override
    protected void initData() {
        type = getArguments().getInt("type",0);
    }

    @Override
    protected void initView() {
        if (type == 0){
            mCoachIv.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.getScreenWidth(getContext())));
            mCoachIv.setImageResource(R.mipmap.img_coach);
            mCoachIv.setOnClickListener(v -> {
                CoachDetailActivity.launcher(getContext());
            });
        }else {
            mCoachIv.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            mCoachIv.setImageResource(R.mipmap.img_coach_land);
        }

    }
}
