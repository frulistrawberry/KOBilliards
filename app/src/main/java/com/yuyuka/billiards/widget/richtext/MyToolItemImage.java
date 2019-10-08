package com.yuyuka.billiards.widget.richtext;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chinalwb.are.AREditText;
import com.chinalwb.are.R;
import com.chinalwb.are.Util;
import com.chinalwb.are.spans.AreImageSpan;
import com.chinalwb.are.strategies.ImageStrategy;
import com.chinalwb.are.styles.IARE_Style;
import com.chinalwb.are.styles.toolitems.ARE_ToolItem_Abstract;
import com.chinalwb.are.styles.toolitems.IARE_ToolItem_Updater;
import com.chinalwb.are.styles.toolitems.styles.ARE_Style_Image;

/**
 * Created by wliu on 13/08/2018.
 */

public class MyToolItemImage extends ARE_ToolItem_Abstract {

    private AREditText editText;

    private View.OnClickListener onClickListener;

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public MyToolItemImage(AREditText editText, ImageView imageView) {
        this.editText = editText;
        this.mToolItemView = imageView;
    }

    @Override
    public IARE_ToolItem_Updater getToolItemUpdater() {
        return null;
    }

    public void setImageView(ImageView imageView){
        mToolItemView = imageView;
    }

    @Override
    public IARE_Style getStyle() {
        if (mStyle == null) {
            mStyle = new ARE_Style_Image(editText, (ImageView) mToolItemView);
            ((ARE_Style_Image)mStyle).setOnImageClick(onClickListener);
        }
        return mStyle;
    }

    @Override
    public View getView(Context context) {
        if (null == context) {
            return mToolItemView;
        }
        if (mToolItemView == null) {
            ImageView imageView = new ImageView(context);
            int size = Util.getPixelByDp(context, 40);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size, size);
            imageView.setLayoutParams(params);
            imageView.setImageResource(R.drawable.image);
            imageView.bringToFront();
            mToolItemView = imageView;
        }

        return mToolItemView;
    }

    @Override
    public void onSelectionChanged(int selStart, int selEnd) {
        return;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (ARE_Style_Image.REQUEST_CODE == requestCode) {
                ARE_Style_Image imageStyle = (ARE_Style_Image) getStyle();
                Uri uri = data.getData();
                ImageStrategy imageStrategy = this.getEditText().getImageStrategy();
                if (imageStrategy != null) {
                    imageStrategy.uploadAndInsertImage(uri, imageStyle);
                    return;
                }
                imageStyle.insertImage(uri, AreImageSpan.ImageType.URI);
            }
        }
    }
}
