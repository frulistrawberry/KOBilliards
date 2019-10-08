package com.yuyuka.billiards.widget.richtext;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chinalwb.are.AREditText;
import com.chinalwb.are.Util;
import com.chinalwb.are.styles.IARE_Style;
import com.chinalwb.are.styles.toolitems.ARE_ToolItem_Bold;
import com.chinalwb.are.styles.toolitems.IARE_ToolItem_Updater;
import com.chinalwb.are.styles.toolitems.styles.ARE_Style_Bold;
import com.google.android.exoplayer2.text.Cue;
import com.yuyuka.billiards.utils.SizeUtils;

public class MyToolItemBold extends ARE_ToolItem_Bold {

    public MyToolItemBold(View itemView) {
        mToolItemView = itemView;
    }

    @Override
    public IARE_Style getStyle() {
        if (mStyle == null) {
            mStyle = new MyBold((TextView) mToolItemView);
        }
        return mStyle;
    }

    @Override
    public View getView(Context context) {
        if (null == context) {
            return mToolItemView;
        }
        if (mToolItemView == null) {
            TextView imageView = new TextView(context);
            int padding = SizeUtils.dp2px(context,10);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            imageView.setPadding(padding,padding,padding,padding);
            imageView.setTextSize(SizeUtils.px2sp(context,28));
            imageView.setLayoutParams(params);
            imageView.setText("加粗");
            mToolItemView = imageView;
        }

        return mToolItemView;
    }
}
