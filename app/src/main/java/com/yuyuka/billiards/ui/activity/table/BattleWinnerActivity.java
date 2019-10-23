package com.yuyuka.billiards.ui.activity.table;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.netease.nimlib.sdk.msg.model.CustomNotification;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.base.BaseMvpActivity;
import com.yuyuka.billiards.mvp.contract.table.PointContract;
import com.yuyuka.billiards.mvp.presenter.table.PointPresenter;
import com.yuyuka.billiards.pojo.CustomNoticePojo;
import com.yuyuka.billiards.utils.BarUtils;
import com.yuyuka.billiards.utils.CommonUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.OnClick;

public class BattleWinnerActivity extends BaseMvpActivity<PointPresenter> implements PointContract.IPointView {

    @BindView(R.id.ll_point)
    LinearLayout pointLayout;

    int point;
    CustomNoticePojo data;
    int userId;
    int id;

    public static void launcher(Activity context,CustomNoticePojo data){
        Intent intent = new Intent(context,BattleWinnerActivity.class);
        intent.putExtra("data",data);
        context.startActivityForResult(intent,0);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_winner);
        super.mStatusBar.setVisibility(View.GONE);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            mStatusBar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, BarUtils.getStatusBarHeight(this)));
        }else {
            mStatusBar.setVisibility(View.GONE);
        }



        for (int i = 0; i < pointLayout.getChildCount(); i++) {
            RelativeLayout rl = (RelativeLayout) pointLayout.getChildAt(i);
            TextView textView = (TextView) rl.getChildAt(0);
            int finalI = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (int j = 0; j < pointLayout.getChildCount(); j++) {
                        RelativeLayout rl1 = (RelativeLayout) pointLayout.getChildAt(j);
                        TextView textView1 = (TextView) rl1.getChildAt(0);
                        textView1.setTextColor(getResourceColor(R.color.text_color_1));
                        textView1.setBackground(getResourceDrawable(R.drawable.bg_point_un_select));
                    }
                    view.setBackground(getResourceDrawable(R.drawable.bg_point_select));
                    ((TextView)view).setTextColor(getResourceColor(R.color.white));
                    point = Integer.valueOf(textView.getText().toString());
                }
            });
        }
    }

    @Override
    protected void initData() {
        data = (CustomNoticePojo) getIntent().getSerializableExtra("data");
        CustomNoticePojo.Battle battle = data.getBizContent().getBattle();
        if (battle.getUserId1() == CommonUtils.getUserId()){
            userId = battle.getUserId2();
        }else {
            userId = battle.getUserId1();
        }
        id = battle.getId();
    }

    @OnClick({R.id.btn_send})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_send:
                getPresenter().sendPoint(id,userId,point);
                break;
        }
    }

    @Override
    protected PointPresenter getPresenter() {
        return new PointPresenter(this);
    }

    @Override
    public void onEvent(CustomNotification notification) {
        super.onEvent(notification);
        CustomNoticePojo data = new Gson().fromJson(notification.getContent(),CustomNoticePojo.class);
        if (data.getNoticeType()== 4){
            setResult(RESULT_OK);
            finish();
        }else if (data.getNoticeType() == 2){
            finish();
        }
    }
}
