package com.yuyuka.billiards.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.utils.ScreenUtils;


public class AlertDialog implements View.OnClickListener{
    private static Context mContext;
    private TextView mTitleTv;
    private Button mNegativeBtn;
    private Button mPositiveBtn;
    private LinearLayout mDoubleLayout;
    private Dialog mDialog;
    private View mDialogView;
    private Builder mBuilder;

    public AlertDialog(Builder builder){
        mBuilder = builder;
        mDialog = new Dialog(mContext, R.style.DialogTheme);
        mDialogView = View.inflate(mContext,R.layout.dialog_alert,null);
        mTitleTv = mDialogView.findViewById(R.id.tv_title);
        mPositiveBtn = mDialogView.findViewById(R.id.btn_positive);
        mNegativeBtn = mDialogView.findViewById(R.id.btn_negative);
        mDoubleLayout = mDialogView.findViewById(R.id.layout_double);
        if (builder.getmNegativeBtnColor()!=-1){
            mNegativeBtn.setTextColor(builder.getmNegativeBtnColor());
        }
        mDialogView.setMinimumWidth((int) (ScreenUtils.getScreenWidth(mContext) * 0.75));
        mDialog.setContentView(mDialogView);
        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
        layoutParams.width = (int) (ScreenUtils.getScreenWidth(mContext) * 0.75);
        layoutParams.gravity = Gravity.CENTER;
        initDialog();
    }

    private void initDialog() {
        mDialog.setCancelable(mBuilder.isCancelable());
        if (TextUtils.isEmpty(mBuilder.getTitle())){
            mTitleTv.setVisibility(View.GONE);
        }else {
            mTitleTv.setText(mBuilder.getTitle());
            mTitleTv.setVisibility(View.VISIBLE);
        }

        mPositiveBtn.setText(mBuilder.getPositiveText());
        mNegativeBtn.setText(mBuilder.getNegativeText());
        mPositiveBtn.setOnClickListener(this);
        mNegativeBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_positive:
                if (mBuilder.getPositiveClcikListener() != null) {
                    mBuilder.getPositiveClcikListener().onClick(v);
                }
                break;
            case R.id.btn_negative:
                if (mBuilder.getNegativeClickListener()!=null){
                    mBuilder.getNegativeClickListener().onClick(v);
                }
                break;
        }
        dismiss();
    }

    public void show(){
        if (mDialog != null) {
            mDialog.show();
        }
    }

    public void dismiss(){
        if (mDialog != null&&mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    public Window getWindow(){
        return mDialog.getWindow();
    }



    public static class Builder{
        private final String DEFAULT_POSITIVE_TEXT = "确定";
        private final String DEFAULT_NEGATIVE_TEXT = "取消";

        private String mTitle;
        private String mMessage;
        private String mPositiveText;
        private String mNegativeText;
        private String mSingleText;
        private String mHighLightText;
        private boolean mCancelable = true;
        private int mNegativeBtnColor = -1;

        private View.OnClickListener mPositiveClickListener;
        private View.OnClickListener mNegativeClickListener;
        private View.OnClickListener mSingleClickLisener;

        public Builder(Context context){
            mContext = context;
            mPositiveText = DEFAULT_POSITIVE_TEXT;
            mNegativeText = DEFAULT_NEGATIVE_TEXT;
            mSingleText = DEFAULT_POSITIVE_TEXT;
        }

        public Builder setTitle(String title){
            mTitle = title;
            return this;
        }
        public String getTitle(){
            return mTitle;
        }
        public Builder setMessage(String message){
            mMessage = message;
            return this;
        }

        public Builder setHighLightText(String text){
            mHighLightText = text;
            return this;
        }

        public String getHighLightText(){
            return mHighLightText;
        }

        public String getMessage(){
            return mMessage;
        }

        public Builder setNegativeBtnColor(int color){
            this.mNegativeBtnColor = color;
            return this;
        }

        public int getmNegativeBtnColor() {
            return mNegativeBtnColor;
        }

        public Builder setPositiveButton(String text, View.OnClickListener listener){
            if (!TextUtils.isEmpty(text))
                mPositiveText = text;
            if (listener != null) {
                mPositiveClickListener = listener;
            }
            return this;
        }

        public Builder setPositiveButton(View.OnClickListener listener){
            return setPositiveButton(null,listener);
        }

        public Builder setPositiveButton(String text){
            return setPositiveButton(text,null);
        }


        public Builder setNegativeButton(String text, View.OnClickListener listener){
            if (!TextUtils.isEmpty(text))
                mNegativeText = text;
            if (listener != null) {
                mNegativeClickListener = listener;
            }
            return this;
        }

        public Builder setNegativeButton(View.OnClickListener listener){
            return setNegativeButton(null,listener);
        }

        public Builder setNegativeButton(String text){
            return setNegativeButton(text,null);
        }


        public Builder setSingleButton(String text, View.OnClickListener listener){
            if (!TextUtils.isEmpty(text))
                mSingleText = text;
            if (listener != null) {
                mSingleClickLisener = listener;
            }
            return this;
        }

        public Builder setSingleButton(View.OnClickListener listener){
            return setSingleButton(null,listener);
        }

        public Builder setSingleButton(String text){
            return setSingleButton(text,null);
        }


        public Builder setCancelable(boolean cancelable){
            mCancelable = cancelable;
            return this;
        }

        public boolean isCancelable(){
            return mCancelable;
        }

        public String getPositiveText() {
            return mPositiveText;
        }

        public String getNegativeText() {
            return mNegativeText;
        }

        public String getSingleText() {
            return mSingleText;
        }

        public View.OnClickListener getPositiveClcikListener() {
            return mPositiveClickListener;
        }

        public View.OnClickListener getNegativeClickListener() {
            return mNegativeClickListener;
        }

        public View.OnClickListener getSingleClickLisener() {
            return mSingleClickLisener;
        }


        public AlertDialog build(){
            return new AlertDialog(this);
        }
    }



}
