package com.yuyuka.billiards.widget.richtext;

import android.graphics.Color;
import android.text.Editable;
import android.text.Spannable;
import android.text.Spanned;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinalwb.are.AREditText;
import com.chinalwb.are.Constants;
import com.chinalwb.are.Util;
import com.chinalwb.are.spans.ListNumberSpan;
import com.chinalwb.are.styles.ARE_Helper;
import com.chinalwb.are.styles.IARE_Style;

public class MyQuote implements IARE_Style,MyStyle {

    private TextView mQuoteImageView;

    private boolean mQuoteChecked;

    private AREditText mEditText;

    private boolean mRemovedNewLine;


    /**
     * @param quoteImageView
     */
    public MyQuote(TextView quoteImageView) {
        this.mQuoteImageView = quoteImageView;
        setListenerForTextView(this.mQuoteImageView);
    }

    /**
     * @param editText
     */
    public void setEditText(AREditText editText) {
        this.mEditText = editText;
    }

    @Override
    public void setListenerForImageView(final ImageView imageView) {

    }

    /**
     * @return
     */
    private void makeLineAsQuote() {
        EditText editText = getEditText();
        int currentLine = Util.getCurrentCursorLine(editText);
        int start = Util.getThisLineStart(editText, currentLine);
        int end = Util.getThisLineEnd(editText, currentLine);
        Editable editable = editText.getText();
        editable.insert(start, Constants.ZERO_WIDTH_SPACE_STR);
        start = Util.getThisLineStart(editText, currentLine);
        end = Util.getThisLineEnd(editText, currentLine);

        if (editable.charAt(end - 1) == Constants.CHAR_NEW_LINE) {
            end--;
        }

        MySpan[] existingQuoteSpans = editable.getSpans(start, end, MySpan.class);
        if (existingQuoteSpans != null && existingQuoteSpans.length > 0) {
            return;
        }
        ListNumberSpan[] listNumberSpans = editable.getSpans(start,end,ListNumberSpan.class);

        if (start > 2) {
            existingQuoteSpans = editable.getSpans(start - 2, start, MySpan.class);
            if (existingQuoteSpans != null && existingQuoteSpans.length > 0) {
                // Merge forward
                int quoteStart = editable.getSpanStart(existingQuoteSpans[0]);
                editable.setSpan(existingQuoteSpans[0], quoteStart, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                return;
            }
        }
        MySpan quoteSpan = new MySpan(Color.parseColor("#F8F8F8"));
        if (listNumberSpans!=null&&listNumberSpans.length>0){
            for (ListNumberSpan listNumberSpan : listNumberSpans) {
                editable.removeSpan(listNumberSpan);
            }
        }

        editable.setSpan(quoteSpan, start, end,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        ARE_Helper.updateCheckStatus(MyQuote.this, true);
    }

    private void removeQuote() {
        EditText editText = getEditText();
        Editable editable = editText.getText();
        int currentLine = Util.getCurrentCursorLine(editText);
        int start = Util.getThisLineStart(editText, currentLine);
        int end = Util.getThisLineEnd(editText, currentLine);
        MySpan[] quoteSpans = null;
        if (start == 0) {
            quoteSpans = editable.getSpans(start, end, MySpan.class);
            if (quoteSpans!=null && quoteSpans.length>0)
            editable.removeSpan(quoteSpans[0]);
            return;
        } else {
            quoteSpans = editable.getSpans(start - 1, end, MySpan.class);
        }
        if (quoteSpans == null || quoteSpans.length == 0) {
            quoteSpans = editable.getSpans(start, end, MySpan.class);
            if (quoteSpans != null && quoteSpans.length == 0) {
                return;
            }
        }
        int quoteStart = editable.getSpanStart(quoteSpans[0]);
        editable.removeSpan(quoteSpans[0]);
        if (start > quoteStart) {
            editable.setSpan(quoteSpans[0], quoteStart, start - 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        }
    }

    @Override
    public void applyStyle(Editable editable, int start, int end) {
//        Util.log("Quote apply style, start == " + start + ", end == " + end + ", is quote checked == " + mQuoteChecked);
//        if (!mQuoteChecked) {
//            return;
//        }

        MySpan[] quoteSpans = editable.getSpans(start, end, MySpan.class);
        if (null == quoteSpans || quoteSpans.length == 0) {
            return;
        }

        // Handle \n and backspace
        if (end > start) {
            // User inputs
            char c = editable.charAt(end - 1);
            if (c == Constants.CHAR_NEW_LINE) {
                editable.append(Constants.ZERO_WIDTH_SPACE_STR);
            }
        } else {
            // User deletes
            MySpan quoteSpan = quoteSpans[0];
            int spanStart = editable.getSpanStart(quoteSpan);
            int spanEnd = editable.getSpanEnd(quoteSpan);
            Util.log("Delete spanStart = " + spanStart + ", spanEnd = "
                    + spanEnd + " ,, start == " + start);
            if (spanStart == spanEnd) {
                setChecked(false);
                ARE_Helper.updateCheckStatus(MyQuote.this, false);
                removeQuote();
            }
            if (end > 2) {
                if (mRemovedNewLine) {
                    mRemovedNewLine = false;
                    return;
                }
                char pChar = editable.charAt(end - 1);
                if (pChar == Constants.CHAR_NEW_LINE) {
                    //
                    // This case
                    // |aa
                    // |
                    // When user deletes at the first of the 2nd line (i.e.: ZERO_WIDTH_STR)
                    // We want to put cursor to the end of the previous line "aa"
                    mRemovedNewLine = true;
                    editable.delete(end - 1, end);
                }
            }
        }
    }

    @Override
    public ImageView getImageView() {
        return null;
    }

    @Override
    public void setChecked(boolean isChecked) {
        this.mQuoteChecked = isChecked;
    }

    @Override
    public boolean getIsChecked() {
        return this.mQuoteChecked;
    }

    @Override
    public EditText getEditText() {
        return this.mEditText;
    }

    @Override
    public void setListenerForTextView(TextView textView) {
        textView.setOnClickListener(v -> {
            mQuoteChecked = !mQuoteChecked;
            Helper.updateCheckStatus(this,this, mQuoteChecked);
            if (null != mEditText) {
                if (mQuoteChecked) {
                    makeLineAsQuote();
                } else {
                    removeQuote();
                }
            }
        });
    }

    @Override
    public TextView getTextView() {
        return mQuoteImageView;
    }
}
