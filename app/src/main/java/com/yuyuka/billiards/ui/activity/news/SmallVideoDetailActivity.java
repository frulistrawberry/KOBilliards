package com.yuyuka.billiards.ui.activity.news;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseMvpActivity;
import com.yuyuka.billiards.image.ImageManager;
import com.yuyuka.billiards.image.support.LoadOption;
import com.yuyuka.billiards.mvp.contract.news.NewsContract;
import com.yuyuka.billiards.mvp.presenter.news.NewsContentPresenter;
import com.yuyuka.billiards.pojo.NewsCommentItem;
import com.yuyuka.billiards.pojo.NewsItem;
import com.yuyuka.billiards.pojo.NewsReplyItem;
import com.yuyuka.billiards.ui.fragment.news.SmallVideoCommentFragment;
import com.yuyuka.billiards.utils.ToastUtils;
import com.yuyuka.billiards.widget.dialog.CommentDialog;
import com.yuyuka.billiards.widget.video.EmptyControlVideo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SmallVideoDetailActivity extends BaseMvpActivity<NewsContentPresenter> implements NewsContract.INewsView {

    @BindView(R.id.video_player)
    EmptyControlVideo videoPlayer;
    int id;
    @BindView(R.id.iv_head)
    ImageView mHeadIv;
    boolean isAttention;
    @BindView(R.id.btn_attention)
    TextView attentionBtn;
    int userId;
    CommentDialog mDialog;

    public static void launcher(Context context ,int id){
        Intent intent = new Intent(context,SmallVideoDetailActivity.class);
        intent.putExtra("id",id);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_small_video_detail);
        mStatusBar.setVisibility(View.GONE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            //下面这一行呢是android4.0起推荐大家使用的将布局延伸到状态栏的代码，配合5.0的设置状态栏颜色可谓天作之合
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }



        getPresenter().getNewsInfo(id);


    }

    @Override
    protected void initData() {
        id = getIntent().getIntExtra("id",0);
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void onEvent(NewsCommentItem item){
        int messageId = item.getId();
        SmallVideoCommentFragment fragment = SmallVideoCommentFragment.newFragment(messageId,1);
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_container,fragment).addToBackStack(null).commit();
    }


    @OnClick({R.id.btn_attention,R.id.btn_zan,R.id.btn_comment,R.id.discuss_tv})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_attention:
                if (!isAttention)
                    getPresenter().attention(userId);
                else
                    getPresenter().disattention(userId);
                break;
            case R.id.btn_zan:
                getPresenter().appreciate(id);
                break;
            case R.id.btn_comment:
                SmallVideoCommentFragment fragment = SmallVideoCommentFragment.newFragment(id,0);
                getSupportFragmentManager().beginTransaction().replace(R.id.layout_container,fragment).addToBackStack(null).commit();
                break;
            case R.id.discuss_tv:
                mDialog = new CommentDialog(getContext());
                mDialog.setOnCommentListener(content -> {
                    getPresenter().comment(id,content);
                });
                mDialog.show();
                break;
        }
    }


    @Override
    protected NewsContentPresenter getPresenter() {
        return new NewsContentPresenter(this);
    }

    @Override
    public void showAttentionSuccess(String msg) {
        ToastUtils.showToast(getContext(),msg);
        isAttention = true;
        attentionBtn.setBackgroundResource(R.drawable.bg_news_attention_gray);
        attentionBtn.setText("已关注");
    }

    @Override
    public void showAttentionFailure(String msg) {
        ToastUtils.showToast(getContext(),msg);
    }

    @Override
    public void showCommentSuccess(String msg) {

    }

    @Override
    public void showCommentFailure(String msg) {

    }

    @Override
    public void showPraiseSuccess(String msg) {
        ToastUtils.showToast(getContext(),msg);
    }

    @Override
    public void showPraiseFailure(String msg) {
        ToastUtils.showToast(getContext(),msg);

    }

    @Override
    public void showCommentList(List<NewsCommentItem> data) {

    }

    @Override
    public void showNewsInfo(NewsItem data) {
        videoPlayer.setUp(data.getAddress(), true, "");
        videoPlayer.setLooping(true);
        videoPlayer.startPlayLogic();
        ImageManager.getInstance().loadNet(data.getBilliardsUsers().getHeadImage(),mHeadIv,new LoadOption().setIsCircle(true));
        userId = data.getUserId();


    }

    @Override
    public void showReplyList(List<NewsReplyItem> data) {

    }

    @Override
    public void showDisAttenttionSuccess(String msg) {
        ToastUtils.showToast(getContext(),msg);
        isAttention = false;
        attentionBtn.setBackgroundResource(R.drawable.bg_news_attention_orange);
        attentionBtn.setText("关注");
    }

    @Override
    public void showDisAttentionFailure(String msg) {
        ToastUtils.showToast(getContext(),msg);
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoPlayer.release();
        videoPlayer.setVideoAllCallBack(null);
        GSYVideoManager.releaseAllVideos();
    }






}
