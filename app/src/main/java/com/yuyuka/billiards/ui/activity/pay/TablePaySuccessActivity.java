package com.yuyuka.billiards.ui.activity.pay;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.constants.CompetitionType;
import com.yuyuka.billiards.pojo.CustomNoticePojo;
import com.yuyuka.billiards.ui.activity.facetoface.FaceToFaceFunActivity;
import com.yuyuka.billiards.ui.activity.facetoface.FaceToFaceQualifyingActivity;
import com.yuyuka.billiards.ui.activity.table.BattleActivity;
import com.yuyuka.billiards.ui.activity.table.SingleBattleActivity;

import org.greenrobot.eventbus.EventBus;

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
    public void finish() {
        if (data == null){
            super.finish();
            return;
        }

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
                    break;
                case CompetitionType.SCAN_RANK:
                    BattleActivity.launcher(this,data);
                    break;
                    case CompetitionType.OPEN_TABLE:
                        SingleBattleActivity.launcher(this,data);
                        break;
            }
        }

        super.finish();

    }
}
