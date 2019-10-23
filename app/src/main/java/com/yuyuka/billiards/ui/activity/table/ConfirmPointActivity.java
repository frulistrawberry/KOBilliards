package com.yuyuka.billiards.ui.activity.table;

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
import com.yuyuka.billiards.base.BaseMvpActivity;
import com.yuyuka.billiards.image.ImageManager;
import com.yuyuka.billiards.mvp.contract.table.PointContract;
import com.yuyuka.billiards.mvp.contract.table.TableContract;
import com.yuyuka.billiards.mvp.presenter.table.PointPresenter;
import com.yuyuka.billiards.mvp.presenter.table.TablePresenter;
import com.yuyuka.billiards.pojo.CustomNoticePojo;
import com.yuyuka.billiards.pojo.OrderPojo;
import com.yuyuka.billiards.pojo.TablePojo;
import com.yuyuka.billiards.utils.BarUtils;
import com.yuyuka.billiards.utils.CommonUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

public class ConfirmPointActivity extends BaseMvpActivity<PointPresenter> implements PointContract.IPointView, TableContract.ITableView {

    CustomNoticePojo data;
    @BindView(R.id.tv_point)
    TextView pointTv;
    @BindView(R.id.iv_head_user1)
    ImageView userHead1;
    @BindView(R.id.iv_head_user2)
    ImageView userHead2;
    @BindView(R.id.tv_user_name1)
    TextView userName1;
    @BindView(R.id.tv_user_name2)
    TextView userName2;

    TablePresenter tablePresenter;

    public static void launch(Activity context, CustomNoticePojo data){
        Intent intent = new Intent(context,ConfirmPointActivity.class);
        intent.putExtra("data",data);
        context.startActivityForResult(intent,0);
    }

    @Override
    protected PointPresenter getPresenter() {
        return new PointPresenter(this);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_confirm_point);
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
        pointTv.setText(data.getBizContent().getBattle().getUser1Point()+":"+data.getBizContent().getBattle().getUser2Point());

    }

    @Override
    protected void initData() {
        data = (CustomNoticePojo) getIntent().getSerializableExtra("data");
        tablePresenter = new TablePresenter(this);
    }


    @OnClick({R.id.btn_send,R.id.btn_cancel})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_send:
                getPresenter().confirmPoint(data.getBizContent().getBattle().getId());
                break;
            case R.id.btn_cancel:
                tablePresenter.cancelOrder(data.getBizContent().getBattle().getId());
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            setResult(RESULT_OK);
            finish();
        }

    }

    @Override
    public void onEvent(CustomNotification notification) {
        super.onEvent(notification);
        CustomNoticePojo data = new Gson().fromJson(notification.getContent(),CustomNoticePojo.class);
        if (data.getNoticeType() == 2){
            finish();
        }
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
}
