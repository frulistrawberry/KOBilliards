package com.yuyuka.billiards.ui.activity.room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.widget.StateButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayForActivity extends BaseActivity {

    @BindView(R.id.tijiao)
    StateButton tijiao;

    public static void launcher(Context context) {
        context.startActivity(new Intent(context, PayForActivity.class));
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("支付订单").showBack().show();
    }

    @Override
        protected void initView() {
        setContentView(R.layout.layout_payforactivity);
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.tijiao)
    public void onViewClicked() {
        PaySuesscesActivity.launcher(this);
    }
}
