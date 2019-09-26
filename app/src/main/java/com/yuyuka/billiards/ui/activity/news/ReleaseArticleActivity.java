package com.yuyuka.billiards.ui.activity.news;


import android.content.Intent;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinalwb.are.AREditText;
import com.chinalwb.are.styles.toolbar.IARE_Toolbar;
import com.chinalwb.are.styles.toolitems.ARE_ToolItem_Bold;
import com.chinalwb.are.styles.toolitems.ARE_ToolItem_Hr;
import com.chinalwb.are.styles.toolitems.ARE_ToolItem_ListBullet;
import com.chinalwb.are.styles.toolitems.ARE_ToolItem_ListNumber;
import com.chinalwb.are.styles.toolitems.ARE_ToolItem_Quote;
import com.chinalwb.are.styles.toolitems.ARE_ToolItem_Underline;
import com.chinalwb.are.styles.toolitems.IARE_ToolItem;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.utils.KeyboardUtils;
import com.yuyuka.billiards.utils.log.LogUtil;
import com.yuyuka.billiards.widget.richtext.LinearToolBar;
import com.yuyuka.billiards.widget.richtext.RichEditor;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.OnClick;

public class ReleaseArticleActivity extends BaseActivity implements KeyboardUtils.OnKeyboardVisibilityListener, RichEditor.OnTextChangeListener {

    @BindView(R.id.tool_bar)
    LinearToolBar mToolbar;
    @BindView(R.id.areditor)
    AREditText mRichEditor;

    @BindView(R.id.btn_text)
    ImageView textBtn;
    @BindView(R.id.btn_img)
    ImageView imgBtn;
    @BindView(R.id.btn_complete)
    TextView mCompleteBtn;


    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setLeftText("取消", view -> finish()).setRightText("发布", view -> {

        }).setTitle("发布文章").hideDivider().show();
    }



    @Override
    protected void initView() {
        setTitleStyle(1);
        setContentView(R.layout.activity_release_article);
        KeyboardUtils.setKeyboardListener(this,this);

        IARE_ToolItem bold = new ARE_ToolItem_Bold();
        IARE_ToolItem underline = new ARE_ToolItem_Underline();
        IARE_ToolItem quote = new ARE_ToolItem_Quote();
        IARE_ToolItem listNumber = new ARE_ToolItem_ListNumber();
        IARE_ToolItem listBullet = new ARE_ToolItem_ListBullet();
        IARE_ToolItem hr = new ARE_ToolItem_Hr();
        mToolbar.addToolbarItem(bold);
        mToolbar.addToolbarItem(underline);
        mToolbar.addToolbarItem(quote);
        mToolbar.addToolbarItem(listNumber);
        mToolbar.addToolbarItem(listBullet);
        mToolbar.addToolbarItem(hr);
        mRichEditor.setToolbar(mToolbar);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.btn_img,R.id.btn_text,R.id.btn_complete})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_img:
                break;
            case R.id.btn_text:
                textBtn.setVisibility(View.GONE);
                imgBtn.setVisibility(View.GONE);
                mCompleteBtn.setVisibility(View.VISIBLE);
                mToolbar.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_complete:
                textBtn.setVisibility(View.VISIBLE);
                imgBtn.setVisibility(View.VISIBLE);
                mCompleteBtn.setVisibility(View.GONE);
                mToolbar.setVisibility(View.GONE);
                break;
        }

    }


    @Override
    public void onTextChange(String text) {
        LogUtil.e("mEditor",text);

    }


    @Override
    public void onVisibilityChanged(boolean visible) {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mToolbar.onActivityResult(requestCode, resultCode, data);
    }
}
