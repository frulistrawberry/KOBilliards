package com.yuyuka.billiards.ui.activity.rearbyroom;

import android.content.Context;
import android.content.Intent;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;

public class PayActivity extends BaseActivity {

    public static void launcher(Context context){
        context.startActivity(new Intent(context, PayActivity.class));
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("支付订单").showBack().show();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_pay);
    }

    @Override
    protected void initData() {

    }
}
