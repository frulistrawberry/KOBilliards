package com.yuyuka.billiards.ui.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kobilliards.pojo.AlbumBean;
import com.yuyuka.billiards.R;


public class AlbumActivityAdapter extends BaseQuickAdapter<AlbumBean, BaseViewHolder> {
    public AlbumActivityAdapter() {
        super(R.layout.layout_albumactivity);
    }

    @Override
    protected void convert(BaseViewHolder helper, AlbumBean item) {

    }
}
