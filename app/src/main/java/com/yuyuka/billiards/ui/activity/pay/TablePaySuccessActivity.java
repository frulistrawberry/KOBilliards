package com.yuyuka.billiards.ui.activity.pay;

import android.content.Context;
import android.content.Intent;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.constants.CompetitionType;
import com.yuyuka.billiards.pojo.CustomNoticePojo;
import com.yuyuka.billiards.ui.activity.facetoface.FaceToFaceFunActivity;
import com.yuyuka.billiards.ui.activity.facetoface.FaceToFaceQualifyingActivity;
import com.yuyuka.billiards.ui.activity.table.BattleActivity;

import org.greenrobot.eventbus.EventBus;

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

    @Override
    public void finish() {
        if (data.getNoticeType() == 0){
            //等待比赛(调到二维码页面)
            CustomNoticePojo.Battle battle = data.getBizContent().getBattle();
            switch (battle.getBattleType()){
                case CompetitionType.SCAN_BATTLE:
                    break;
                case CompetitionType.SCAN_RANK:
                    FaceToFaceQualifyingActivity.launcher(this,tableId,battle.getId(),battle.getRefOrderId(),battle.getBattleType());
                    break;
            }
        }else if (data.getNoticeType() == 1){
            //比赛开始进入对战页面
            CustomNoticePojo.Battle battle = data.getBizContent().getBattle();
            switch (battle.getBattleType()){
                case CompetitionType.SCAN_BATTLE:
                    BattleActivity.launcher(this,data);
                    break;
                case CompetitionType.SCAN_RANK:
                    BattleActivity.launcher(this,data);
                    break;
            }
        }

        super.finish();

    }
}
