package com.yuyuka.billiards.ui.activity.course;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseMvpActivity;
import com.yuyuka.billiards.image.ImageManager;
import com.yuyuka.billiards.image.support.LoadOption;
import com.yuyuka.billiards.mvp.contract.course.CourseDetailContract;
import com.yuyuka.billiards.mvp.presenter.course.CourseDetailPresenter;
import com.yuyuka.billiards.pojo.KOListPojo;
import com.yuyuka.billiards.utils.SizeUtils;
import com.yuyuka.billiards.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class CourseDetailActivity extends BaseMvpActivity<CourseDetailPresenter> implements CourseDetailContract.ICourseDetailView {
    OrientationUtils orientationUtils;

    @BindView(R.id.detail_player)
    StandardGSYVideoPlayer detailPlayer;
    String url;
    boolean isPlay;
    boolean isPause;

    @BindView(R.id.tv_info)
    TextView infoTv;
    @BindView(R.id.iv_cover)
    ImageView coverIv;

    int id;

    int type;

    public static void launcher(Context context,int id,int type){
        Intent intent = new Intent(context,CourseDetailActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("type",type);
        context.startActivity(intent);
    }


    @Override
    protected CourseDetailPresenter getPresenter() {
        return new CourseDetailPresenter(this);
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setRightImage(R.mipmap.ic_news_info_collect, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPresenter().collect(id);
            }
        }).setRightImage2(R.mipmap.ic_news_info_share, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }).showBack().show();
    }

    @Override
    protected void initView() {
        setTitleStyle(1);
        setContentView(R.layout.activity_course_detail);
        orientationUtils = new OrientationUtils(this, detailPlayer);
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false);


        detailPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直接横屏
                orientationUtils.resolveByClick();

                //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                detailPlayer.startWindowFullscreen(CourseDetailActivity.this, true, true);
            }

        });

        detailPlayer.getBackButton().setVisibility(View.GONE);
        getPresenter().getCourseInfo(id);

    }

    @Override
    protected void initData() {
        id = getIntent().getIntExtra("id",0);
        type = getIntent().getIntExtra("type",0);
    }

    @Override
    public void showCourseInfo(KOListPojo data) {
        ImageManager.getInstance().loadNet(data.getImageAdd(),coverIv,new LoadOption().setRoundRadius(SizeUtils.dp2px(this,4)));
        infoTv.setText(data.getInfo());
        getTitleBar().setTitle(data.getTitle());
        detailPlayer.setUp(data.getAddress(),true,"");
    }

    @Override
    public void showCollectSuccess(String msg) {
        ToastUtils.showToast(this,msg);
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
    protected void onResume() {
        detailPlayer.getCurrentPlayer().onVideoResume(false);
        super.onResume();
        isPause = false;
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
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //如果旋转了就全屏
        if (isPlay && !isPause) {
            detailPlayer.onConfigurationChanged(this, newConfig, orientationUtils, true, true);
        }
    }

    @OnClick(R.id.btn_zan)
    public void onClick(View v){
        getPresenter().appreciate(id);
    }

}
