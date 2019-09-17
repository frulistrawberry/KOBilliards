package com.yuyuka.billiards.ui.adapter.ranking;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.net.CanJuBean;


public class CanJuAdapter extends BaseQuickAdapter<CanJuBean, BaseViewHolder> {
    public CanJuAdapter() {
        super(R.layout.layout_canjuitem);
    }

    @Override
    protected void convert(BaseViewHolder helper, CanJuBean item) {

    }
}
