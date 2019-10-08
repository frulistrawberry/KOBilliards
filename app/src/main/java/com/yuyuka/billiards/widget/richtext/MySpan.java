package com.yuyuka.billiards.widget.richtext;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.style.LineBackgroundSpan;

public  class MySpan implements LineBackgroundSpan {
        private final int color;

        public MySpan(int color) {
            this.color = color;
        }

        @Override
        public void drawBackground(Canvas c, Paint p, int left, int right, int top, int baseline, int bottom, CharSequence text, int start, int end, int lnum) {
            final int paintColor = p.getColor();
            p.setColor(color);
            c.drawRect(new Rect(left, top, right, bottom), p);
            p.setColor(paintColor);
        }
}

