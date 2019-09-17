package com.yuyuka.billiards.ui.adapter.ranking;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.pojo.RankingBean;



public class RunkingAdapter  extends BaseQuickAdapter<RankingBean, BaseViewHolder> {
    public RunkingAdapter() {
        super(R.layout.layout_ranking_item);
    }


    @Override
    protected void convert(BaseViewHolder helper, RankingBean item) {

    }
}
