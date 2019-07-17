package com.yuyuka.billiards.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.pojo.BilliardsCoachPojo;
import com.yuyuka.billiards.ui.activity.room.BilliardsRoomDetailActivity;

public class BilliardsCoachAdapter extends BaseQuickAdapter<BilliardsCoachPojo, BaseViewHolder> {


    public BilliardsCoachAdapter() {
        super(R.layout.item_billiards_coach);
    }

    @Override
    protected void convert(BaseViewHolder helper, BilliardsCoachPojo item) {

        helper.getConvertView().setOnClickListener(v -> BilliardsRoomDetailActivity.launcher(mContext));
    }
}
