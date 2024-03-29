package com.yuyuka.billiards.ui.activity.facetoface;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.netease.nimlib.sdk.msg.model.CustomNotification;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.base.BaseMvpActivity;
import com.yuyuka.billiards.constants.CompetitionType;
import com.yuyuka.billiards.mvp.contract.table.TableContract;
import com.yuyuka.billiards.mvp.presenter.table.TablePresenter;
import com.yuyuka.billiards.pojo.CustomNoticePojo;
import com.yuyuka.billiards.pojo.OrderPojo;
import com.yuyuka.billiards.pojo.TablePojo;
import com.yuyuka.billiards.ui.activity.table.BattleActivity;
import com.yuyuka.billiards.ui.activity.table.SingleBattleActivity;
import com.yuyuka.billiards.utils.BarUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

public class FaceToFaceActivity extends BaseMvpActivity<TablePresenter> implements TableContract.ITableView {
    @BindView(R.id.v_status)
    View mStatusBar;
    private boolean isOnResume;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_face_to_face);
        getStatusbar().setVisibility(View.GONE);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            mStatusBar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, BarUtils.getStatusBarHeight(this)));
        }else {
            mStatusBar.setVisibility(View.GONE);
        }


    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.iv_paiwei,R.id.iv_yule,R.id.btn_back})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.iv_paiwei:
                getPresenter().openTable(0,2, CompetitionType.FACE_TO_FACE_RANK);
                break;
            case R.id.iv_yule:
                getPresenter().openTable(0,2, CompetitionType.FACE_TO_FACE_BATTLE);
                break;
            case R.id.btn_back:
                finish();
                break;

        }
    }


    @Override
    protected TablePresenter getPresenter() {
        return new TablePresenter(this);
    }

    @Override
    public void showTableInfo(TablePojo data) {

    }

    @Override
    public void showOrderSuccess(OrderPojo data) {

    }

    @Override
    public void showOrderFailure(String msg) {

    }

    @Override
    public void showEnterSuccess() {

    }

    @Override
    public void showEnterFailure() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        isOnResume = true;

    }

    @Override
    protected void onPause() {
        super.onPause();
        isOnResume = false;
    }



}
