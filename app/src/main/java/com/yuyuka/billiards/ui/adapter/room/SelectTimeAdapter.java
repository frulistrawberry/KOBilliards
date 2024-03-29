package com.yuyuka.billiards.ui.adapter.room;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.pojo.SelectTimePojo;

public class SelectTimeAdapter extends BaseQuickAdapter<SelectTimePojo, BaseViewHolder> {
    public SelectTimeAdapter() {
        super(R.layout.item_select_time);
    }

    @Override
    protected void convert(BaseViewHolder helper, SelectTimePojo item) {
        if (item.isSelected()){
            helper.setTextColor(R.id.tv_time,mContext.getResources().getColor(R.color.text_color_6));
            helper.setBackgroundRes(R.id.ll_time,R.drawable.bg_select_time_selected);
        }else {
            helper.setTextColor(R.id.tv_time,mContext.getResources().getColor(R.color.text_color_3));
            helper.setBackgroundRes(R.id.ll_time,R.drawable.bg_select_time_un_selected);
        }

        if (item.isActive()){
            helper.setTextColor(R.id.tv_price,mContext.getResources().getColor(R.color.red));
            helper.setText(R.id.tv_price,"优惠价"+item.getAmount());

        }else {
            helper.setTextColor(R.id.tv_price,mContext.getResources().getColor(R.color.text_color_3));
            helper.setText(R.id.tv_price,"正常价"+item.getAmount());

        }
        helper.setText(R.id.tv_time,item.getClock());

    }
}
