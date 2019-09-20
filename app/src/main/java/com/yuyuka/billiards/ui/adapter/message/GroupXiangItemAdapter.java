package com.yuyuka.billiards.ui.adapter.message;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.pojo.GroupXiangitenBean;



public class GroupXiangItemAdapter extends BaseQuickAdapter<GroupXiangitenBean, BaseViewHolder> {
    public GroupXiangItemAdapter() {
        super(R.layout.layout_grouplistitem);
    }

    @Override
    protected void convert(BaseViewHolder helper, GroupXiangitenBean item) {

    }
}
