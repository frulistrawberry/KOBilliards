package com.yuyuka.billiards.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.yuyuka.billiards.R;



public class FilterDialog extends Dialog {


    public FilterDialog(@NonNull Context context) {
        super(context, R.style.DialogTheme);
        View contentView = LayoutInflater.from(context).inflate(R.layout.pop_goods_filter,null);

        setContentView(contentView);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width= (int) ((getWindow().getWindowManager().getDefaultDisplay().getWidth()) * 0.9);
        params.height= WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setGravity(Gravity.RIGHT);
        getWindow().setWindowAnimations(R.style.RightAnimation);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }



}
