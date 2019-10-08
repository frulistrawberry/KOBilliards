package com.yuyuka.billiards.widget.richtext;

import android.view.View;
import android.widget.TextView;

import com.chinalwb.are.Constants;
import com.chinalwb.are.styles.IARE_Style;

public class Helper {

    public static void updateCheckStatus(IARE_Style areStyle,MyStyle myStyle, boolean checked) {
        areStyle.setChecked(checked);
        TextView textView = myStyle.getTextView();
        int color = checked ? Constants.CHECKED_COLOR : Constants.UNCHECKED_COLOR;
        textView.setTextColor(color);
    }

}
