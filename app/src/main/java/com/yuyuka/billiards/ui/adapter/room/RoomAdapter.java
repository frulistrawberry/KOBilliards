package com.yuyuka.billiards.ui.adapter.room;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.pojo.BilliardsRoomPojo;
import com.yuyuka.billiards.ui.activity.room.RoomDetailActivity;

public class RoomAdapter extends BaseQuickAdapter<BilliardsRoomPojo, BaseViewHolder> {


    public RoomAdapter() {
        super(R.layout.item_billiards_room);
    }

    @Override
    protected void convert(BaseViewHolder helper, BilliardsRoomPojo item) {
        if (helper.getAdapterPosition() - getHeaderLayoutCount() == 0){
            helper.setGone(R.id.v_divider,true);
        }else {
            helper.setGone(R.id.v_divider,false);
        }

        helper.setText(R.id.tv_billiards_name,item.getBilliardsName());

        helper.getConvertView().setOnClickListener(v -> RoomDetailActivity.launcher(mContext));
    }
}
