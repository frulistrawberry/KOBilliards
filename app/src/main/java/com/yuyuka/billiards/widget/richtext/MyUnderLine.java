package com.yuyuka.billiards.widget.richtext;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinalwb.are.AREditText;
import com.chinalwb.are.spans.AreBoldSpan;
import com.chinalwb.are.spans.AreUnderlineSpan;
import com.chinalwb.are.styles.ARE_ABS_Style;
import com.chinalwb.are.styles.ARE_Helper;
import com.chinalwb.are.styles.ARE_Underline;

public class MyUnderLine extends ARE_ABS_Style<AreUnderlineSpan> implements MyStyle {
    private TextView mUnderlineImageView;

    private boolean mUnderlineChecked;

    private AREditText mEditText;

    public MyUnderLine(TextView imageView) {
        super(imageView.getContext());
        this.mUnderlineImageView = imageView;
        setListenerForTextView(this.mUnderlineImageView);
    }

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
        this.mUnderlineChecked = isChecked;
    }

    @Override
    public boolean getIsChecked() {
        return this.mUnderlineChecked;
    }

    @Override
    public AreUnderlineSpan newSpan() {
        return new AreUnderlineSpan();
    }

    @Override
    public void setListenerForTextView(TextView textView) {
        textView.setOnClickListener(v -> {
            mUnderlineChecked = !mUnderlineChecked;
            Helper.updateCheckStatus(this,this, mUnderlineChecked);
            if (null != mEditText) {
                applyStyle(mEditText.getEditableText(), mEditText.getSelectionStart(), mEditText.getSelectionEnd());
            }
        });
    }

    @Override
    public TextView getTextView() {
        return mUnderlineImageView;
    }
}
