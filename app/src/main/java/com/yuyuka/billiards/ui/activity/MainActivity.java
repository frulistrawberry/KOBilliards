package com.yuyuka.billiards.ui.activity;

import android.view.View;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.ui.activity.match.NearbyMatchActivity;
import com.yuyuka.billiards.ui.activity.room.NearbyRoomActivity;

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
