package com.yuyuka.billiards.ui.activity.room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BallRoomShopActivity extends BaseActivity {
    @BindView(R.id.jiushui)
    LinearLayout jiushui;
    @BindView(R.id.fabu)
    LinearLayout fabu;

    public static void launcher(Context context) {
        context.startActivity(new Intent(context, BallRoomShopActivity.class));
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("球房商铺").showBack().show();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.layout_ballroomshop);
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.jiushui, R.id.fabu,R.id.zizhu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.jiushui:
                RoomStoreActivity.launcher(this);
                break;
            case R.id.fabu:
                YuejuRoomActivity.launcher(this);
                break;
            case R.id.zizhu:
                ZizhuActivity.launcher(this);
                break;
        }
    }
}
