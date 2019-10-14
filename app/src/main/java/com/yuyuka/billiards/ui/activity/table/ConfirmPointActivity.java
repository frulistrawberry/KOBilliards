package com.yuyuka.billiards.ui.activity.table;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.netease.nimlib.sdk.msg.model.CustomNotification;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.base.BaseMvpActivity;
import com.yuyuka.billiards.image.ImageManager;
import com.yuyuka.billiards.mvp.contract.table.PointContract;
import com.yuyuka.billiards.mvp.presenter.table.PointPresenter;
import com.yuyuka.billiards.pojo.CustomNoticePojo;
import com.yuyuka.billiards.utils.CommonUtils;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

public class ConfirmPointActivity extends BaseMvpActivity<PointPresenter> implements PointContract.IPointView {

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

    public static void launch(Context context,CustomNoticePojo data){
        Intent intent = new Intent(context,ConfirmPointActivity.class);
        intent.putExtra("data",data);
        context.startActivity(intent);
    }

    @Override
    protected PointPresenter getPresenter() {
        return new PointPresenter(this);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_confirm_point);
        ImageManager.getInstance().loadNet(data.getBizContent().getUser1().getHeadImage(),userHead1);
        ImageManager.getInstance().loadNet(data.getBizContent().getUser2().getHeadImage(),userHead2);
        userName1.setText(data.getBizContent().getUser1().getUserName());
        userName2.setText(data.getBizContent().getUser2().getUserName());
        pointTv.setText(data.getBizContent().getBattle().getUser1Point()+":"+data.getBizContent().getBattle().getUser2Point());

    }

    @Override
    protected void initData() {
        data = (CustomNoticePojo) getIntent().getSerializableExtra("data");
    }

    @OnClick({R.id.btn_send})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_send:
                getPresenter().confirmPoint(data.getBizContent().getBattle().getId());
                break;
        }
    }

    @Subscribe
    public void onEvent(CustomNotification notification){
        CustomNoticePojo noticePojo = new Gson().fromJson(notification.getContent(),CustomNoticePojo.class);
        if (noticePojo.getNoticeType() == 4){
            // TODO: 2019-10-14 结算页
        }
    }


}
