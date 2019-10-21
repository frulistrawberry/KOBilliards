package com.yuyuka.billiards.ui.activity.match;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.ui.activity.common.PayActivity;

import butterknife.OnClick;

public class MatchOrderConfirmActivity extends BaseActivity {

    public static void launch(Context context){
        Intent intent = new Intent(context,MatchOrderConfirmActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("报名").showBack().show();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_match_order_confirm);
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.btn_commit)
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_commit:
//                PayActivity.launcher(this);
                break;
        }
    }
}
