package com.yuyuka.billiards.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.ui.activity.rearbyroom.PayActivity;
import com.yuyuka.billiards.utils.ScreenUtils;

public class ReserveConfirmDialog extends Dialog {
    public ReserveConfirmDialog( Context context) {
        this(context, R.style.DialogTheme);
    }

    public ReserveConfirmDialog(Context context, int themeResId) {
        super(context, themeResId);

        View contentView = LayoutInflater.from(context).inflate(R.layout.dialog_reserve_confirm, null);
        TextView cancelBtn = contentView.findViewById(R.id.btn_cancel);
        TextView confirmBtn = contentView.findViewById(R.id.btn_pay);
        cancelBtn.setOnClickListener(v -> dismiss());

        confirmBtn.setOnClickListener(v -> {
            PayActivity.launcher(context);
        });

        setContentView(contentView);

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
        layoutParams.width = (int) (ScreenUtils.getScreenWidth(getContext()) * 0.75);
        layoutParams.gravity = Gravity.CENTER;
    }


}
