package com.yuyuka.billiards.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.ui.activity.nearbymatch.NearbyMatchActivity;
import com.yuyuka.billiards.ui.activity.rearbyroom.NearbyRoomActivity;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.btn_room,R.id.btn_match})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_room:
                NearbyRoomActivity.launcher(this);
                break;
            case R.id.btn_match:
                NearbyMatchActivity.launcher(this);
                break;
        }
    }
}
