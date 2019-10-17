package com.yuyuka.billiards.ui.adapter.roomball;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.pojo.ContestantBean;



public class ContestantAdapter extends BaseQuickAdapter<ContestantBean, BaseViewHolder> {
    public ContestantAdapter() {
        super(R.layout.layout_contestantitem);
    }

    @Override
    protected void convert(BaseViewHolder helper, ContestantBean item) {

    }
}
