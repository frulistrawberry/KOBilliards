package com.yuyuka.billiards.ui.activity.room;

import android.content.Context;
import android.content.Intent;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;

public class RoomSelf extends BaseActivity {
    public static void launcher(Context context) {
        context.startActivity(new Intent(context, RoomSelf.class));
    }
    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("球房自办比赛").showBack().show();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.layout_roomself);
    }

    @Override
    protected void initData() {

    }
}
