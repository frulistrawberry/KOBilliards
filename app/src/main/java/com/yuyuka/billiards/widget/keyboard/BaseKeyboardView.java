package com.yuyuka.billiards.widget.keyboard;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;


import com.yuyuka.billiards.utils.SizeUtils;

import java.util.List;

/**
 * Created by xud on 2017/3/2.
 */

public class BaseKeyboardView extends KeyboardView {

    private static final String TAG = "BaseKeyboardView";
    private Drawable rKeyBackground;
    private int rLabelTextSize;
    private int rKeyTextSize;
    private int rKeyTextColor;
    private float rShadowRadius;
    private int rShadowColor;

    private Rect rClipRegion = new Rect(0, 0, 0, 0);

    private Keyboard.Key rInvalidatedKey;

    private Paint paint;

    private Rect padding;

    public BaseKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public BaseKeyboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BaseKeyboardView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {

        rKeyBackground = new ColorDrawable(Color.WHITE);
        rLabelTextSize = SizeUtils.dp2px(context,14);
        rKeyTextSize = SizeUtils.dp2px(context,14);
        rKeyTextColor = Color.BLACK;
        rShadowColor = Color.GRAY;
        rShadowRadius = 0;

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(rKeyTextSize);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAlpha(255);

        padding = new Rect(0, 0, 0, 0);

    }

    @Override
    public void onDraw(Canvas canvas) {
        //说明CustomKeyboardView只针对CustomBaseKeyboard键盘进行重绘,
        // 且CustomBaseKeyboard必需有设置CustomKeyStyle的回调接口实现, 才进行重绘, 这才有意义

        if (null == getKeyboard() || !(getKeyboard() instanceof BaseKeyboard) || null == ((BaseKeyboard) getKeyboard()).getKeyStyle()) {
            Log.e(TAG, "");
            super.onDraw(canvas);
            return;
        }
        super.onDraw(canvas);
        onRefreshKey(canvas);
    }

    @Override
    public void invalidateKey(int keyIndex) {
        try {
            super.invalidateKey(keyIndex);
            rInvalidatedKey = getKeyboard().getKeys().get(keyIndex);
        }catch (Exception e){

        }

    }

    /**
     * onRefreshKey是对父类的private void onBufferDraw()进行的重写. 只是在对key的绘制过程中进行了重新设置.
     *
     * @param canvas
     */
    private void onRefreshKey(Canvas canvas) {


        paint.setColor(rKeyTextColor);
        final int kbdPaddingLeft = getPaddingLeft();
        final int kbdPaddingTop = getPaddingTop();
        Drawable keyBackground = null;

        final Rect clipRegion = rClipRegion;
        final Keyboard.Key invalidKey = rInvalidatedKey;
        boolean drawSingleKey = false;
        if (invalidKey != null && canvas.getClipBounds(clipRegion)) {
            // Is clipRegion completely contained within the invalidated key?
            if (invalidKey.x + kbdPaddingLeft - 1 <= clipRegion.left &&
                    invalidKey.y + kbdPaddingTop - 1 <= clipRegion.top &&
                    invalidKey.x + invalidKey.width + kbdPaddingLeft + 1 >= clipRegion.right &&
                    invalidKey.y + invalidKey.height + kbdPaddingTop + 1 >= clipRegion.bottom) {
                drawSingleKey = true;
            }
        }

        //拿到当前键盘被弹起的输入源 和 键盘为每个key的定制实现customKeyStyle
        EditText etCur = ((BaseKeyboard) getKeyboard()).getEditText();
        BaseKeyboard.KeyStyle customKeyStyle = ((BaseKeyboard) getKeyboard()).getKeyStyle();

        List<Keyboard.Key> keys = getKeyboard().getKeys();
        final int keyCount = keys.size();
        //canvas.drawColor(0x00000000, PorterDuff.Mode.CLEAR);
        for (int i = 0; i < keyCount; i++) {
            final Keyboard.Key key = keys.get(i);
            if (drawSingleKey && invalidKey != key) {
                continue;
            }

            //获取为Key自定义的背景, 若没有定制, 使用KeyboardView的默认属性keyBackground设置
            keyBackground = customKeyStyle.getKeyBackound(key);
            if (null == keyBackground) {
                keyBackground = rKeyBackground;
            }

            int[] drawableState = key.getCurrentDrawableState();
            keyBackground.setState(drawableState);

            //获取为Key自定义的Label, 若没有定制, 使用xml布局中指定的
            CharSequence keyLabel = customKeyStyle.getKeyLabel(key);
            if (null == keyLabel) {
                keyLabel = key.label;
            }
            // Switch the character to uppercase if shift is pressed
            String label = keyLabel == null ? null : adjustCase(keyLabel).toString();

            final Rect bounds = keyBackground.getBounds();
            if (key.width != bounds.right ||
                    key.height != bounds.bottom) {
                keyBackground.setBounds(0, 0, key.width, key.height);
            }
            canvas.translate(key.x + kbdPaddingLeft, key.y + kbdPaddingTop);
            keyBackground.draw(canvas);

            if (label != null) {
                //获取为Key的Label的字体大小, 若没有定制, 使用KeyboardView的默认属性keyTextSize设置
                Float customKeyTextSize = customKeyStyle.getKeyTextSize(key);
                // For characters, use large font. For labels like "Done", use small font.
                if (null != customKeyTextSize) {
                    paint.setTextSize(customKeyTextSize);
                    paint.setTypeface(Typeface.DEFAULT);
                    //paint.setTypeface(Typeface.DEFAULT_BOLD);
                } else {
                    if (label.length() > 1 && key.codes.length < 2) {
                        paint.setTextSize(rLabelTextSize);
                        paint.setTypeface(Typeface.DEFAULT);
                        //paint.setTypeface(Typeface.DEFAULT_BOLD);
                    } else {
                        paint.setTextSize(rKeyTextSize);
                        paint.setTypeface(Typeface.DEFAULT);
                        //paint.setTypeface(Typeface.DEFAULT);
                    }
                }

                //获取为Key的Label的字体颜色, 若没有定制, 使用KeyboardView的默认属性keyTextColor设置
                Integer customKeyTextColor = customKeyStyle.getKeyTextColor(key);
                if (null != customKeyTextColor) {
                    paint.setColor(customKeyTextColor);
                } else {
                    paint.setColor(rKeyTextColor);
                }
                // Draw a drop shadow for the text
                paint.setShadowLayer(rShadowRadius, 0, 0, rShadowColor);
                // Draw the text
                canvas.drawText(label,
                        (key.width - padding.left - padding.right) / 2
                                + padding.left,
                        (key.height - padding.top - padding.bottom) / 2
                                + (paint.getTextSize() - paint.descent()) / 2 + padding.top,
                        paint);
                // Turn off drop shadow
                paint.setShadowLayer(0, 0, 0, 0);
            } else if (key.icon != null) {
                final int drawableX = (key.width - padding.left - padding.right
                        - key.icon.getIntrinsicWidth()) / 2 + padding.left;
                final int drawableY = (key.height - padding.top - padding.bottom
                        - key.icon.getIntrinsicHeight()) / 2 + padding.top;
                canvas.translate(drawableX, drawableY);
                key.icon.setBounds(0, 0,
                        key.icon.getIntrinsicWidth(), key.icon.getIntrinsicHeight());
                key.icon.draw(canvas);
                canvas.translate(-drawableX, -drawableY);
            }
            canvas.translate(-key.x - kbdPaddingLeft, -key.y - kbdPaddingTop);
        }
        rInvalidatedKey = null;
    }

    private CharSequence adjustCase(CharSequence label) {
        if (getKeyboard().isShifted() && label != null && label.length() < 3
                && Character.isLowerCase(label.charAt(0))) {
            label = label.toString().toUpperCase();
        }
        return label;
    }
}
