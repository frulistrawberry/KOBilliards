package com.yuyuka.billiards.ui.adapter;

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
    }
}
