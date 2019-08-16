package com.yuyuka.billiards.ui.activity.merchant;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;

public class AddBusinessDateActivity extends BaseActivity {

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("营业时间").showBack().showDivider().show();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_add_business_date);
    }

    @Override
    protected void initData() {

    }
}
