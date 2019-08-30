package com.yuyuka.billiards.ui.activity.market;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;

public class ReleaseGoodsActivity extends BaseActivity {

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("发布").showBack().show();
    }

    @Override
    protected void initView() {
        setTitleStyle(1);
        setContentView(R.layout.activity_release_goods);
    }

    @Override
    protected void initData() {

    }
}
