package com.yuyuka.billiards.ui.activity.rearbyroom;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import com.kobilliards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.utils.RatingBar;

import butterknife.BindView;


public class CommentActivity extends BaseActivity {
    @BindView(R.id.rb_total)
    RatingBar mTotalRb;
    @BindView(R.id.rb_location)
    RatingBar mLocationRb;
    @BindView(R.id.rb_service)
    RatingBar mServiceRb;
    @BindView(R.id.rb_hygiene)
    RatingBar mHygieneRb;
    @BindView(R.id.rb_facilities)
    RatingBar mFacilitiesRb;

    @BindView(R.id.tv_total)
    TextView mTotalTv;
    @BindView(R.id.tv_location)
    TextView mLocationTv;
    @BindView(R.id.tv_service)
    TextView mServiceTv;
    @BindView(R.id.tv_hygiene)
    TextView mHygieneTv;
    @BindView(R.id.tv_facilities)
    TextView mFacilitiesTv;

    Drawable[] mPosDrawableList;
    Drawable[] mNegDrawableList;
    String[] mCommentStr = {"不佳","一般","很好","非常满意","完美体验"};
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
        mLocationRb.setStarEmptyDrawableList(mNegDrawableList);
        mLocationRb.setStarFillDrawableList(mPosDrawableList);
        mServiceRb.setStarEmptyDrawableList(mNegDrawableList);
        mServiceRb.setStarFillDrawableList(mPosDrawableList);
        mHygieneRb.setStarEmptyDrawableList(mNegDrawableList);
        mHygieneRb.setStarFillDrawableList(mPosDrawableList);
        mFacilitiesRb.setStarEmptyDrawableList(mNegDrawableList);
        mFacilitiesRb.setStarFillDrawableList(mPosDrawableList);
        mTotalRb.init();
        mLocationRb.init();
        mServiceRb.init();
        mHygieneRb.init();
        mFacilitiesRb.init();
        mTotalRb.setOnRatingChangeListener(ratingCount -> {
            mTotalTv.setText(mCommentStr[Integer.valueOf(ratingCount-1+"")]);
        });
        mLocationRb.setOnRatingChangeListener(ratingCount -> {
            mLocationTv.setText(mCommentStr[Integer.valueOf(ratingCount-1+"")]);
        });
        mServiceRb.setOnRatingChangeListener(ratingCount -> {
            mServiceTv.setText(mCommentStr[Integer.valueOf(ratingCount-1+"")]);
        });
        mHygieneRb.setOnRatingChangeListener(ratingCount -> {
            mHygieneTv.setText(mCommentStr[Integer.valueOf(ratingCount-1+"")]);
        });
        mFacilitiesRb.setOnRatingChangeListener(ratingCount -> {
            mFacilitiesTv.setText(mCommentStr[Integer.valueOf(ratingCount-1+"")]);
        });
    }

    @Override
    protected void initData() {
        mPosDrawableList  = new Drawable[]{getResourceDrawable(R.mipmap.sorryl),getResourceDrawable(R.mipmap.flatl),
                getResourceDrawable(R.mipmap.smile),getResourceDrawable(R.mipmap.happyl),
                getResourceDrawable(R.mipmap.ciyaxiao)};
        mNegDrawableList  = new Drawable[]{getResourceDrawable(R.mipmap.sorry),getResourceDrawable(R.mipmap.flat),
                getResourceDrawable(R.mipmap.smilel),getResourceDrawable(R.mipmap.happy),
                getResourceDrawable(R.mipmap.ciyahui)};

    }


}
