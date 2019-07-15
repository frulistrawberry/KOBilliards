package com.kobilliards.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kobilliards.R;
import com.kobilliards.pojo.AlbumBean;

import java.util.List;


public class AlbumActivityAdapter extends BaseQuickAdapter<AlbumBean, BaseViewHolder> {
    public AlbumActivityAdapter() {
        super(R.layout.layout_albumactivity);
    }

    @Override
    protected void convert(BaseViewHolder helper, AlbumBean item) {

    }
}
