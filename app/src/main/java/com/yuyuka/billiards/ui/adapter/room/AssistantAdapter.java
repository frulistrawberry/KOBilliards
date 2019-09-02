package com.yuyuka.billiards.ui.adapter.room;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.pojo.BilliardsCoachPojo;
import com.yuyuka.billiards.ui.activity.merchant.MerchantDetailActivity;

public class AssistantAdapter extends BaseQuickAdapter<BilliardsCoachPojo, BaseViewHolder> {


    public AssistantAdapter() {
        super(R.layout.item_billiards_coach);
    }

    @Override
    protected void convert(BaseViewHolder helper, BilliardsCoachPojo item) {

        helper.getConvertView().setOnClickListener(v -> MerchantDetailActivity.launcher(mContext,""));
    }
}
