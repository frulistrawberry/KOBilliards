package com.yuyuka.billiards.widget;

import android.content.Context;

import android.util.AttributeSet;
import android.widget.LinearLayout;


public class CoinTextView extends LinearLayout {

    public CoinTextView(Context context) {
        this(context,null);
    }

    public CoinTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(HORIZONTAL);
    }
}
