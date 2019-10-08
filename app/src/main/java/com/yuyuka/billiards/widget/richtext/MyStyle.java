package com.yuyuka.billiards.widget.richtext;

import android.widget.TextView;

import com.chinalwb.are.styles.IARE_Style;

public interface MyStyle {

    /**
     * For styles like Bold / Italic / Underline, by clicking the ImageView,
     * we should change the UI, so user can notice that this style takes
     * effect now.
     *
     * @param textView
     */
    void setListenerForTextView(TextView textView);


    /**
     * Returns the {@link TextView} of this style.
     *
     * @return
     */
    TextView getTextView();

}
