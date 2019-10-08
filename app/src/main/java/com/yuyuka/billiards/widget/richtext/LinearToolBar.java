package com.yuyuka.billiards.widget.richtext;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chinalwb.are.AREditText;
import com.chinalwb.are.styles.ARE_Image;
import com.chinalwb.are.styles.toolbar.IARE_Toolbar;
import com.chinalwb.are.styles.toolitems.ARE_ToolItem_Image;
import com.chinalwb.are.styles.toolitems.IARE_ToolItem;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.utils.KeyboardUtils;

import java.util.ArrayList;
import java.util.List;

public class LinearToolBar extends LinearLayout implements IARE_Toolbar, View.OnClickListener {

    private AREditText mAREditText;
    private List<IARE_ToolItem> mToolItems = new ArrayList<>();
    private Context mContext;
    private LinearLayout mContainer;
    private ImageView mTextBtn;
    private ImageView mImgBtn;
    private TextView mCompleteBtn;

    public LinearToolBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LinearToolBar(Context context) {
        this(context,null,0);
    }

    public LinearToolBar(Context context, @Nullable AttributeSet attrs,int style) {
        super(context, attrs,style);
        mContext = context;
        init();
    }

    private void init(){
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        inflater.inflate(R.layout.layout_linear_tool_bar,this,true);
        mContainer = findViewById(R.id.ll_container);
        mTextBtn = findViewById(R.id.btn_text);
        mImgBtn = findViewById(R.id.btn_img);
        mCompleteBtn = findViewById(R.id.btn_complete);
        mTextBtn.setOnClickListener(this);
        mImgBtn.setOnClickListener(this);
        mCompleteBtn.setOnClickListener(this);
        mContainer.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mContainer.setGravity(Gravity.CENTER_VERTICAL);
        mContainer.setLayoutParams(params);
    }

    @Override
    public void addToolbarItem(IARE_ToolItem toolbarItem) {
        toolbarItem.setToolbar(this);
        mToolItems.add(toolbarItem);
        if (toolbarItem instanceof MyToolItemImage){
            return;
        }
        View view = toolbarItem.getView(mContext);
        if (view != null) {
            mContainer.addView(view);
        }
    }

    public ImageView getmImgBtn() {
        return mImgBtn;
    }

    @Override
    public List<IARE_ToolItem> getToolItems() {
        return mToolItems;
    }

    @Override
    public void setEditText(AREditText editText) {
        this.mAREditText = editText;
    }

    @Override
    public AREditText getEditText() {
        return mAREditText;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        for (IARE_ToolItem toolItem : this.mToolItems) {
            toolItem.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_text:
                mCompleteBtn.setVisibility(VISIBLE);
                mContainer.setVisibility(VISIBLE);
                mImgBtn.setVisibility(GONE);
                mTextBtn.setVisibility(GONE);
                KeyboardUtils.hide(getContext(),this);
                break;
            case R.id.btn_img:
                break;
            case R.id.btn_complete:
                mContainer.setVisibility(GONE);
                mImgBtn.setVisibility(VISIBLE);
                mTextBtn.setVisibility(VISIBLE);
                mCompleteBtn.setVisibility(GONE);
                break;
        }
    }

    public void hide(){
        mContainer.setVisibility(GONE);
        mImgBtn.setVisibility(VISIBLE);
        mTextBtn.setVisibility(VISIBLE);
        mCompleteBtn.setVisibility(GONE);
    }
}
