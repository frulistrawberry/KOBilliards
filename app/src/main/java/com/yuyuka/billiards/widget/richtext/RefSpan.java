package com.yuyuka.billiards.widget.richtext;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.style.ReplacementSpan;

import com.chinalwb.are.spans.ARE_Span;

public class RefSpan extends ReplacementSpan implements ARE_Span {
    @Override
    public int getSize(@NonNull Paint paint, CharSequence charSequence, int i, int i1, @Nullable Paint.FontMetricsInt fontMetricsInt) {
        return 0;
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence charSequence, int i, int i1, float v, int i2, int i3, int i4, @NonNull Paint paint) {

    }

    @Override
    public String getHtml() {
        return null;
    }
}
