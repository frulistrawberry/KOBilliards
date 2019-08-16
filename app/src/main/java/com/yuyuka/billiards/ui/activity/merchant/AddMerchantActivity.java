package com.yuyuka.billiards.ui.activity.merchant;

import android.content.Intent;
import android.view.View;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;

import butterknife.OnClick;

public class AddMerchantActivity extends BaseActivity {

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("添加商户").showBack().showDivider().show();
    }

    @Override
    protected void initView() {
        setTitleStyle(1);
        setContentView(R.layout.activity_add_merchant);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.ll_add_business_date,R.id.btn_commit})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.ll_add_business_date:
                startActivity(new Intent(this, AddBusinessDateActivity.class));
                break;
            case R.id.btn_commit:
                startActivity(new Intent(this,AddSuccessActivity.class));
                break;
        }
    }
}
