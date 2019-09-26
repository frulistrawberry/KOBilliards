package com.yuyuka.billiards.widget.richtext;

import android.content.Context;
import android.view.View;

import com.chinalwb.are.styles.IARE_Style;
import com.chinalwb.are.styles.toolitems.ARE_ToolItem_Abstract;
import com.chinalwb.are.styles.toolitems.IARE_ToolItem_Updater;

public class ToolbarItem extends ARE_ToolItem_Abstract {

    int style;
    @Override
    public IARE_Style getStyle() {
        return null;
    }

    @Override
    public View getView(Context context) {
        return null;
    }

    @Override
    public void onSelectionChanged(int selStart, int selEnd) {

    }

    @Override
    public IARE_ToolItem_Updater getToolItemUpdater() {
        return null;
    }
}
