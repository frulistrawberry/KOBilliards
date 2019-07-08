package com.kobilliards.ui.activity;

import android.content.Context;
import android.content.Intent;

import com.kobilliards.R;
import com.kobilliards.base.BaseActivity;

public class BilliardsRoomDetailActivity extends BaseActivity {

    public static void launcher(Context context){
        Intent intent = new Intent(context,BilliardsRoomDetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("球房详情").showBack().show();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_billiards_detail);
    }

    @Override
    protected void initData() {

    }
}
