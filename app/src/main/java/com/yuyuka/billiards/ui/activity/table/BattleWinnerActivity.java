package com.yuyuka.billiards.ui.activity.table;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.OnClick;

public class BattleWinnerActivity extends BaseMvpActivity<PointPresenter> implements PointContract.IPointView {

    @BindView(R.id.ll_point)
    LinearLayout pointLayout;

    int point;
    int id;
    int userId;

    public static void launcher(Activity context, int id, int userId){
        Intent intent = new Intent(context,BattleWinnerActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("userId",userId);
        context.startActivityForResult(intent,0);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_winner);
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
        id = getIntent().getIntExtra("id",0);
        userId = getIntent().getIntExtra("userId",0);
        EventBus.getDefault().register(this);
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

    @Subscribe
    public void onEvent(CustomNotification notification){
        CustomNoticePojo noticePojo = new Gson().fromJson(notification.getContent(),CustomNoticePojo.class);
        if (noticePojo.getNoticeType() == 4){
            // TODO: 2019-10-14 结算页
            BattleResultActivity.launcher(this,noticePojo,true);
            setResult(RESULT_OK);
            finish();
        }
    }


}
