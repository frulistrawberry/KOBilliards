package com.yuyuka.billiards.ui.fragment.nearbyroom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseFragment;
import com.yuyuka.billiards.utils.ScreenUtils;

import butterknife.BindView;

public class BilliardsRoomCoachFragment extends BaseFragment {
    @BindView(R.id.iv_coach)
    ImageView mCoachIv;
    @Override
    protected View createView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.fragment_billiards_room_coach,parent,false);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mCoachIv.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.getScreenWidth(getContext())));
        mCoachIv.setImageResource(R.mipmap.img_coach);
    }
}
