package com.yuyuka.billiards.ui.activity.table;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.base.BaseMvpActivity;
import com.yuyuka.billiards.image.ImageManager;
import com.yuyuka.billiards.pojo.CustomNoticePojo;
import com.yuyuka.billiards.ui.activity.pay.TablePayActivity;
import com.yuyuka.billiards.utils.BarUtils;
import com.yuyuka.billiards.utils.CommonUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class SingleBattleActivity extends BaseActivity {

    CustomNoticePojo data;
    @BindView(R.id.iv_head_user1)
    ImageView userHead1;
    @BindView(R.id.tv_user_name1)
    TextView userName1;




    public static void launcher(Activity context, CustomNoticePojo data){
        Intent intent = new Intent(context, SingleBattleActivity.class);
        intent.putExtra("data",data);
        context.startActivityForResult(intent,0);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_my_battle);
        super.mStatusBar.setVisibility(View.GONE);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            mStatusBar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, BarUtils.getStatusBarHeight(this)));
        }else {
            mStatusBar.setVisibility(View.GONE);
        }

        ImageManager.getInstance().loadNet(data.getBizContent().getUser1().getHeadImage(),userHead1);
        userName1.setText(data.getBizContent().getUser1().getUserName());
    }

    @OnClick(R.id.end_battle)
    public void onClick(View v){
        CustomNoticePojo.BizContent content = data.getBizContent();
        switch (v.getId()){
            case R.id.end_battle:
                TablePayActivity.launcher(this,data.getBizContent().getBattle().getId());
                break;
        }
    }

    @Override
    protected void initData() {
        data = (CustomNoticePojo) getIntent().getSerializableExtra("data");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            finish();
        }
    }
}
