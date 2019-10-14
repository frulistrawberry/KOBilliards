package com.yuyuka.billiards.ui.activity.table;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.image.ImageManager;
import com.yuyuka.billiards.pojo.CustomNoticePojo;
import com.yuyuka.billiards.utils.BarUtils;
import com.yuyuka.billiards.utils.CommonUtils;

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



    public static void launcher(Context context, CustomNoticePojo data){
        Intent intent = new Intent(context,BattleActivity.class);
        intent.putExtra("data",data);
        context.startActivity(intent);
    }

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
    }

    @OnClick(R.id.end_battle)
    public void onClick(View v){
        CustomNoticePojo.BizContent content = data.getBizContent();
        switch (v.getId()){
            case R.id.end_battle:
                int userId = CommonUtils.getUserId();
                int user1Id = content.getUser1().getId();
                int user2Id = content.getUser2().getId();
                BattleEndActivity.launcher(this,userId == user1Id?user2Id:user1Id,content.getBattle().getId());

                break;
        }
    }

    @Override
    protected void initData() {
        data = (CustomNoticePojo) getIntent().getSerializableExtra("data");
    }
}
