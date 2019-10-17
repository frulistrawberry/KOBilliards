package com.yuyuka.billiards.ui;

import android.content.Intent;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.ui.activity.MainActivity;
import com.yuyuka.billiards.ui.activity.login.LoginActivity;
import com.yuyuka.billiards.utils.CommonUtils;

public class SplashActivity extends BaseActivity {
    @Override
    protected void initView() {
        setContentView(R.layout.activity_splash);
        if (CommonUtils.isLogin()){
            startActivity(new Intent(this, MainActivity.class));
        }else {
            startActivity(new Intent(this, LoginActivity.class));
        }
        finish();
    }

    @Override
    protected void initData() {

    }
}
