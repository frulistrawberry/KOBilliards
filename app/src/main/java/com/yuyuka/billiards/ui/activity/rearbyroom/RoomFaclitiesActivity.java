package com.yuyuka.billiards.ui.activity.rearbyroom;

import android.content.Context;
import android.content.Intent;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;

public class RoomFaclitiesActivity extends BaseActivity {

    public static void launcher(Context context){
        context.startActivity(new Intent(context,RoomFaclitiesActivity.class));
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
