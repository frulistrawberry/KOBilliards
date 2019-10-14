package com.yuyuka.billiards.ui.activity.table;

import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.netease.nimlib.sdk.msg.model.CustomNotification;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.pojo.CustomNoticePojo;
import com.yuyuka.billiards.utils.BarUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class BattleLoserWaitActivity extends BaseActivity {
    @Override
    protected void initView() {
        setContentView(R.layout.activity_battle_loser_wait);
        super.mStatusBar.setVisibility(View.GONE);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            mStatusBar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, BarUtils.getStatusBarHeight(this)));
        }else {
            mStatusBar.setVisibility(View.GONE);
        }

        EventBus.getDefault().register(this);
    }

    @Override
    protected void initData() {

    }

    @Subscribe
    public void onEvent(CustomNotification notification){
        CustomNoticePojo noticePojo = new Gson().fromJson(notification.getContent(),CustomNoticePojo.class);
        if (noticePojo.getNoticeType() == 3){
            ConfirmPointActivity.launch(this,noticePojo);
        }
    }
}
