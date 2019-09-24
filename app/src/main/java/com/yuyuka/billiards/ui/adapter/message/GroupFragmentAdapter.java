package com.yuyuka.billiards.ui.adapter.message;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.pojo.GroupFragmentBean;


public class GroupFragmentAdapter  extends BaseQuickAdapter<GroupFragmentBean, BaseViewHolder> {
    public GroupFragmentAdapter() {
        super(R.layout.layout_groupitem);
    }

    @Override
    protected void convert(BaseViewHolder helper, GroupFragmentBean item) {

    }
}
