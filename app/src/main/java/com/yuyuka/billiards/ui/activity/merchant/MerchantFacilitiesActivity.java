package com.yuyuka.billiards.ui.activity.merchant;

import android.content.Context;
import android.content.Intent;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;

public class MerchantFacilitiesActivity extends BaseActivity {

    public static void launcher(Context context){
        context.startActivity(new Intent(context, MerchantFacilitiesActivity.class));
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("球房设施").showBack().show();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_room_facilities);
    }

    @Override
    protected void initData() {

    }
}
