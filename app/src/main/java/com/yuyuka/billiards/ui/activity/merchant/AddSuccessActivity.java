package com.yuyuka.billiards.ui.activity.merchant;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;

public class AddSuccessActivity extends BaseActivity {

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("添加商户").showBack().showDivider().show();
    }

    @Override
    protected void initView() {
        setTitleStyle(1);
        setContentView(R.layout.activity_add_success);
    }

    @Override
    protected void initData() {

    }
}
