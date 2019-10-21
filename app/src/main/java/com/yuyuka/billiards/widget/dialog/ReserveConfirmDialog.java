package com.yuyuka.billiards.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.ui.activity.common.PayActivity;
import com.yuyuka.billiards.utils.ScreenUtils;
import com.yuyuka.billiards.utils.log.LogUtil;

import org.w3c.dom.Text;

public class ReserveConfirmDialog extends Dialog {
    TextView dateTv;
    TextView durationTv;
    int id;String name;String money;String beginDate;String endDate;String remark;int setmId;
    public ReserveConfirmDialog( Context context) {
        this(context, R.style.DialogTheme);
    }

    public ReserveConfirmDialog(Context context, int themeResId) {
        super(context, themeResId);

        View contentView = LayoutInflater.from(context).inflate(R.layout.dialog_reserve_confirm, null);
        TextView cancelBtn = contentView.findViewById(R.id.btn_cancel);
        TextView confirmBtn = contentView.findViewById(R.id.btn_pay);
        cancelBtn.setOnClickListener(v -> dismiss());
        dateTv = contentView.findViewById(R.id.tv_date);
        durationTv = contentView.findViewById(R.id.tv_duration);

        confirmBtn.setOnClickListener(v -> {
            LogUtil.e("data","id="+id);
            LogUtil.e("data","money="+money);
            LogUtil.e("data","beginDate="+beginDate);
            LogUtil.e("data","endDate="+endDate);
            LogUtil.e("data","remark="+remark);
            PayActivity.launcher((Activity) context,id,name,money,beginDate,endDate,remark,setmId);
        });

        setContentView(contentView);

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
        layoutParams.width = (int) (ScreenUtils.getScreenWidth(getContext()) * 0.75);
        layoutParams.gravity = Gravity.CENTER;
    }

    public void setData(String duration,String date,int id,String name,String money,String beginDate,String endDate,String remark,int setmId){
        durationTv.setText(duration);
        dateTv.setText(date);
        this.id = id;
        this.name = name;
        this.money = money;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.remark = remark;
        this.setmId = setmId;
    }


}
