package com.yuyuka.billiards.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.yuyuka.billiards.R;

public class QunDialog  {
    private static Context mContext;
    private TextView mTitleTv;
    private TextView  qunneirong;
    private TextView qunxiaoxi;
    private TextView quxiao;
    private Dialog mDialog;
    private View mDialogView;
    private QunDialog.Builder mBuilder;
    public QunDialog(QunDialog.Builder builder){
        mBuilder = builder;
        mDialog = new Dialog(mContext, R.style.CalculatorDialog);
        mDialogView = View.inflate(mContext,R.layout.layout_qundialog,null);
        mTitleTv = mDialogView.findViewById(R.id.qun_title);
        qunneirong = mDialogView.findViewById(R.id.qun_neirong);
        qunxiaoxi=mDialogView.findViewById(R.id.qun_xiaoxi);
        quxiao = mDialogView.findViewById(R.id.qun_quxiao);
        mDialog.setContentView(mDialogView);
        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height=WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN|WindowManager.LayoutParams.SOFT_INPUT_ADJUST_UNSPECIFIED);
        getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        initDialog();
    }

    private void initDialog() {
        mTitleTv.setText(mBuilder.getTitle());
        qunneirong.setText(mBuilder.getMessage());
        qunxiaoxi.setText(mBuilder.getmPositiveText());
        quxiao.setText(mBuilder.getmNegativeText());
    }

    public Window getWindow(){
        return mDialog.getWindow();
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
    public static class Builder{
        private String mTitle;
        private String mMessage;
        private String mPositiveText;
        private String clanText;

        public Builder(Context context){
            mContext = context;
        }

        public com.yuyuka.billiards.utils.dialog.QunDialog.Builder setTitle(String title){
            mTitle = title;
            return this;
        }
        public String getTitle(){
            return mTitle;
        }
        public com.yuyuka.billiards.utils.dialog.QunDialog.Builder setMessage(String message){
            mMessage = message;
            return this;
        }

        public String getMessage(){
            return mMessage;
        }
        public com.yuyuka.billiards.utils.dialog.QunDialog.Builder setmPositiveText(String PositiveText){
            mPositiveText = PositiveText;
            return this;
        }

        public String getmPositiveText(){
            return mPositiveText;
        }


        public com.yuyuka.billiards.utils.dialog.QunDialog.Builder setcanText(String mNegativeText){
            clanText = mNegativeText;
            return this;
        }

        public String getmNegativeText(){
            return clanText;
        }

        public QunDialog build(){
            return new QunDialog(this);
        }
    }
}
