package com.yuyuka.billiards.ui.activity.news;


import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shuyu.gsyvideoplayer.utils.GSYVideoHelper;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.ui.adapter.news.VideoPickAdapter;
import com.yuyuka.billiards.utils.CollectionUtils;
import com.yuyuka.billiards.utils.FileManager;
import com.yuyuka.billiards.utils.VideoUtils;
import com.yuyuka.billiards.utils.bean.Video;
import com.yuyuka.billiards.widget.video.MyVideoView;

import java.util.List;
import java.util.logging.LogRecord;

import butterknife.BindView;
import butterknife.OnClick;

public class VideoPickerActivity extends BaseActivity {

    @BindView(R.id.video_player)
    MyVideoView detailPayer;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    VideoPickAdapter adapter;

    FileManager manager;

    List<Video> data;
    ImageView imageView;


    Handler handler;

    Video video;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_video_picker);
        mStatusBar.setBackgroundColor(getResourceColor(R.color.black));
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(adapter);
        imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        detailPayer.getBackButton().setVisibility(View.GONE);
        detailPayer.getFullscreenButton().setVisibility(View.GONE);
        showProgressDialog();
        new InitThread().start();
    }

    @Override
    protected void initData() {
        manager = FileManager.getInstance(this);
        handler = new Handler(message -> {
            if (!CollectionUtils.isEmpty(data)){
                Video video = data.get(0);
                detailPayer.setUp("file://"+video.getPath(),false,"");
                imageView.setImageBitmap(video.getBitmap());
                detailPayer.setThumbImageView(imageView);
                dismissProgressDialog();
            }
            adapter.setNewData(data);
            return false;
        });
        adapter = new VideoPickAdapter();
        adapter.setOnItemClickListener((adapter, view, position) -> {
            for (int i = 0; i < adapter.getData().size(); i++) {
                Video video = data.get(i);
                video.setCheck(i==position);
            }
            detailPayer.release();
            detailPayer.setUp("file://"+data.get(position).getPath(),false,"");
            imageView.setImageBitmap(data.get(position).getBitmap());
            detailPayer.startPlayLogic();
            video = data.get(position);
            adapter.notifyDataSetChanged();

        });

    }

    class InitThread extends Thread {
        @Override
        public void run() {
            super.run();
            data = manager.getVideos();
            if (!CollectionUtils.isEmpty(data)){
                data.get(0).setCheck(true);
                for (Video datum : data) {
                    datum.setBitmap(VideoUtils.getVideoThumb(datum.getPath()));
                }
                video = data.get(0);
            }
            handler.sendEmptyMessage(0);
        }
    }

    @OnClick({R.id.btn_cancel,R.id.btn_next})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_cancel:
                finish();
                break;
            case R.id.btn_next:
                Intent intent = new Intent(this,ReleaseVideoActivity.class);
                intent.putExtra("video",video.getPath());
                startActivity(intent);
                break;
        }
    }


}
