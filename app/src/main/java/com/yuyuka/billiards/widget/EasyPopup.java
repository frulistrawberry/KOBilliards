package com.yuyuka.billiards.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.support.v4.widget.PopupWindowCompat;
import android.transition.Transition;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;

import com.yuyuka.billiards.utils.ScreenUtils;

public class EasyPopup implements OnDismissListener {
    private static final String TAG = "EasyPopup";
    private static final float DEFAULT_DIM = 0.7F;
    private PopupWindow mPopupWindow;
    private Context mContext;
    protected View mContentView;
    protected int mLayoutId;
    protected boolean mFocusable = true;
    protected boolean mOutsideTouchable = true;
    protected int mWidth;
    protected int mHeight;
    protected int mAnimationStyle;
    private OnDismissListener mOnDismissListener;
    protected boolean isBackgroundDim;
    protected float mDimValue = 0.7F;
    @ColorInt
    protected int mDimColor = -16777216;
    @NonNull
    protected ViewGroup mDimView;
    protected Transition mEnterTransition;
    protected Transition mExitTransition;
    private boolean mFocusAndOutsideEnable;
    private View mAnchorView;
    private int mVerticalGravity = 2;
    private int mHorizontalGravity = 1;
    private int mOffsetX;
    private int mOffsetY;
    private boolean isOnlyGetWH = true;
    private final OnGlobalLayoutListener mOnGlobalLayoutListener = new OnGlobalLayoutListener() {
        public void onGlobalLayout() {
            EasyPopup.this.mWidth = EasyPopup.this.getContentView().getWidth();
            EasyPopup.this.mHeight = EasyPopup.this.getContentView().getHeight();
            if (EasyPopup.this.isOnlyGetWH) {
                EasyPopup.this.removeGlobalLayoutListener();
            } else if (EasyPopup.this.mPopupWindow != null) {
                EasyPopup.this.updateLocation(EasyPopup.this.mWidth, EasyPopup.this.mHeight, EasyPopup.this.mAnchorView, EasyPopup.this.mVerticalGravity, EasyPopup.this.mHorizontalGravity, EasyPopup.this.mOffsetX, EasyPopup.this.mOffsetY);
                EasyPopup.this.removeGlobalLayoutListener();
            }
        }
    };

    public EasyPopup(Context context) {
        this.mContext = context;
    }

    public EasyPopup createPopup() {
        if (this.mPopupWindow == null) {
            this.mPopupWindow = new PopupWindow();
        }

        this.onPopupWindowCreated();
        if (this.mContentView == null) {
            if (this.mLayoutId == 0) {
                throw new IllegalArgumentException("The content view is null");
            }

            this.mContentView = LayoutInflater.from(this.mContext).inflate(this.mLayoutId, (ViewGroup)null);
        }

        this.mPopupWindow.setContentView(this.mContentView);
        if (this.mWidth != 0) {
            this.mPopupWindow.setWidth(this.mWidth);
        } else {
            this.mPopupWindow.setWidth(-2);
        }

        if (this.mHeight != 0) {
            this.mPopupWindow.setHeight(this.mHeight);
        } else {
            this.mPopupWindow.setHeight(-2);
        }

        this.onPopupWindowViewCreated(this.mContentView);
        if (this.mAnimationStyle != 0) {
            this.mPopupWindow.setAnimationStyle(this.mAnimationStyle);
        }

        if (!this.mFocusAndOutsideEnable) {
            this.mPopupWindow.setFocusable(true);
            this.mPopupWindow.setOutsideTouchable(false);
            this.mPopupWindow.setBackgroundDrawable((Drawable)null);
            this.mPopupWindow.getContentView().setFocusable(true);
            this.mPopupWindow.getContentView().setFocusableInTouchMode(true);
            this.mPopupWindow.getContentView().setOnKeyListener(new OnKeyListener() {
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (keyCode == 4) {
                        EasyPopup.this.mPopupWindow.dismiss();
                        return true;
                    } else {
                        return false;
                    }
                }
            });
            this.mPopupWindow.setTouchInterceptor(new OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    int x = (int)event.getX();
                    int y = (int)event.getY();
                    if (event.getAction() != 0 || x >= 0 && x < EasyPopup.this.mWidth && y >= 0 && y < EasyPopup.this.mHeight) {
                        return event.getAction() == 4;
                    } else {
                        return true;
                    }
                }
            });
        } else {
            this.mPopupWindow.setFocusable(this.mFocusable);
            this.mPopupWindow.setOutsideTouchable(this.mOutsideTouchable);
            this.mPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
        }

        this.mPopupWindow.setOnDismissListener(this);
        if (VERSION.SDK_INT >= 23) {
            if (this.mEnterTransition != null) {
                this.mPopupWindow.setEnterTransition(this.mEnterTransition);
            }

            if (this.mExitTransition != null) {
                this.mPopupWindow.setExitTransition(this.mExitTransition);
            }
        }

        return this;
    }

    protected void onPopupWindowCreated() {
    }

    protected void onPopupWindowViewCreated(View contentView) {
    }

    protected void onPopupWindowDismiss() {
    }

    public EasyPopup setContentView(View contentView) {
        this.mContentView = contentView;
        this.mLayoutId = 0;
        return this;
    }

    public EasyPopup setContentView(@LayoutRes int layoutId) {
        this.mContentView = null;
        this.mLayoutId = layoutId;
        return this;
    }

    public EasyPopup setContentView(View contentView, int width, int height) {
        this.mContentView = contentView;
        this.mLayoutId = 0;
        this.mWidth = width;
        this.mHeight = height;
        return this;
    }

    public EasyPopup setContentView(@LayoutRes int layoutId, int width, int height) {
        this.mContentView = null;
        this.mLayoutId = layoutId;
        this.mWidth = width;
        this.mHeight = height;
        return this;
    }

    public EasyPopup setWidth(int width) {
        this.mWidth = width;
        return this;
    }

    public EasyPopup setHeight(int height) {
        this.mHeight = height;
        return this;
    }

    public EasyPopup setAnchorView(View view) {
        this.mAnchorView = view;
        return this;
    }

    public EasyPopup setVerticalGravity(int verticalGravity) {
        this.mVerticalGravity = verticalGravity;
        return this;
    }

    public EasyPopup setHorizontalGravity(int horizontalGravity) {
        this.mHorizontalGravity = horizontalGravity;
        return this;
    }

    public EasyPopup setOffsetX(int offsetX) {
        this.mOffsetX = offsetX;
        return this;
    }

    public EasyPopup setOffsetY(int offsetY) {
        this.mOffsetY = offsetY;
        return this;
    }

    public EasyPopup setAnimationStyle(@StyleRes int animationStyle) {
        this.mAnimationStyle = animationStyle;
        return this;
    }

    public EasyPopup setFocusable(boolean focusable) {
        this.mFocusable = focusable;
        return this;
    }

    public EasyPopup setOutsideTouchable(boolean outsideTouchable) {
        this.mOutsideTouchable = outsideTouchable;
        return this;
    }

    public EasyPopup setFocusAndOutsideEnable(boolean focusAndOutsideEnable) {
        this.mFocusAndOutsideEnable = focusAndOutsideEnable;
        return this;
    }

    public EasyPopup setBackgroundDimEnable(boolean isDim) {
        this.isBackgroundDim = isDim;
        return this;
    }

    public EasyPopup setDimValue(@FloatRange(from = 0.0D,to = 1.0D) float dimValue) {
        this.mDimValue = dimValue;
        return this;
    }

    public EasyPopup setDimColor(@ColorInt int color) {
        this.mDimColor = color;
        return this;
    }

    public EasyPopup setDimView(@NonNull ViewGroup dimView) {
        this.mDimView = dimView;
        return this;
    }

    @RequiresApi(
        api = 23
    )
    public EasyPopup setEnterTransition(Transition enterTransition) {
        this.mEnterTransition = enterTransition;
        return this;
    }

    @RequiresApi(
        api = 23
    )
    public EasyPopup setExitTransition(Transition exitTransition) {
        this.mExitTransition = exitTransition;
        return this;
    }

    public void showAsDropDown() {
        if (this.mAnchorView != null) {
            this.showAsDropDown(this.mAnchorView, this.mOffsetX, this.mOffsetY);
        }
    }

    public void showAsDropDown(View anchor, int offsetX, int offsetY) {
        if (this.mPopupWindow != null) {
            this.isOnlyGetWH = true;
            this.handleBackgroundDim();
            this.mAnchorView = anchor;
            this.mOffsetX = offsetX;
            this.mOffsetY = offsetY;
            this.addGlobalLayoutListener(this.mPopupWindow.getContentView());
            this.mPopupWindow.showAsDropDown(anchor, offsetX, offsetY);
        }

    }

    public void showAsDropDown(View anchor) {
        if (this.mPopupWindow != null) {
            this.handleBackgroundDim();
            this.mAnchorView = anchor;
            this.isOnlyGetWH = true;
            this.addGlobalLayoutListener(this.mPopupWindow.getContentView());
            if (Build.VERSION.SDK_INT < 24) {
                mPopupWindow.showAsDropDown(anchor);
            }else {
                int[] a = new int[2];
                anchor.getLocationInWindow(a);
                mPopupWindow.setHeight(ScreenUtils.getScreenHeight(mContext)-a[1]-anchor.getHeight());
                mPopupWindow.showAtLocation(anchor, Gravity.NO_GRAVITY,a[0], anchor.getHeight()+a[1]);
                mPopupWindow.update();
            }
        }



    }

    @RequiresApi(
        api = 19
    )
    public void showAsDropDown(View anchor, int offsetX, int offsetY, int gravity) {
        if (this.mPopupWindow != null) {
            this.handleBackgroundDim();
            this.mAnchorView = anchor;
            this.mOffsetX = offsetX;
            this.mOffsetY = offsetY;
            this.isOnlyGetWH = true;
            this.addGlobalLayoutListener(this.mPopupWindow.getContentView());
            PopupWindowCompat.showAsDropDown(this.mPopupWindow, anchor, offsetX, offsetY, gravity);
        }

    }

    public void showAtLocation(View parent, int gravity, int offsetX, int offsetY) {
        if (this.mPopupWindow != null) {
            this.handleBackgroundDim();
            this.mAnchorView = parent;
            this.mOffsetX = offsetX;
            this.mOffsetY = offsetY;
            this.isOnlyGetWH = true;
            this.addGlobalLayoutListener(this.mPopupWindow.getContentView());
            this.mPopupWindow.showAtLocation(parent, gravity, offsetX, offsetY);
        }

    }

    public void showAtAnchorView() {
        if (this.mAnchorView != null) {
            this.showAtAnchorView(this.mAnchorView, this.mVerticalGravity, this.mHorizontalGravity);
        }
    }

    public void showAtAnchorView(@NonNull View anchor, int vertGravity, int horizGravity) {
        this.showAtAnchorView(anchor, vertGravity, horizGravity, 0, 0);
    }

    public void showAtAnchorView(@NonNull View anchor, int vertGravity, int horizGravity, int x, int y) {
        if (this.mPopupWindow != null) {
            this.mAnchorView = anchor;
            this.mOffsetX = x;
            this.mOffsetY = y;
            this.mVerticalGravity = vertGravity;
            this.mHorizontalGravity = horizGravity;
            this.isOnlyGetWH = false;
            this.handleBackgroundDim();
            View contentView = this.getContentView();
            this.addGlobalLayoutListener(contentView);
            contentView.measure(0, 0);
            int measuredW = contentView.getMeasuredWidth();
            int measuredH = contentView.getMeasuredHeight();
            x = this.calculateX(anchor, horizGravity, measuredW, x);
            y = this.calculateY(anchor, vertGravity, measuredH, y);
            PopupWindowCompat.showAsDropDown(this.mPopupWindow, anchor, x, y, 0);
        }
    }

    private int calculateY(View anchor, int vertGravity, int measuredH, int y) {
        switch(vertGravity) {
        case 0:
            y -= anchor.getHeight() / 2 + measuredH / 2;
            break;
        case 1:
            y -= measuredH + anchor.getHeight();
        case 2:
        default:
            break;
        case 3:
            y -= anchor.getHeight();
            break;
        case 4:
            y -= measuredH;
        }

        return y;
    }

    private int calculateX(View anchor, int horizGravity, int measuredW, int x) {
        switch(horizGravity) {
        case 0:
            x += anchor.getWidth() / 2 - measuredW / 2;
            break;
        case 1:
            x -= measuredW;
            break;
        case 2:
            x += anchor.getWidth();
        case 3:
        default:
            break;
        case 4:
            x -= measuredW - anchor.getWidth();
        }

        return x;
    }

    private void updateLocation(int width, int height, @NonNull View anchor, int vertGravity, int horizGravity, int x, int y) {
        x = this.calculateX(anchor, horizGravity, width, x);
        y = this.calculateY(anchor, vertGravity, height, y);
        this.mPopupWindow.update(anchor, x, y, width, height);
    }

    public EasyPopup setOnDismissListener(OnDismissListener listener) {
        this.mOnDismissListener = listener;
        return this;
    }

    private void handleBackgroundDim() {
        if (VERSION.SDK_INT >= 18 && this.isBackgroundDim) {
            if (this.mDimView != null) {
                this.applyDim(this.mDimView);
            } else if (this.getContentView() != null) {
                Activity activity = (Activity)this.getContentView().getContext();
                if (activity != null) {
                    this.applyDim(activity);
                }
            }
        }

    }

    @RequiresApi(
        api = 18
    )
    private void applyDim(Activity activity) {
        ViewGroup parent = (ViewGroup)activity.getWindow().getDecorView().getRootView();
        Drawable dim = new ColorDrawable(this.mDimColor);
        dim.setBounds(0, 0, parent.getWidth(), parent.getHeight());
        dim.setAlpha((int)(255.0F * this.mDimValue));
        ViewGroupOverlay overlay = parent.getOverlay();
        overlay.add(dim);
    }

    @RequiresApi(
        api = 18
    )
    private void applyDim(ViewGroup dimView) {
        Drawable dim = new ColorDrawable(this.mDimColor);
        dim.setBounds(0, 0, dimView.getWidth(), dimView.getHeight());
        dim.setAlpha((int)(255.0F * this.mDimValue));
        ViewGroupOverlay overlay = dimView.getOverlay();
        overlay.add(dim);
    }

    private void clearBackgroundDim() {
        if (VERSION.SDK_INT >= 18 && this.isBackgroundDim) {
            if (this.mDimView != null) {
                this.clearDim(this.mDimView);
            } else if (this.getContentView() != null) {
                Activity activity = (Activity)this.getContentView().getContext();
                if (activity != null) {
                    this.clearDim(activity);
                }
            }
        }

    }

    @RequiresApi(
        api = 18
    )
    private void clearDim(Activity activity) {
        ViewGroup parent = (ViewGroup)activity.getWindow().getDecorView().getRootView();
        ViewGroupOverlay overlay = parent.getOverlay();
        overlay.clear();
    }

    @RequiresApi(
        api = 18
    )
    private void clearDim(ViewGroup dimView) {
        ViewGroupOverlay overlay = dimView.getOverlay();
        overlay.clear();
    }

    public View getContentView() {
        return this.mPopupWindow != null ? this.mPopupWindow.getContentView() : null;
    }

    public Context getContext() {
        return this.mContext;
    }

    public PopupWindow getPopupWindow() {
        return this.mPopupWindow;
    }

    public View getView(@IdRes int viewId) {
        View view = null;
        if (this.getContentView() != null) {
            view = this.getContentView().findViewById(viewId);
        }

        return view;
    }

    public void dismiss() {
        if (this.mPopupWindow != null) {
            this.mPopupWindow.dismiss();
        }

    }

    public void onDismiss() {
        this.handleDismiss();
    }

    private void handleDismiss() {
        if (this.mOnDismissListener != null) {
            this.mOnDismissListener.onDismiss();
        }

        this.removeGlobalLayoutListener();
        this.clearBackgroundDim();
        if (this.mPopupWindow != null && this.mPopupWindow.isShowing()) {
            this.mPopupWindow.dismiss();
        }

        this.onPopupWindowDismiss();
    }

    private void addGlobalLayoutListener(View contentView) {
        contentView.getViewTreeObserver().addOnGlobalLayoutListener(this.mOnGlobalLayoutListener);
    }

    private void removeGlobalLayoutListener() {
        if (this.getContentView() != null) {
            if (VERSION.SDK_INT >= 16) {
                this.getContentView().getViewTreeObserver().removeOnGlobalLayoutListener(this.mOnGlobalLayoutListener);
            } else {
                this.getContentView().getViewTreeObserver().removeGlobalOnLayoutListener(this.mOnGlobalLayoutListener);
            }
        }

    }
}
