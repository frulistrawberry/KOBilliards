package com.yuyuka.billiards.ui.activity.message;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.pojo.GroupXiangitenBean;
import com.yuyuka.billiards.ui.adapter.message.GroupXiangItemAdapter;
import com.yuyuka.billiards.ui.adapter.message.QunchengyuanActivity;
import com.yuyuka.billiards.utils.dialog.IDCardDialog;
import com.yuyuka.billiards.utils.dialog.QunDialog;
import com.yuyuka.billiards.widget.StateButton;
import com.yuyuka.billiards.widget.dialog.AlertDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class GroupXiangActivity extends BaseActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.qun_name)
    LinearLayout qunName;
    @BindView(R.id.qun_nicheng)
    LinearLayout qunNicheng;
    @BindView(R.id.qun_jieshao)
    LinearLayout qunJieshao;
    @BindView(R.id.switch_checkbox_message1)
    CheckBox switchCheckboxMessage1;
    @BindView(R.id.btn_guide)
    StateButton btnGuide;
    @BindView(R.id.yaoqing_qunxian)
    LinearLayout yaoqingQunxian;
    @BindView(R.id.xiugai)
    LinearLayout xiugai;
    @BindView(R.id.shenfen_yanzheng)
    LinearLayout shenfenYanzheng;
    private GroupXiangItemAdapter xiangItemAdapter;
    private List<GroupXiangitenBean> groupXiangitenBeans;

    public static void launcher(Context context) {
        context.startActivity(new Intent(context, GroupXiangActivity.class));
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().showBack().show();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.layout_groupxiangactivity);
        for (int i = 0; i < 6; i++) {
            GroupXiangitenBean groupXiangitenBean = new GroupXiangitenBean();
            groupXiangitenBeans.add(groupXiangitenBean);
        }
        xiangItemAdapter.setNewData(groupXiangitenBeans);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 6));
        recyclerView.setAdapter(xiangItemAdapter);
    }

    @Override
    protected void initData() {
        xiangItemAdapter = new GroupXiangItemAdapter();
        groupXiangitenBeans = new ArrayList<>();

    }


    @OnClick({R.id.qun_name, R.id.qun_nicheng, R.id.qun_jieshao, R.id.quan_gonggao, R.id.qun_chengyuan, R.id.yanzhen,R.id.yaoqing_qunxian, R.id.xiugai, R.id.shenfen_yanzheng})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.qun_name:
                new AlertDialog.Builder(this).setTitle("修改群名称").setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismissProgressDialog();
                    }
                }).setNegativeBtnColor(getResourceColor(R.color.text_color_7))
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }).build().show();
                break;
            case R.id.qun_nicheng:
                new AlertDialog.Builder(this).setTitle("修改群昵称").setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismissProgressDialog();
                    }
                }).setNegativeBtnColor(getResourceColor(R.color.text_color_7))
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }).build().show();
                break;
            case R.id.qun_jieshao:
                new AlertDialog.Builder(this).setTitle("修改群介绍").setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismissProgressDialog();
                    }
                }).setNegativeBtnColor(getResourceColor(R.color.text_color_7))
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }).build().show();
                break;
            case R.id.quan_gonggao:
                QungonggaoActivity.launcher(this);
                break;
            case R.id.qun_chengyuan:
                QunchengyuanActivity.launcher(this);
                break;
            case R.id.yanzhen:
                new IDCardDialog(this).show();
                break;
            case R.id.yaoqing_qunxian:
                new QunDialog.Builder(this)
                        .setTitle("邀请他人入群")
                        .setMessage("管理员邀请")
                        .setmPositiveText("所有人邀请")
                        .setcanText("取消").build().show();
                break;
            case R.id.xiugai:
               new QunDialog.Builder(this)
                        .setTitle("群资料修改权限")
                        .setMessage("管理员邀请")
                        .setmPositiveText("所有人邀请")
                        .setcanText("取消").build().show();
                break;
            case R.id.shenfen_yanzheng:
               new QunDialog.Builder(this)
                        .setTitle("被邀请人身份验证")
                        .setMessage("需要验证")
                        .setmPositiveText("不需要验证")
                        .setcanText("取消").build().show();
                break;
        }
    }



}
