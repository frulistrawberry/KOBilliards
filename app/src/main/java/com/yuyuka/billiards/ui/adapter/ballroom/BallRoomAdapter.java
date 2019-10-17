package com.yuyuka.billiards.ui.adapter.ballroom;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.pojo.BallRoomBean;

public class BallRoomAdapter extends BaseQuickAdapter<BallRoomBean, BaseViewHolder> {
    public BallRoomAdapter() {
        super(R.layout.layout_ballroomitem);
    }

    @Override
    protected void convert(BaseViewHolder helper, BallRoomBean item) {

    }
}
