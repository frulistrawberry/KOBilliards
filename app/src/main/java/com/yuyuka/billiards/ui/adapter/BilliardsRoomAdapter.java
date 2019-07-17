package com.yuyuka.billiards.ui.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.pojo.BilliardsRoomPojo;
import com.yuyuka.billiards.ui.activity.room.BilliardsRoomDetailActivity;

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
