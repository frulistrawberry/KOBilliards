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

public class ZizhuActivity extends BaseActivity {
    @BindView(R.id.zhifu)
    StateButton zhifu;

    public static void launcher(Context context) {
        context.startActivity(new Intent(context, ZizhuActivity.class));
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("自助关灯").showBack().show();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.layout_zizhu);
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.zhifu)
    public void onViewClicked() {
        PayForActivity.launcher(this);
    }
}