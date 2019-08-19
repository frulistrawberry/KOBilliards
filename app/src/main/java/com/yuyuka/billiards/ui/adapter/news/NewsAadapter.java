package com.yuyuka.billiards.ui.adapter.news;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class NewsAadapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public NewsAadapter(List<MultiItemEntity> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {

    }
}
