package com.yuyuka.billiards.ui.activity.room;

import android.content.Context;
import android.content.Intent;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;

public class PaySuesscesActivity extends BaseActivity {
    public static void launcher(Context context) {
        context.startActivity(new Intent(context, PaySuesscesActivity.class));
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("支付订单").showBack().show();
    }

    @Override
    protected void initView() {
       setContentView(R.layout.layout_paysuesscesactivity);
    }

    @Override
    protected void initData() {

    }
}
