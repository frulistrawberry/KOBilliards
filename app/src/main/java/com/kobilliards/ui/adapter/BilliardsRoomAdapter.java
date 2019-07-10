package com.kobilliards.ui.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kobilliards.R;
import com.kobilliards.pojo.BilliardsRoomPojo;
import com.kobilliards.ui.activity.rearbyroom.BilliardsRoomDetailActivity;

public class BilliardsRoomAdapter extends BaseQuickAdapter<BilliardsRoomPojo, BaseViewHolder> {


    public BilliardsRoomAdapter() {
        super(R.layout.item_billiards_room);
    }

    @Override
    protected void convert(BaseViewHolder helper, BilliardsRoomPojo item) {
        if (helper.getAdapterPosition() - getHeaderLayoutCount() == 0){
            helper.setGone(R.id.v_divider,true);
        }else {
            helper.setGone(R.id.v_divider,false);
        }
        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BilliardsRoomDetailActivity.launcher(mContext);
            }
        });
    }
}
