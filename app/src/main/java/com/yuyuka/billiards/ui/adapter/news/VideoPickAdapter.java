package com.yuyuka.billiards.ui.adapter.news;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.utils.bean.Video;

public class VideoPickAdapter extends BaseQuickAdapter<Video, BaseViewHolder> {
    public VideoPickAdapter(int layoutResId) {
        super(R.layout.item_photo_picker);
    }

    @Override
    protected void convert(BaseViewHolder helper, Video item) {

    }
}
