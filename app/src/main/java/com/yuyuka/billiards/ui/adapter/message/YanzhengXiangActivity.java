package com.yuyuka.billiards.ui.adapter.message;

import android.content.Context;
import android.content.Intent;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;


public class YanzhengXiangActivity extends BaseActivity {
    public static void launcher(Context context) {
        context.startActivity(new Intent(context, YanzhengXiangActivity.class));
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().showBack().show();
    }

    @Override
    protected void initView() {
       setContentView(R.layout.layout_yanzhengxiangactivity);
    }

    @Override
    protected void initData() {

    }
}
