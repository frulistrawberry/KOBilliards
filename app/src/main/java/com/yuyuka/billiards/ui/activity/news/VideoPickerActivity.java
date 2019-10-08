package com.yuyuka.billiards.ui.activity.news;


import android.database.Cursor;
import android.support.v7.widget.RecyclerView;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.utils.FileManager;
import com.yuyuka.billiards.widget.video.MyVideoView;

import butterknife.BindView;

public class VideoPickerActivity extends BaseActivity {

    @BindView(R.id.video_player)
    MyVideoView detailPayer;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    FileManager manager;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_video_picker);
    }

    @Override
    protected void initData() {
        manager = FileManager.getInstance(this);

    }


}
