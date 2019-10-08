package com.yuyuka.billiards.ui.activity.news;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.utils.Debuger;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseMvpActivity;
import com.yuyuka.billiards.image.ImageManager;
import com.yuyuka.billiards.mvp.contract.news.NewsContract;
import com.yuyuka.billiards.mvp.presenter.news.NewsContentPresenter;
import com.yuyuka.billiards.pojo.NewsCommentItem;
import com.yuyuka.billiards.pojo.NewsItem;
import com.yuyuka.billiards.pojo.NewsReplyItem;
import com.yuyuka.billiards.ui.fragment.news.NewsCommentFragment;
import com.yuyuka.billiards.ui.fragment.news.NewsReplyFragment;
import com.yuyuka.billiards.utils.SizeUtils;
import com.yuyuka.billiards.utils.ToastUtils;
import com.yuyuka.billiards.widget.dialog.CommentDialog;
import com.yuyuka.billiards.widget.video.MyVideoView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class VideoDetailActivity extends BaseMvpActivity<NewsContentPresenter> implements NewsContract.INewsView {

    OrientationUtils orientationUtils;

    @BindView(R.id.detail_player)
    MyVideoView detailPlayer;
    String url;
    boolean isPlay;
    boolean isPause;

    int id;


    NewsCommentFragment commentFragment;

    public static void launch(Context context,int id,String url,String cover){
        Intent intent = new Intent(context,VideoDetailActivity.class);
        intent.putExtra("id",id);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        setTitleStyle(1);
        setContentView(R.layout.activity_video_detail);
        //外部辅助的旋转，帮助全屏
        orientationUtils = new OrientationUtils(this, detailPlayer);
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false);


        detailPlayer.getFullscreenButton().setOnClickListener(v -> {
            //直接横屏
            orientationUtils.resolveByClick();

            //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
            detailPlayer.startWindowFullscreen(VideoDetailActivity.this, true, true);
        });

        detailPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (orientationUtils != null) {
                    orientationUtils.backToProtVideo();
                }
                if (GSYVideoManager.backFromWindowFull(getContext())) {
                    return;
                }
                finish();
            }
        });

        getPresenter().getNewsInfo(id);
    }

    @Override
    public void onBackPressed() {
        if (orientationUtils != null) {
            orientationUtils.backToProtVideo();
        }
        if (GSYVideoManager.backFromWindowFull(this)) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        detailPlayer.getCurrentPlayer().onVideoPause();
        super.onPause();
        isPause = true;
    }

    @Override
    protected void onResume() {
        detailPlayer.getCurrentPlayer().onVideoResume(false);
        super.onResume();
        isPause = false;
    }

    @Override
    protected NewsContentPresenter getPresenter() {
        return new NewsContentPresenter(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isPlay) {
            detailPlayer.getCurrentPlayer().release();
        }
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }


    @Override
    protected void initData() {
        url = getIntent().getStringExtra("url");
        id = getIntent().getIntExtra("id",0);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //如果旋转了就全屏
        if (isPlay && !isPause) {
            detailPlayer.onConfigurationChanged(this, newConfig, orientationUtils, true, true);
        }
    }
    @Subscribe
    public void onEvent(NewsCommentItem item){
        int messageId = item.getId();
        NewsReplyFragment fragment = NewsReplyFragment.newFragment(messageId,item);
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_container,fragment).addToBackStack(null).commit();
    }



    @Override
    public void showAttentionSuccess(String msg) {

    }

    @Override
    public void showAttentionFailure(String msg) {

    }

    @Override
    public void showCommentSuccess(String msg) {


    }

    @Override
    public void showCommentFailure(String msg) {

    }

    @Override
    public void showPraiseSuccess(String msg) {

    }

    @Override
    public void showPraiseFailure(String msg) {

    }

    @Override
    public void showCommentList(List<NewsCommentItem> data) {

    }

    @Override
    public void showNewsInfo(NewsItem data) {

        ImageView coverImage = new ImageView(this);
        coverImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
        coverImage.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, SizeUtils.dp2px(this,220)));
        ImageManager.getInstance().loadNet(data.getCoverImageAdd(),coverImage);

        GSYVideoOptionBuilder gsyVideoOption = new GSYVideoOptionBuilder();
        gsyVideoOption
                .setIsTouchWiget(true)
                .setRotateViewAuto(false)
                .setLockLand(false)
                .setThumbImageView(coverImage)
                .setAutoFullWithSize(true)
                .setShowFullAnimation(false)
                .setNeedLockFull(true)
                .setUrl(data.getAddress())
                .setCacheWithPlay(false)
                .setVideoTitle("")
                .setVideoAllCallBack(new GSYSampleCallBack() {
                    @Override
                    public void onPrepared(String url, Object... objects) {
                        super.onPrepared(url, objects);
                        //开始播放了才能旋转和全屏
                        orientationUtils.setEnable(true);
                        isPlay = true;
                    }

                    @Override
                    public void onQuitFullscreen(String url, Object... objects) {
                        super.onQuitFullscreen(url, objects);
                        Debuger.printfError("***** onQuitFullscreen **** " + objects[0]);//title
                        Debuger.printfError("***** onQuitFullscreen **** " + objects[1]);//当前非全屏player
                        if (orientationUtils != null) {
                            orientationUtils.backToProtVideo();
                        }
                    }
                }).setLockClickListener(new LockClickListener() {
            @Override
            public void onClick(View view, boolean lock) {
                if (orientationUtils != null) {
                    //配合下方的onConfigurationChanged
                    orientationUtils.setEnable(!lock);
                }
            }
        }).build(detailPlayer);

        commentFragment = NewsCommentFragment.newFragment(data.getId(),data.getBilliardsUsers());
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_container,commentFragment).commit();

    }

    @Override
    public void showReplyList(List<NewsReplyItem> data) {

    }

    @Override
    public void showDisAttenttionSuccess(String msg) {

    }

    @Override
    public void showDisAttentionFailure(String msg) {

    }
}
