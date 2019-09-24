package com.yuyuka.billiards.ui.activity.message;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;

public class QunGongGaoNewActivity extends BaseActivity {
    public static void launcher(Context context) {
        context.startActivity(new Intent(context, QunGongGaoNewActivity.class));
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("新建群公告").setRightText("保存", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).showBack().show();
    }

    @Override
    protected void initView() {
      setContentView(R.layout.layout_qungonggaonewactivity);
    }

    @Override
    protected void initData() {

    }
}
