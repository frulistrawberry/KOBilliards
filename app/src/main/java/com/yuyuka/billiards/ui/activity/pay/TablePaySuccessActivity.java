package com.yuyuka.billiards.ui.activity.pay;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.netease.nimlib.sdk.msg.model.CustomNotification;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.constants.CompetitionType;
import com.yuyuka.billiards.pojo.CustomNoticePojo;
import com.yuyuka.billiards.ui.activity.facetoface.BattleWaitActivity;
import com.yuyuka.billiards.ui.activity.table.BattleActivity;
import com.yuyuka.billiards.ui.activity.table.SingleBattleActivity;

import org.greenrobot.eventbus.Subscribe;

import butterknife.OnClick;

public class TablePaySuccessActivity extends BaseActivity {

    private CustomNoticePojo data;
    private long tableId;

    public static void launcher(Context context, CustomNoticePojo data,long tableId){
        Intent intent = new Intent(context,TablePaySuccessActivity.class);
        intent.putExtra("data",data);
        intent.putExtra("tableId",tableId);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        super.initTitle();
       getTitleBar().showBack().showDivider().setTitle("支付订单").show();
    }

    @Override
    protected void initView() {
        setTitleStyle(1);
        setContentView(R.layout.activity_table_pay_success);
    }

    @Override
    protected void initData() {
        this.data = (CustomNoticePojo) getIntent().getSerializableExtra("data");
        tableId = getIntent().getLongExtra("tableId",0);
    }

    @OnClick({R.id.btn_back})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_back:
                finish();
                break;
        }
    }

    @Override
    public void onEvent(CustomNotification notification) {
//        super.onEvent(notification);
    }

    @Override
    public void finish() {
        if (data == null){
            setResult(RESULT_OK);
            super.finish();
            return;
        }

        if (data.getNoticeType() == 0){
            //等待比赛(调到二维码页面)
            CustomNoticePojo.Battle battle = data.getBizContent().getBattle();
            CustomNoticePojo.BizContent bizContent = data.getBizContent();
            if (battle.getBattleType() == CompetitionType.SCAN_BATTLE
                    ||battle.getBattleType() == CompetitionType.SCAN_RANK
                    ||battle.getBattleType() == CompetitionType.FACE_TO_FACE_BATTLE
                    ||battle.getBattleType() == CompetitionType.FACE_TO_FACE_RANK){
                long tableNum = bizContent.getTableNum();
                BattleWaitActivity.launcher(this, tableNum,battle.getId(),battle.getRefOrderId(),battle.getBattleType());
            }
        }else if (data.getNoticeType() == 1){
            //比赛开始进入对战页面
            //比赛开始进入对战页面
            CustomNoticePojo.Battle battle = data.getBizContent().getBattle();
            if (battle.getBattleType() == CompetitionType.SCAN_BATTLE
                    ||battle.getBattleType() == CompetitionType.SCAN_RANK
                    ||battle.getBattleType() == CompetitionType.FACE_TO_FACE_BATTLE
                    ||battle.getBattleType() == CompetitionType.FACE_TO_FACE_RANK) {
                BattleActivity.launcher(this,data);

            }else if (battle.getBattleType() == CompetitionType.OPEN_TABLE){
                SingleBattleActivity.launcher(this,data);
            }
        }

        setResult(RESULT_OK);
        super.finish();



    }
}
