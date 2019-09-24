package com.yuyuka.billiards.ui.adapter.message;

import android.content.Context;
import android.content.Intent;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;

public class TianjiaActivity extends BaseActivity {
    public static void launcher(Context context) {
        context.startActivity(new Intent(context, TianjiaActivity.class));
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("添加好友").showBack().show();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.layout_tianjiaactivity);
    }

    @Override
    protected void initData() {

    }
}
