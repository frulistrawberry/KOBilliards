package com.kobilliards.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kobilliards.R;
import com.kobilliards.pojo.BilliardsRoomPojo;

public class BilliardsRoomAdapter extends BaseQuickAdapter<BilliardsRoomPojo, BaseViewHolder> {


    public BilliardsRoomAdapter() {
        super(R.layout.item_default_drop_down);
    }

    @Override
    protected void convert(BaseViewHolder helper, BilliardsRoomPojo item) {

    }
}
