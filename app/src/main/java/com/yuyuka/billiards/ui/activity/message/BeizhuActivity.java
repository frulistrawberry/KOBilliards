package com.yuyuka.billiards.ui.activity.message;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;

public class BeizhuActivity extends BaseActivity {
    public static void launcher(Context context) {
        context.startActivity(new Intent(context, BeizhuActivity.class));
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("备注名").setLeftText("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).setRightText("完成", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).show();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.layout_beizhuactivity);
    }

    @Override
    protected void initData() {

    }
}
