package com.yuyuka.billiards.ui.activity.table;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.netease.nimlib.sdk.msg.model.CustomNotification;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.constants.CompetitionType;
import com.yuyuka.billiards.image.ImageManager;
import com.yuyuka.billiards.pojo.CustomNoticePojo;
import com.yuyuka.billiards.ui.activity.pay.TablePayActivity;
import com.yuyuka.billiards.utils.BarUtils;
import com.yuyuka.billiards.utils.CommonUtils;
import com.yuyuka.billiards.utils.DataOptionUtils;
import com.yuyuka.billiards.utils.log.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

public class BattleActivity extends BaseActivity {

    CustomNoticePojo data;
    @BindView(R.id.iv_head_user1)
    ImageView userHead1;
    @BindView(R.id.iv_head_user2)
    ImageView userHead2;
    @BindView(R.id.tv_user_name1)
    TextView userName1;
    @BindView(R.id.tv_user_name2)
    TextView userName2;
    @BindView(R.id.duanwei1)
    TextView duanwei1;
    @BindView(R.id.duanwei2)
    TextView duanwei2;

    @BindView(R.id.ll_paiwei)
    LinearLayout paweill;
    @BindView(R.id.ll_yule)
    LinearLayout yulell;



    public static void launcher(Activity context, CustomNoticePojo data){
        Intent intent = new Intent(context,BattleActivity.class);
        intent.putExtra("data",data);
        context.startActivityForResult(intent,0);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initView() {
        setContentView(R.layout.activity_battle);
        super.mStatusBar.setVisibility(View.GONE);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            mStatusBar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, BarUtils.getStatusBarHeight(this)));
        }else {
            mStatusBar.setVisibility(View.GONE);
        }

        ImageManager.getInstance().loadNet(data.getBizContent().getUser1().getHeadImage(),userHead1);
        ImageManager.getInstance().loadNet(data.getBizContent().getUser2().getHeadImage(),userHead2);
        userName1.setText(data.getBizContent().getUser1().getUserName());
        userName2.setText(data.getBizContent().getUser2().getUserName());
        int rank1 = data.getBizContent().getUser1().getRankingConfigurtion().getUserAt().getRank();
        duanwei1.setText(data.getBizContent().getUser1().getRankingConfigurtion().getName()+rank1+"段");
        int rank2 = data.getBizContent().getUser2().getRankingConfigurtion().getUserAt().getRank();
        duanwei2.setText(data.getBizContent().getUser2().getRankingConfigurtion().getName()+rank2+"段");

        CustomNoticePojo.Battle battle = data.getBizContent().getBattle();
        if (battle.getBattleType() == CompetitionType.FACE_TO_FACE_RANK ||battle.getBattleType() == CompetitionType.SCAN_RANK){
            paweill.setVisibility(View.VISIBLE);
            yulell.setVisibility(View.GONE);
        }else {
            paweill.setVisibility(View.GONE);
            yulell.setVisibility(View.VISIBLE);
        }

    }

    @OnClick(R.id.end_battle)
    public void onClick(View v){
        switch (v.getId()){
            case R.id.end_battle:
                BattleEndActivity.launcher(this,data);
                break;
        }
    }

    @Override
    protected void initData() {
        data = (CustomNoticePojo) getIntent().getSerializableExtra("data");
    }

    @Subscribe
    public void onEvent(CustomNotification message){
        super.onEvent(message);
        if (data.getNoticeType() == 6)
            finish();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            finish();
        }
    }
}
