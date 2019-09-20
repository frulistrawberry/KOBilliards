package com.yuyuka.billiards.ui.activity.message;

import android.content.Context;
import android.content.Intent;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;

public class DisturbingActivity extends BaseActivity {
    public static void launcher(Context context) {
        context.startActivity(new Intent(context, DisturbingActivity.class));
    }
    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("免打扰").showBack().show();
    }

    @Override
    protected void initView() {
       setContentView(R.layout.layout_disturbingactivity);
    }

    @Override
    protected void initData() {

    }
}
