package com.yuyuka.billiards.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.utils.SizeUtils;

/**
 * 自动跟换背景的按钮
 */
public class StateButton extends AppCompatButton {
    public static final int DEFAULT_CLICKABLE_COLOR = Color.parseColor("#F1A82A");
    public static final int DEFAULT_UN_CLICKABLE_COLOR = Color.parseColor("#F1A82A");
    public static final int DEFAULT_PRESS_COLOR = Color.parseColor("#F1A82A");

    private int mColor;//可点击背景色
    private int mPressColor;//按下背景色
    private int mUnClickableColor;//不可点击背景色
    private int mRadius;//圆角半径
    private int mStrokeColor;//边缘线颜色

    private Paint mPaint;
    private Paint mStrokePaint;

    public StateButton(Context context) {
        this(context,null,0);
    }

    public StateButton(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public StateButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.StateButton);
        mColor = array.getColor(R.styleable.StateButton_normalColor,DEFAULT_CLICKABLE_COLOR);
        mPressColor = array.getColor(R.styleable.StateButton_pressColor,DEFAULT_PRESS_COLOR);
        mUnClickableColor = array.getColor(R.styleable.StateButton_unClickableColor,DEFAULT_UN_CLICKABLE_COLOR);
        mRadius = array.getDimensionPixelSize(R.styleable.StateButton_RoundedConnerRadius,0);
        mStrokeColor = array.getColor(R.styleable.StateButton_strokeColor,0);
        array.recycle();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(mColor);
        if (mStrokeColor!=0){
            mStrokePaint = new Paint();
            mStrokePaint.setAntiAlias(true);
            mStrokePaint.setDither(true);
            mStrokePaint.setStyle(Paint.Style.STROKE);
            mStrokePaint.setColor(mStrokeColor);
            mStrokePaint.setStrokeWidth(SizeUtils.dp2px(getContext(),1));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float left = mStrokeColor==0?0:SizeUtils.dp2px(getContext(),1);
        float top = mStrokeColor==0?0:SizeUtils.dp2px(getContext(),1);
        float right = mStrokeColor==0?getMeasuredWidth():getMeasuredWidth()-SizeUtils.dp2px(getContext(),1);
        float bottom = mStrokeColor==0?getMeasuredHeight():getMeasuredHeight()-SizeUtils.dp2px(getContext(),1);
        RectF rectF = new RectF(left,top,right,bottom);
        canvas.drawRoundRect(rectF,mRadius,mRadius,mPaint);
        if (mStrokeColor!=0)
            canvas.drawRoundRect(rectF,mRadius,mRadius,mStrokePaint);
        super.onDraw(canvas);
    }
    public void setCanClick(boolean canClick){
        setClickable(canClick);
        mPaint.setColor(canClick?mColor:mUnClickableColor);
        postInvalidate();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isClickable()){
        switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    mPaint.setColor(mPressColor);
                    postInvalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    mPaint.setColor(mColor);
                    postInvalidate();
                    break;
                case MotionEvent.ACTION_CANCEL:
                    mPaint.setColor(mColor);
                    postInvalidate();
                    break;

        }}

        return super.onTouchEvent(event);
    }

    public void setBackground(int clickableColor,int unClickableColor,int pressColor) {
        mColor = clickableColor;
        mUnClickableColor = unClickableColor;
        mPressColor = pressColor;
        mPaint.setColor(mColor);
        postInvalidate();

    }

}
