package com.chinalwb.are.spans;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.style.LeadingMarginSpan;
import android.text.style.QuoteSpan;

import com.chinalwb.are.Constants;

/**
 * Created by wliu on 2018/3/4.
 */

public class AreQuoteSpan extends QuoteSpan {

    @Override
    public int getLeadingMargin(boolean first) {
        return 0; // hard-coded..
    }

    @Override
    public void drawLeadingMargin(Canvas c, Paint p, int x, int dir, int top, int baseline, int bottom, CharSequence text, int start, int end, boolean first, Layout layout) {
        Paint.Style style = p.getStyle();
        int color = p.getColor();

        p.setStyle(Paint.Style.FILL);
        p.setColor(Constants.COLOR_QUOTE);


        p.setStyle(style);
        p.setColor(color);
    }
}
