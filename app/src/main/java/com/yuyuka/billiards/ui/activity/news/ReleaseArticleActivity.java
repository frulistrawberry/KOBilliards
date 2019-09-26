package com.yuyuka.billiards.ui.activity.news;


import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.utils.KeyboardUtils;
import com.yuyuka.billiards.utils.log.LogUtil;
import com.yuyuka.billiards.widget.richtext.RichEditor;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReleaseArticleActivity extends AppCompatActivity implements KeyboardUtils.OnKeyboardVisibilityListener, RichEditor.OnTextChangeListener {

    boolean isTitle;
    boolean isBold;
    boolean isUnderLine;
    boolean isRef;
    boolean isOl;
    boolean isUl;
    boolean isSpline;

    @BindView(R.id.et_rich)
    RichEditor mRichEditor;
    @BindView(R.id.ll_text_style)
    LinearLayout mTextStyleLayout;
    @BindView(R.id.btn_img)
    ImageView mImgBtn;
    @BindView(R.id.btn_text)
    ImageView mTextBtn;
    @BindView(R.id.btn_complete)
    TextView mCompleteBtn;
    @BindView(R.id.btn_bold)
    TextView mBoldBtn;
    @BindView(R.id.btn_title)
    TextView mTitleBtn;

//    @Override
//    protected void initTitle() {
//        super.initTitle();
//        getTitleBar().setLeftText("取消", view -> finish()).setRightText("发布", view -> {
//
//        }).setTitle("发布文章").hideDivider().show();
//    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

//    @Override
    protected void initView() {
        setContentView(R.layout.activity_release_article);
        ButterKnife.bind(this);
        mRichEditor.setOnTextChangeListener(this);
        mRichEditor.setEditorFontSize(18);
        //输入框显示字体的颜色
        mRichEditor.setEditorFontColor(getResourceColor(R.color.text_color_1));
        //输入框背景设置
        mRichEditor.setEditorBackgroundColor(Color.WHITE);
        //输入框文本padding
        mRichEditor.setPadding(10, 10, 10, 10);
        //输入提示文本
        mRichEditor.setPlaceholder("请输入编辑内容");
        KeyboardUtils.setKeyboardListener(this,this);
    }

//    @Override
//    protected void initData() {
//
//    }


    @Override
    public void onTextChange(String text) {
        LogUtil.e("mEditor",text);

    }

    @OnClick({R.id.btn_text,R.id.btn_title,R.id.btn_bold,R.id.btn_under_line,R.id.btn_ref,R.id.btn_ol,R.id.btn_ul,R.id.btn_spline,R.id.btn_complete})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_text:
                KeyboardUtils.hide(this,v);
                mTextStyleLayout.setVisibility(View.VISIBLE);
                mCompleteBtn.setVisibility(View.VISIBLE);
                mTextBtn.setVisibility(View.GONE);
                mImgBtn.setVisibility(View.GONE);
                break;
            case R.id.btn_complete:
                mTextStyleLayout.setVisibility(View.GONE);
                break;
            case R.id.btn_title:
                if (isTitle){
                    mTitleBtn.setTextColor(getResourceColor(R.color.text_color_1));
                }else {
                    mTitleBtn.setTextColor(getResourceColor(R.color.text_color_6));
                }
                isTitle = !isTitle;
//                mRichEditor.setTitile();
                break;
            case R.id.btn_bold:
                if (isBold){
                    mBoldBtn.setTextColor(getResourceColor(R.color.text_color_1));
                }else {
                    mBoldBtn.setTextColor(getResourceColor(R.color.text_color_6));
                }
                isBold = !isBold;
                mRichEditor.setBold();
                break;
        }
    }

    @Override
    public void onVisibilityChanged(boolean visible) {
        if (visible){
            mTextStyleLayout.setVisibility(View.GONE);
            mCompleteBtn.setVisibility(View.GONE);
            mImgBtn.setVisibility(View.VISIBLE);
            mTextBtn.setVisibility(View.VISIBLE);
            mRichEditor.focusEditor();
        }
    }


    public int getResourceColor(@ColorRes int colorId) {
        return ResourcesCompat.getColor(getResources(), colorId, null);

    }
}
