package com.yuyuka.billiards.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.yuyuka.billiards.R;
public class IDCardDialog {
    private Dialog mDialog;
    private View mDialogView;
    private TextView yunxu;
    private TextView yanzheng;
    private TextView jujue;
    private TextView quxiao;
    public IDCardDialog( Context mContext){
        mDialog = new Dialog(mContext, R.style.CalculatorDialog);
        mDialogView = View.inflate(mContext,R.layout.layout_idcarddialog,null);
        yunxu=mDialogView.findViewById(R.id.yunxu);
        yanzheng=mDialog.findViewById(R.id.yanzheng);
        jujue=mDialog.findViewById(R.id.jujue);
        quxiao=mDialog.findViewById(R.id.quxiao);
        mDialog.setContentView(mDialogView);
        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height=WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN|WindowManager.LayoutParams.SOFT_INPUT_ADJUST_UNSPECIFIED);
        getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
    }



    public void dismiss(){
        if (mDialog != null&&mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    public Window getWindow(){
        return mDialog.getWindow();
    }

    public void show(){
        if (mDialog != null) {
            mDialog.show();
        }
    }

}
