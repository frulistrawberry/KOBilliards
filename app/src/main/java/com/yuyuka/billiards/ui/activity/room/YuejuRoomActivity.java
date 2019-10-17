package com.yuyuka.billiards.ui.activity.room;

import android.content.Context;
import android.content.Intent;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;

public class YuejuRoomActivity extends BaseActivity {
    public static void launcher(Context context) {
        context.startActivity(new Intent(context, YuejuRoomActivity.class));
    }
    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("本球房约局").showBack().show();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.layout_yueju_activity);
    }

    @Override
    protected void initData() {

    }
}
