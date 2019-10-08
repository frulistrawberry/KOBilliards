package com.yuyuka.billiards.widget.richtext;

import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinalwb.are.AREditText;
import com.chinalwb.are.Constants;
import com.chinalwb.are.spans.AreHrSpan;
import com.chinalwb.are.styles.ARE_ABS_FreeStyle;
import com.chinalwb.are.styles.IARE_Style;
import com.chinalwb.are.styles.toolbar.ARE_Toolbar;

public class MyHr   implements MyStyle, IARE_Style {

	private AREditText mEditText;

	private TextView mListNumberImageView;

	public MyHr(TextView imageView, AREditText editText) {
		mListNumberImageView = imageView;
		mEditText = editText;
		setListenerForTextView(mListNumberImageView);
	}

	/**
	 * @param editText
	 */
	public void setEditText(AREditText editText) {
		this.mEditText = editText;
	}

	@Override
	public void setListenerForImageView(ImageView imageView) {

	}

	@Override
	public void applyStyle(Editable editable, int start, int end) {
        // Do nothing
	}


	@Override
	public ImageView getImageView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setChecked(boolean isChecked) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean getIsChecked() {
		return false;
	}

	@Override
	public EditText getEditText() {
		return mEditText;
	}

	@Override
	public void setListenerForTextView(TextView textView) {
		textView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Editable editable = mEditText.getEditableText();
				int start = mEditText.getSelectionStart();
				int end = mEditText.getSelectionEnd();

				SpannableStringBuilder ssb = new SpannableStringBuilder();
				ssb.append(Constants.CHAR_NEW_LINE);
				ssb.append(Constants.CHAR_NEW_LINE);
				ssb.append(Constants.ZERO_WIDTH_SPACE_STR);
				ssb.append(Constants.CHAR_NEW_LINE);
				ssb.setSpan(new AreHrSpan(), 2, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				editable.replace(start, end, ssb);
			}
		});
	}

	@Override
	public TextView getTextView() {
		return null;
	}
}
