package com.yuyuka.billiards.ui.adapter.news;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.utils.bean.Video;

public class VideoPickAdapter extends BaseQuickAdapter<Video, BaseViewHolder> {
    public VideoPickAdapter() {
        super(R.layout.item_pick_video);
    }

    @Override
    protected void convert(BaseViewHolder helper, Video item) {
        helper.setImageResource(R.id.iv_check,item.isCheck()?R.mipmap.ic_video_pick_check:R.mipmap.ic_video_pick_select);
        helper.setImageBitmap(R.id.iv_cover,item.getBitmap());
    }




}
