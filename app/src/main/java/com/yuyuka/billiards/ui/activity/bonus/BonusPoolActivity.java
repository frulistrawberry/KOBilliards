package com.yuyuka.billiards.ui.activity.bonus;

import android.content.Intent;
import android.view.View;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;

import butterknife.OnClick;

public class BonusPoolActivity extends BaseActivity {

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("奖金池").showBack().showDivider().show();
    }

    @Override
    protected void initView() {
        setTitleStyle(2);
        setContentView(R.layout.activity_bonus_pool);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.btn_bonus_rewards})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_bonus_rewards:
                startActivity(new Intent(this,RewardBonusActivity.class));
                break;
        }
    }
}
