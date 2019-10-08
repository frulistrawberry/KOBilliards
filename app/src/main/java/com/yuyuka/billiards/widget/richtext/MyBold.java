package com.yuyuka.billiards.widget.richtext;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chinalwb.are.AREditText;
import com.chinalwb.are.spans.AreBoldSpan;
import com.chinalwb.are.styles.ARE_ABS_Style;
import com.chinalwb.are.styles.ARE_Helper;
import com.yuyuka.billiards.utils.SizeUtils;

public class MyBold extends ARE_ABS_Style<AreBoldSpan> implements MyStyle{

    private TextView mBoldImageView;

    private boolean mBoldChecked;

    private AREditText mEditText;

    /**
     * @param boldImage
     */
    public MyBold(TextView boldImage) {


        super(boldImage.getContext());


        this.mBoldImageView = boldImage;
        setListenerForTextView(this.mBoldImageView);
    }

    /**
     * @param editText
     */
    public void setEditText(AREditText editText) {
        this.mEditText = editText;
    }

    @Override
    public EditText getEditText() {
        return mEditText;
    }

    @Override
    public void setListenerForImageView(final ImageView imageView) {

    }

    @Override
    public ImageView getImageView() {
        return null;
    }

    @Override
    public void setChecked(boolean isChecked) {
        this.mBoldChecked = isChecked;
    }

    @Override
    public boolean getIsChecked() {
        return this.mBoldChecked;
    }

    @Override
    public AreBoldSpan newSpan() {
        return new AreBoldSpan();
    }

    @Override
    public void setListenerForTextView(TextView textView) {
        textView.setOnClickListener(v -> {
            mBoldChecked = !mBoldChecked;
            Helper.updateCheckStatus(this, this,mBoldChecked);
            if (null != mEditText) {
                applyStyle(mEditText.getEditableText(),
                        mEditText.getSelectionStart(),
                        mEditText.getSelectionEnd());
            }
        });
    }

    @Override
    public TextView getTextView() {
        return mBoldImageView;
    }
}
