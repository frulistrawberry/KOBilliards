package com.yuyuka.billiards.widget.keyboard;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.support.annotation.IntegerRes;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;

import com.yuyuka.billiards.R;


/**
 * Created by xud on 2017/3/2.
 */

public abstract class BaseKeyboard extends Keyboard implements KeyboardView.OnKeyboardActionListener{

    private EditText mEditText;

    private View mNextFocusView;

    private KeyStyle mKeyStyle;

    private Context mContext;

    public BaseKeyboard(Context context, int xmlLayoutResId) {
        super(context, xmlLayoutResId);
        mContext = context;
    }

    public BaseKeyboard(Context context, int xmlLayoutResId, int modeId, int width, int height) {
        super(context, xmlLayoutResId, modeId, width, height);
        mContext = context;
    }

    public BaseKeyboard(Context context, int xmlLayoutResId, int modeId) {
        super(context, xmlLayoutResId, modeId);
        mContext = context;
    }

    public BaseKeyboard(Context context, int layoutTemplateResId, CharSequence characters, int columns, int horizontalPadding) {
        super(context, layoutTemplateResId, characters, columns, horizontalPadding);
        mContext = context;
    }

    public void setEditText(EditText editText) {
        mEditText = editText;
    }

    public void setNextFocusView(View nextFocusView) {
        mNextFocusView = nextFocusView;
    }

    public void setKeyStyle(KeyStyle keyStyle) {
        mKeyStyle = keyStyle;
    }

    public EditText getEditText() {
        return mEditText;
    }

    public View getNextFocusView() {
        return mNextFocusView;
    }

    public KeyStyle getKeyStyle() {
        return mKeyStyle;
    }

    public int getKeyCode(@IntegerRes int redId) {
        return mContext.getResources().getInteger(redId);
    }

    @Override
    public void onPress(int primaryCode) {

    }

    @Override
    public void onRelease(int primaryCode) {

    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        if(null != mEditText && mEditText.hasFocus() && !handleSpecialKey(primaryCode)) {
            Editable editable = mEditText.getText();
            int start = mEditText.getSelectionStart();
            int end = mEditText.getSelectionEnd();
            if (end > start){
                editable.delete(start,end);
            }
            if(primaryCode == KEYCODE_DELETE) {
                if(!TextUtils.isEmpty(editable)) {
                    if(start > 0) {
                        editable.delete(start-1,start);
                    }
                }
            }else if(primaryCode == getKeyCode(R.integer.hide_keyboard)){
                hideKeyboard();
            }else {
                editable.insert(start, Character.toString((char) primaryCode));
            }
        }
    }

    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }

    public void hideKeyboard() {
       if(mNextFocusView != null) {
           mNextFocusView.requestFocus();
       }
    }

    /**
     *
     * @param primaryCode
     * @return true if handle the key
     *         false no handle and dispatch
     */
    public abstract boolean handleSpecialKey(int primaryCode);

    public interface KeyStyle {

        public Drawable getKeyBackound(Key key);

        public Float getKeyTextSize(Key key);

        public Integer getKeyTextColor(Key key);

        public CharSequence getKeyLabel(Key key);
    }

    public Padding getPadding() {
        return new Padding(0,0,0,0);
    }

    public static class DefaultKeyStyle implements KeyStyle {
        private Context mContext;

        public DefaultKeyStyle(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public Drawable getKeyBackound(Key key) {
            if(key.iconPreview != null) {
                return key.iconPreview;
            } else {
                return ContextCompat.getDrawable(mContext, R.drawable.key_number_bg);
            }
        }

        @Override
        public Float getKeyTextSize(Key key) {
            if(key.codes[0] == mContext.getResources().getInteger(R.integer.action_done)) {
                return convertSpToPixels(mContext, 20f);
            }
            return convertSpToPixels(mContext, 24f);
        }

        @Override
        public Integer getKeyTextColor(Key key) {
            if(key.codes[0] == mContext.getResources().getInteger(R.integer.action_done)) {
                return Color.WHITE;
            }
            return null;
        }

        @Override
        public CharSequence getKeyLabel(Key key) {
            return key.label;
        }
    }

    public static class Padding {
        int top;
        int left;
        int bottom;
        int right;

        /**
         * px
         * @param top
         * @param left
         * @param bottom
         * @param right
         */
        public Padding(int top, int left, int bottom, int right) {
            this.top = top;
            this.left = left;
            this.bottom = bottom;
            this.right = right;
        }
    }

    public static float convertSpToPixels(Context context, float sp) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
        return px;
    }
}
