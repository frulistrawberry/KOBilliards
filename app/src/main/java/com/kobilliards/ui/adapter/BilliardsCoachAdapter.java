package com.kobilliards.ui.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kobilliards.R;
import com.kobilliards.pojo.BilliardsCoachPojo;
import com.kobilliards.pojo.BilliardsRoomPojo;
import com.kobilliards.ui.activity.rearbyroom.BilliardsRoomDetailActivity;

public class BilliardsCoachAdapter extends BaseQuickAdapter<BilliardsCoachPojo, BaseViewHolder> {


    public BilliardsCoachAdapter() {
        super(R.layout.item_billiards_coach);
    }

    @Override
    protected void convert(BaseViewHolder helper, BilliardsCoachPojo item) {

        helper.getConvertView().setOnClickListener(v -> BilliardsRoomDetailActivity.launcher(mContext));
    }
}
