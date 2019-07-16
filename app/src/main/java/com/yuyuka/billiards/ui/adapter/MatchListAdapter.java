package com.yuyuka.billiards.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.pojo.BilliardsMatchPojo;

public class MatchListAdapter extends BaseQuickAdapter<BilliardsMatchPojo, BaseViewHolder> {

    public MatchListAdapter() {
        super(R.layout.item_match_list);
    }

    @Override
    protected void convert(BaseViewHolder helper, BilliardsMatchPojo item) {

    }
}
