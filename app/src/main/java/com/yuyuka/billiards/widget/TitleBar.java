package com.yuyuka.billiards.widget;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuyuka.billiards.R;


public class TitleBar extends LinearLayout {
    private View mDivider;
    private ImageView mLeftTitleIv;
    private TextView mLeftTitleTv;
    private TextView mTitleTv;
    private ImageView mTitleIv;
    private ImageView mRightTitleIv;
    private ImageView mRightTitleIv2;
    private TextView mRightTitleTv;
    private int style;

    public TitleBar(Context context) {
        super(context);
        init();
    }

    public TitleBar(Context context,int style) {
        super(context);
        this.style = style;
        init();
    }


    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public TitleBar setTitle(CharSequence title){
        if (!TextUtils.isEmpty(title)){
            mTitleTv.setText(title);
        }
        return this;
    }

    public TitleBar setLeftText(CharSequence text, OnClickListener listener){
        mLeftTitleIv.setVisibility(GONE);
        mLeftTitleTv.setVisibility(VISIBLE);
        mLeftTitleTv.setText(text);
        mLeftTitleTv.setOnClickListener(listener);
        return this;
    }

    public TitleBar setLeftImage(int resId, OnClickListener listener){
        mLeftTitleTv.setVisibility(GONE);
        mLeftTitleIv.setVisibility(VISIBLE);
        mLeftTitleIv.setImageResource(resId);
        mLeftTitleIv.setOnClickListener(listener);
        return this;
    }

    public TitleBar setRightText(CharSequence text, OnClickListener listener){
        mRightTitleIv.setVisibility(GONE);
        mRightTitleTv.setVisibility(VISIBLE);
        mRightTitleTv.setText(text);
        mRightTitleTv.setOnClickListener(listener);
        return this;
    }

    public TitleBar setRightImage(int resId, OnClickListener listener){
        mRightTitleTv.setVisibility(GONE);
        mRightTitleIv.setVisibility(VISIBLE);
        mRightTitleIv.setImageResource(resId);
        mRightTitleIv.setOnClickListener(listener);
        return this;
    }

    public TitleBar setRightImage2(int resId, OnClickListener listener){
        mRightTitleTv.setVisibility(GONE);
        mRightTitleIv2.setVisibility(VISIBLE);
        mRightTitleIv2.setImageResource(resId);
        mRightTitleIv2.setOnClickListener(listener);
        return this;
    }



    public TitleBar showBack(){
        int backIcon = R.mipmap.ic_nearby_room_back;
        if (style == 0){
            backIcon = R.mipmap.ic_nearby_room_back;
        }else if (style == 1){
            backIcon = R.mipmap.icon_title_back;

        }else if (style == 2){
            backIcon = R.mipmap.icon_back_white;
        }
        return setLeftImage(backIcon, v -> {
            if (getContext() instanceof Activity){
                ((Activity) getContext()).onBackPressed();
            }
        });
    }

    public TitleBar showDivider(){
        mDivider.setVisibility(VISIBLE);
        return this;
    }

    public TitleBar showTitleImage(int resId){
        mTitleIv.setVisibility(VISIBLE);
        mTitleIv.setImageResource(resId);
        return this;
    }

    public TitleBar hideDivider(){
        mDivider.setVisibility(GONE);
        return this;
    }

    public View getDivider() {
        return mDivider;
    }

    public ImageView getLeftTitleIv() {
        return mLeftTitleIv;
    }

    public TextView getLeftTitleTv() {
        return mLeftTitleTv;
    }

    public TextView getTitleTv() {
        return mTitleTv;
    }

    public ImageView getRightTitleIv() {
        return mRightTitleIv;
    }

    public TextView getRightTitleTv() {
        return mRightTitleTv;
    }

    public void show(){
        setVisibility(VISIBLE);
    }

    public void hide(){
        setVisibility(GONE);
    }

    private void init(){
        setOrientation(VERTICAL);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        if (style == 0)
            inflater.inflate(R.layout.include_title_bar,this,true);
        else if (style == 1)
            inflater.inflate(R.layout.include_title_bar_white,this,true);
        else if (style == 2)
            inflater.inflate(R.layout.include_title_bar_gold,this,true);
        mLeftTitleIv = findViewById(R.id.iv_title_left);
        mLeftTitleTv = findViewById(R.id.tv_title_left);
        mTitleTv = findViewById(R.id.tv_title);
        mRightTitleIv = findViewById(R.id.iv_title_right);
        mRightTitleIv2 = findViewById(R.id.iv_title_right2);
        mRightTitleTv = findViewById(R.id.tv_title_right);
        mDivider = findViewById(R.id.v_title_divider);
        mTitleIv = findViewById(R.id.iv_title);


    }


}
