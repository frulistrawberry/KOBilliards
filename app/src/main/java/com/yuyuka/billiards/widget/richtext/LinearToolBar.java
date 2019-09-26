package com.yuyuka.billiards.widget.richtext;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.chinalwb.are.AREditText;
import com.chinalwb.are.styles.toolbar.IARE_Toolbar;
import com.chinalwb.are.styles.toolitems.IARE_ToolItem;

import java.util.ArrayList;
import java.util.List;

public class LinearToolBar extends FrameLayout implements IARE_Toolbar {

    private AREditText mAREditText;
    private List<IARE_ToolItem> mToolItems = new ArrayList<>();
    private Context mContext;
    private LinearLayout mContainer;

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
        mContainer = new LinearLayout(getContext());
        mContainer.setOrientation(LinearLayout.VERTICAL);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        mContainer.setGravity(Gravity.CENTER_VERTICAL);
        mContainer.setLayoutParams(params);
        this.addView(mContainer);
    }

    @Override
    public void addToolbarItem(IARE_ToolItem toolbarItem) {
        toolbarItem.setToolbar(this);
        mToolItems.add(toolbarItem);
        View view = toolbarItem.getView(mContext);
        if (view != null) {
            mContainer.addView(view);
        }
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
}
