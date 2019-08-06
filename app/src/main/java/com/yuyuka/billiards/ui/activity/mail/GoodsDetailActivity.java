package com.yuyuka.billiards.ui.activity.mail;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;

public class GoodsDetailActivity extends BaseActivity {

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("详情").showBack().show();
    }

    @Override
    protected void initView() {
        setTitleStyle(1);
        setContentView(R.layout.activity_goods_detail);
    }

    @Override
    protected void initData() {

    }
}
