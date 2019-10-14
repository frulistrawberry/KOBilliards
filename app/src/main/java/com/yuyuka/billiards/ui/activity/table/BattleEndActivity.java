package com.yuyuka.billiards.ui.activity.table;

import android.content.Context;
import android.content.Intent;
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

import butterknife.OnClick;

public class BattleEndActivity extends BaseActivity {

    int id;
    int otherUserId;

    public static void launcher(Context context,int userId,int id){
        Intent intent = new Intent(context,BattleEndActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("userId",userId);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_battle_end);
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
        id = getIntent().getIntExtra("id",0);
        otherUserId = getIntent().getIntExtra("userId",0);
    }

    @OnClick({R.id.btn_win,R.id.btn_lose})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_win:
                BattleWinnerActivity.launcher(this,id,otherUserId);
                break;
            case R.id.btn_lose:
                startActivity(new Intent(this,BattleLoserWaitActivity.class));
                break;
        }
    }

    @Subscribe
    public void onEvent(CustomNotification notification){
        CustomNoticePojo noticePojo = new Gson().fromJson(notification.getContent(),CustomNoticePojo.class);
        if (noticePojo.getNoticeType() == 4){
            // TODO: 2019-10-14 败者结算页
        }
    }
}
