package com.yuyuka.billiards.ui.activity.table;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
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

    public static void launcher(Activity context, int userId, int id){
        Intent intent = new Intent(context,BattleEndActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("userId",userId);
        context.startActivityForResult(intent,0);
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
                startActivityForResult(new Intent(this,BattleLoserWaitActivity.class),0);
                break;
        }
    }

    @Subscribe
    public void onEvent(CustomNotification notification){

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            setResult(RESULT_OK);
            finish();
        }
    }
}
