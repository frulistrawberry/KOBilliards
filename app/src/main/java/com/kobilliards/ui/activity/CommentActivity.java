package com.kobilliards.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.kobilliards.R;
import com.kobilliards.base.BaseActivity;
import com.kobilliards.utils.RatingBar;

import butterknife.BindView;


public class CommentActivity extends BaseActivity {
    @BindView(R.id.ratingbar)
    RatingBar ratingbar;

    public static void launcher(Context context) {
        Intent intent = new Intent(context, CommentActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("球房名称").showBack().show();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.layout_comment_activity);
        ratingbar.setClickable(true);
        ratingbar.setStepSize(RatingBar.StepSize.Full);
        ratingbar.setStar(0f);//设置显示的星星个数
        ratingbar.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float ratingCount) {

            }
        });
    }

    @Override
    protected void initData() {

    }


}
