package com.yuyuka.billiards.ui.activity.news;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseMvpActivity;
import com.yuyuka.billiards.image.ImageManager;
import com.yuyuka.billiards.mvp.contract.news.ReleaseContract;
import com.yuyuka.billiards.mvp.presenter.news.ReleasePresenter;
import com.yuyuka.billiards.net.ProgressListener;
import com.yuyuka.billiards.pojo.Tag;
import com.yuyuka.billiards.pojo.UploadResult;
import com.yuyuka.billiards.utils.SizeUtils;
import com.yuyuka.billiards.utils.ToastUtils;
import com.yuyuka.billiards.utils.VideoUtils;
import com.yuyuka.billiards.utils.bean.Video;
import com.yuyuka.billiards.utils.log.LogUtil;
import com.yuyuka.billiards.widget.TagLayout;
import com.yuyuka.billiards.widget.TitleBar;
import com.yuyuka.billiards.widget.video.MyVideoView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ReleaseVideoActivity extends BaseMvpActivity<ReleasePresenter> implements ReleaseContract.IReleaseView {

    String video;
    @BindView(R.id.detail_player)
    MyVideoView player;
    @BindView(R.id.et_title)
    EditText mTitleEt;
    @BindView(R.id.et_desc)
    EditText mDescEt;
    @BindView(R.id.tag_layout)
    TagLayout tagLayout;
    List<Tag> tagList;
    String tags;
    String address;
    String cover;
    ImageView imageView;

    ProgressDialog dialog;

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("发布视频").setLeftText("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }).setRightText("发布", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                getPresenter().releaseNews();
            }
        }).showDivider().show();
        getTitleBar().getRightTitleTv().setTextColor(getResourceColor(R.color.text_color_6));
    }

    @Override
    protected ReleasePresenter getPresenter() {
        return new ReleasePresenter(this);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_video_release);
        dialog = new ProgressDialog(this);
        dialog.show();
        getPresenter().upload(video, 0, (hasWrittenLen, totalLen, hasFinish) -> {
            dialog.setProgress((int) (hasWrittenLen*100/totalLen));
            LogUtil.e("HttpLog",hasWrittenLen+","+totalLen+","+hasFinish);
        });
        player.setUp("file://"+video,false,"");
        imageView = new ImageView(this);
        player.setThumbImageView(imageView);
        player.getBackButton().setVisibility(View.GONE);
        player.getFullscreenButton().setVisibility(View.GONE);
        imageView.setImageBitmap(VideoUtils.getVideoThumb(video));

    }

    @Override
    protected void initData() {
        video =  getIntent().getStringExtra("video");
    }

    @Override
    public void showReleaseSuccess(String msg) {
        ToastUtils.showToast(this,msg);
        finish();
    }

    @Override
    public void showReleaseFailure(String msg) {
        ToastUtils.showToast(this,msg);
    }

    @Override
    public void showTags(List<Tag> tags) {
        tagLayout.removeAllViews();
        tagList = tags;
        for (int i = 0; i < tagList.size(); i++) {
            int margin = SizeUtils.dp2px(this,5);
            TextView textView = new TextView(this);
            ViewGroup.LayoutParams parentParams = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setLayoutParams(parentParams);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(SizeUtils.dp2px(this,60),SizeUtils.dp2px(this,22));
            params.setMargins(margin,margin,margin,margin);
            textView.setLayoutParams(params);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
            textView.setTextColor(Color.WHITE);
            textView.setBackgroundResource(R.drawable.bg_tag_comment_negative);
            textView.setGravity(Gravity.CENTER);
            textView.setText(tagList.get(i).getTagName());
            linearLayout.addView(textView);
            boolean isSelected = tagList.get(i).isSelected();
            if (tagList.get(i).isSelected()){
                textView.setBackgroundResource(R.drawable.bg_tag_comment_positive);
            }else {
                textView.setBackgroundResource(R.drawable.bg_tag_comment_negative);
            }
            int finalI = i;
            textView.setOnClickListener(view -> {
                String tagStr = "";
                tagList.get(finalI).setSelected(!isSelected);
                showTags(tagList);
                for (int j = 0; j < tagList.size(); j++) {
                    if (tagList.get(j).isSelected()){
                        tagStr += tagList.get(j).getMark()+",";

                    }
                }
                if (TextUtils.isEmpty(tagStr))
                    this.tags = "";
                else
                    this.tags = tagStr.substring(0,tagStr.lastIndexOf(","));
            });
            tagLayout.addView(linearLayout);
        }
    }

    @Override
    public void showUploadSuccess(UploadResult url) {
        if (url.getIndex() == 0){
            address = url.getUrl();
            LogUtil.e("IM",address);
        }else {
            cover = url.getUrl();
            ImageManager.getInstance().loadNet(cover,imageView);
        }
        dialog.dismiss();
    }

    @Override
    public void showUploadFailure(int index) {
        if (index == 0){
//            ToastUtils.showToast(this,"视频上传失败");
        }else{
            ToastUtils.showToast(this,"封面上传失败");
        }
        dialog.dismiss();

    }

}
