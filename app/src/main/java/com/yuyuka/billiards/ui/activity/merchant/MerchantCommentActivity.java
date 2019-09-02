package com.yuyuka.billiards.ui.activity.merchant;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.base.BaseMvpActivity;
import com.yuyuka.billiards.mvp.contract.merchant.MerchantCommentContract;
import com.yuyuka.billiards.mvp.presenter.merchant.MerchantCommentPresenter;
import com.yuyuka.billiards.utils.CollectionUtils;
import com.yuyuka.billiards.utils.RatingBar;
import com.yuyuka.billiards.utils.ToastUtils;
import com.yuyuka.billiards.widget.TagLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class MerchantCommentActivity extends BaseMvpActivity<MerchantCommentPresenter> implements MerchantCommentContract.IMerchantCommentView {
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

    @BindView(R.id.et_advice_content)
    EditText messageInfoEt;
    @BindView(R.id.tag_layout)
    TagLayout mTagLayout;

    Drawable[] mPosDrawableList;
    Drawable[] mNegDrawableList;
    String[] mCommentStr = {"不佳","一般","很好","非常满意","完美体验"};
    private int population = 1;
    private int local = 1;
    private int service = 1;
    private int hygiene = 1;
    private int facilities = 1;
    private String billiardsId;
    private String merchantName;
    public static void launcher(Context context,String billiardsId,String merchantName) {
        Intent intent = new Intent(context, MerchantCommentActivity.class);
        intent.putExtra("billiardsId",billiardsId);
        intent.putExtra("merchantName",merchantName);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle(merchantName).showBack().show();
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
            population = ratingCount;
        });
        mLocationRb.setOnRatingChangeListener(ratingCount -> {
            mLocationTv.setText(mCommentStr[Integer.valueOf(ratingCount-1+"")]);
            local = ratingCount;
        });
        mServiceRb.setOnRatingChangeListener(ratingCount -> {
            mServiceTv.setText(mCommentStr[Integer.valueOf(ratingCount-1+"")]);
            service = ratingCount;
        });
        mHygieneRb.setOnRatingChangeListener(ratingCount -> {
            mHygieneTv.setText(mCommentStr[Integer.valueOf(ratingCount-1+"")]);
            hygiene = ratingCount;
        });
        mFacilitiesRb.setOnRatingChangeListener(ratingCount -> {
            mFacilitiesTv.setText(mCommentStr[Integer.valueOf(ratingCount-1+"")]);
            facilities = ratingCount;
        });

        for (int i = 0; i < mTagLayout.getChildCount(); i++) {
            TextView textView = (TextView) mTagLayout.getChildAt(i);
            textView.setTag(false);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!(Boolean) textView.getTag()){
                        textView.setTag(true);
                        textView.setBackgroundResource(R.drawable.bg_tag_comment_positive);
                    }else {
                        textView.setTag(false);
                        textView.setBackgroundResource(R.drawable.bg_tag_comment_negative);
                    }
                }
            });
        }
    }

    @Override
    protected void initData() {
        mPosDrawableList  = new Drawable[]{getResourceDrawable(R.mipmap.sorryl),getResourceDrawable(R.mipmap.flatl),
                getResourceDrawable(R.mipmap.smile),getResourceDrawable(R.mipmap.happyl),
                getResourceDrawable(R.mipmap.ciyaxiao)};
        mNegDrawableList  = new Drawable[]{getResourceDrawable(R.mipmap.sorry),getResourceDrawable(R.mipmap.flat),
                getResourceDrawable(R.mipmap.smilel),getResourceDrawable(R.mipmap.happy),
                getResourceDrawable(R.mipmap.ciyahui)};
        billiardsId = getIntent().getStringExtra("billiardsId");
        merchantName = getIntent().getStringExtra("merchantName");

    }

    @OnClick(R.id.btn_commit)
    public void onClick(View v){
        List<String> gameTypeList = new ArrayList<>();
        for (int i = 0; i < mTagLayout.getChildCount(); i++) {
            TextView textView = (TextView) mTagLayout.getChildAt(i);
            if ((Boolean) textView.getTag())
                gameTypeList.add(textView.getText().toString());
        }
        if (CollectionUtils.isEmpty(gameTypeList)){
            ToastUtils.showToast(this,"请选择游玩类型");
            return;
        }
        String messageInfo = messageInfoEt.getText().toString();
        if (TextUtils.isEmpty(messageInfo)){
            ToastUtils.showToast(this,"请输入评论内容");
            return;
        }
        getPresenter().comment(billiardsId,messageInfo,gameTypeList,population,local,service,hygiene,facilities);

    }


    @Override
    protected MerchantCommentPresenter getPresenter() {
        return new MerchantCommentPresenter(this);
    }

    @Override
    public void showCommentSuccess() {
        ToastUtils.showToast(this,"评论成功");
        finish();
    }
}
